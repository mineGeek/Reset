package com.github.mineGeek.Reset.Actions;

import java.util.ArrayList;
import java.util.List;

import com.github.mineGeek.Areas.Structs.Area;
import com.github.mineGeek.Reset.Structs.ActionTimerEvent;
import com.github.mineGeek.Reset.Structs.IAction;
import com.github.mineGeek.Timers.Structs.Timer2;

abstract class ActionBase implements IAction {

	public Integer 	secondsDelay 	= null;
	public Integer 	secondsInto 	= null;
	public Integer 	secondsRepeat	= null;
	public Integer 	secondsLong		= null;
	public Long 	lastStartTime	= null;
	
	
	public Area area = new Area();
	public List<IAction> preActions = new ArrayList<IAction>();
	public List<IAction> postActions = new ArrayList<IAction>();	
	
	public ActionTimerEvent handler = new ActionTimerEvent( this );
	public Timer2 timer = new Timer2( handler );
	
	
	public List<IAction> preActions() { return this.preActions;	}
	
	public List<IAction> postActions() { return this.postActions; }
	
	public void start() {
		for ( IAction a : preActions ) a.start();
		this.timer.start();
		for ( IAction a : postActions ) a.start();
		
	}
	
	public void stop() {
		this.timer.stop();
	}	
	
	public void ini() {
		
		timer.secondsDelay 	= secondsDelay;
		timer.secondsInto 	= secondsInto;
		timer.secondsLong 	= secondsLong;
		timer.secondsRepeat = secondsRepeat;
		
		for ( IAction a : preActions ) a.ini();
		for ( IAction a : postActions) a.ini();
		
	}
	

}
