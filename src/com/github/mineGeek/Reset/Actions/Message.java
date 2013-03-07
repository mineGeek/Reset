package com.github.mineGeek.Reset.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import com.github.mineGeek.Reset.Main.Reset;
import com.github.mineGeek.Reset.Main.ResetRegistry.Scope;
import com.github.mineGeek.Reset.Structs.MessageItem;

public class Message extends ActionBase {

	public enum MessageEvent {START, END, STOP, RESUME, INCRIMENT, RESET, PAUSE};
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
	public void ding( Object args[] ) {
		broadCast( MessageEvent.INCRIMENT, args );
	}
	
	@Override
	public void start( Object args[] ) {
		broadCast( MessageEvent.START, args );
	}
	
	@Override
	public void end( Object args[] ) {
		broadCast( MessageEvent.END, args );
	}
	
	@Override
	public void reset( Object args[] ) {
		broadCast( MessageEvent.RESET, args );
	}
	
	@Override
	public void paused( Object args[] ) {
		broadCast( MessageEvent.PAUSE, args );
	}
	
	@Override
	public void resume( Object args[] ) {
		broadCast( MessageEvent.RESUME, args );
	}	
	
	public void close() {
		clear();
	}
	


}
