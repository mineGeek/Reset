package com.github.mineGeek.Reset.Actions;

import com.github.mineGeek.Timers.Structs.ITimer;

abstract class ActionBase implements Runnable, ITimer {

	public Integer start 	= null;
	public Integer interval = null;
	public Integer end 		= null;
	
}
