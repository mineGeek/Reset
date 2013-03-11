package com.github.mineGeek.Reset.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import com.github.mineGeek.Reset.Events.TimerEventStart;
import com.github.mineGeek.Reset.Events.TimerEventsComplete;
import com.github.mineGeek.Reset.Main.Reset;
import com.github.mineGeek.Reset.Main.ResetRegistry.Scope;
import com.github.mineGeek.Reset.Structs.MessageItem;
import com.github.mineGeek.Timers.Structs.Timer;


public class Message extends ActionBase {

	public enum MessageEvent {START, COMPLETE, STOP, INCRIMENT};
	public Map< MessageEvent, List<MessageItem>> messages = new WeakHashMap< MessageEvent, List<MessageItem> >();

	public void clear() {
		for( List<MessageItem> items : messages.values() ) { 
			for( MessageItem item : items ) { item.close(); }
		}
		messages.clear();
	}
	public void addMessageItem( MessageEvent event, MessageItem item ) {
		if ( !messages.containsKey( event ) ) { messages.put( event, new ArrayList<MessageItem>() ); }
		messages.get( event ).add( item );
	}
	
	public void addMessage( MessageEvent event, Scope scope, String message ) {
		MessageItem item = new MessageItem( scope, message, this);
		this.addMessageItem( event, item);
	}
	
	public void broadCast( MessageEvent event, Object[] args ) {
		if ( messages.containsKey( event ) ) Reset.getRegistry().broadCastMessageItems( messages.get( event), args );
	}
	
	@Override
	public void ini() {
		
		if ( this.secondComplete != null || this.secondInterval != null || this.secondStart != null ) {
			
			super.timer = new Timer();
			super.timer.secondStart = this.secondStart;
			super.timer.secondInterval = this.secondInterval;
			super.timer.secondStop = this.secondComplete;
			if ( messages.containsKey( MessageEvent.START ) && this.secondStart != null ) 		this.timer.eventHandler.start = new TimerEventStart( this );
			if ( messages.containsKey( MessageEvent.COMPLETE ) && this.secondComplete != null )	this.timer.eventHandler.complete = new TimerEventsComplete( this );			
			super.getClock().addSubTimer( super.timer );
			super.ini();
			
			
		} else {		
			super.ini();
		}

		
	}
	
	@Override
	public void complete( Object[] args ) {
		if ( messages.containsKey( MessageEvent.COMPLETE ) ) Reset.getRegistry().broadCastMessageItems( messages.get( MessageEvent.COMPLETE), args);
	}
	
	@Override
	public void start( Object[] args ) {
		//super.start(args);
		if ( messages.containsKey( MessageEvent.START ) ) {
			List<MessageItem> m = messages.get( MessageEvent.START );
			Reset.getRegistry().broadCastMessageItems( m, args);
		}
	}
	
	@Override
	public void stop( Object[] args ) {
		if ( messages.containsKey( MessageEvent.STOP ) ) Reset.getRegistry().broadCastMessageItems( messages.get( MessageEvent.STOP), args);
	}	
	
	public void close() {
		super.close();
		clear();
	}



}
