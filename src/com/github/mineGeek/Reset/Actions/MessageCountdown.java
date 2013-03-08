package com.github.mineGeek.Reset.Actions;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;

import com.github.mineGeek.Reset.Actions.Message.MessageEvent;
import com.github.mineGeek.Reset.Events.TimerEventStart;
import com.github.mineGeek.Reset.Events.TimerEventStop;
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
				m.secondsDelay = x;
				m.addMessage( MessageEvent.END, scope, message );
				this.postActions.add( m );
			}
			
		}
		
	}
	
	@Override
	public void ini() {
		queueMessages();
		super.ini();
		this.timer.endHandler = new TimerEventStart( this );
	}
	
	@Override
	public void start() {
		Bukkit.broadcastMessage("poop");
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	
}
