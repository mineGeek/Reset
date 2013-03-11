package com.github.mineGeek.Reset.Events;

import com.github.mineGeek.Reset.Structs.IAction;
import com.github.mineGeek.Timers.Events.TimerEvent;

public class TimerEventsComplete extends TimerEvent {
	public IAction parent;
	public TimerEventsComplete( IAction parent ) {
		this.parent = parent; 
	}
	
	@Override
	public void run() {
		if ( parent!= null) parent.complete( this.getTimeArgs() );
	}
}
