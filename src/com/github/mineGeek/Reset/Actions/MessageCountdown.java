package com.github.mineGeek.Reset.Actions;

import java.util.ArrayList;
import java.util.List;

import com.github.mineGeek.Reset.Actions.Message.MessageEvent;
import com.github.mineGeek.Reset.Main.Utilities;
import com.github.mineGeek.Reset.Main.ResetRegistry.Scope;
import com.github.mineGeek.Timers.Structs.ITimer;

public class MessageCountdown extends ActionBase {

	public List< String > 	scheduleText 	= new ArrayList< String >();
	public List< Integer > 	schedule 		= new ArrayList< Integer >();
	public List<ITimer> 	messages 		= new ArrayList< ITimer >();
	public String message = null;
	public Scope scope = Scope.AREA;
	
	public MessageCountdown( List<String> timer, String message ) {
		clear();
		setSchedule( timer );
		this.message = message;
		queueMessages();

	}
	
	public void queueMessages() {
		
		if ( !schedule.isEmpty() ) {
			
			for( Integer x : schedule ) {
				Message m = new Message();
				m.start = x;
				m.addMessage( MessageEvent.END, scope, message );
				messages.add( m );
			}
			
		}
		
	}
	
	public void clear() {
		if ( !messages.isEmpty() ) for ( ITimer m : messages )  (( Message )m).close();
		messages.clear();
		schedule.clear();
		scheduleText.clear();
	}
	
	public void setSchedule( List<String> list ) {
		scheduleText = list;
		schedule = Utilities.getSecondsFromText(list);
	}
	
	@Override
	public void close() {
		clear();
	}
	
}
