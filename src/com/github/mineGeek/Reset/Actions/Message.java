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
	
	public void close() {
		clear();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	


}
