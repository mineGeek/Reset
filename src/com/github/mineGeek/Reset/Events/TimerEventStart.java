package com.github.mineGeek.Reset.Events;

import com.github.mineGeek.Reset.Structs.IAction;
import com.github.mineGeek.Timers.Structs.TimerEvent;

public class TimerEventStart extends TimerEvent {

	public IAction parent;
	public TimerEventStart( IAction parent ) { this.parent = parent; }
	
	@Override
	public void run() {
		if ( parent != null) parent.start( this.getTimeArgs() );
	}
	
}
