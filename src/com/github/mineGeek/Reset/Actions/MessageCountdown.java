package com.github.mineGeek.Reset.Actions;

import java.util.ArrayList;
import java.util.List;


import com.github.mineGeek.Reset.Actions.Message.MessageEvent;
import com.github.mineGeek.Reset.Main.Utilities;
import com.github.mineGeek.Reset.Main.ResetRegistry.Scope;

public class MessageCountdown extends ActionBase {

	public List<String> 	scheduleText 	= new ArrayList< String >();
	public List<Integer> 	schedule 		= new ArrayList< Integer >();
	public List<Message> 	messages 		= new ArrayList< Message >();
	public String message = null;
	public Scope scope = Scope.AREA;
	
	public MessageCountdown( List<String> timer, String message ) {
		clear();
		setSchedule( timer );
		this.message = message;
	}
	
	public void queueMessages() {
		
		if ( !schedule.isEmpty() ) {
			
			for( Integer x : schedule ) {
				Message m = new Message();
				m.secondStart = x;
				m.addMessage( MessageEvent.START, scope, message );
				this.addPostAction( m );
				
				
			}
			
		}
		
	}
	
		
	@Override
	public void ini() {
		queueMessages();
		super.ini();
	}
	
	
	public void clear() {
		if ( !messages.isEmpty() ) for ( Message m : messages )  m.close();
		messages.clear();
		schedule.clear();
		scheduleText.clear();
	}
	
	public void setSchedule( List<String> list ) {
		scheduleText = list;
		schedule = Utilities.getSecondsFromText(list);
	}

	@Override
	public void close() {}

	
	
}
