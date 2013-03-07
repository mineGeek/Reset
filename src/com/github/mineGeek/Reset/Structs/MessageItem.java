package com.github.mineGeek.Reset.Structs;

import com.github.mineGeek.Reset.Actions.Message;
import com.github.mineGeek.Reset.Main.ResetRegistry.Scope;

public class MessageItem {

	public String message = null;
	public Scope scope = Scope.AREA;
	public Message parent;
	
	public MessageItem() {}
	
	public MessageItem( Scope scope, String message, Message parent ) {
		this.scope = scope;
		this.message = message;
		this.parent = parent;
	}
	
	public void close() {
		parent = null;
	}
}
