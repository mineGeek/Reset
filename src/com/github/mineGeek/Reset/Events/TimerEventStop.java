package com.github.mineGeek.Reset.Events;

import com.github.mineGeek.Reset.Structs.IAction;
import com.github.mineGeek.Timers.Events.TimerEvent;

public class TimerEventStop extends TimerEvent {
	public IAction parent;
	public TimerEventStop( IAction parent ) {
		this.parent = parent; 
	}
	
	@Override
	public void run() {
		if ( parent!= null) parent.stop( this.getTimeArgs() );
	}
}
