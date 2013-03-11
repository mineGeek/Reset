package com.github.mineGeek.Reset.Actions;

import java.util.ArrayList;
import java.util.List;

import com.github.mineGeek.Reset.Structs.IAction;
import com.github.mineGeek.Timers.Structs.SubTimer;
import com.github.mineGeek.Timers.Structs.TimerCollection;

abstract class ActionBase implements IAction {

	public Integer 	secondStart 	= null;
	public Integer 	secondInterval 	= null;
	public Integer 	secondComplete	= null;	
	
	public TimerCollection clock	= null;
	
	public IAction		parent	= null;
	public List<IAction> preActions = new ArrayList<IAction>();
	public List<IAction> postActions = new ArrayList<IAction>();
	
	public SubTimer timer = null;
	
	public List<IAction> preActions() { return this.preActions;	}	
	public List<IAction> postActions() { return this.postActions; }
	
	public void setClock( TimerCollection clock ) { this.clock = clock; }
	public TimerCollection getClock() { return this.clock; }
	
	public void addPreAction( IAction action ) {
		action.setClock(clock);
		preActions.add( action );
		
	}
	
	public void addPostAction( IAction action ) { 
		action.setClock(clock);
		postActions.add( action );
	}	
	
	public void start( Object[] args ) {
		for ( IAction a : preActions ) a.start( args );
		if ( timer != null ) this.timer.start();
		for ( IAction a : postActions ) a.start( args );
		
	}

	public void incriment( Object[] args ) {
		for ( IAction a : preActions ) a.incriment( args );
		if ( timer != null ) this.timer.stop();
		for ( IAction a : preActions ) a.incriment( args );
	}	
	
	public void complete( Object[] args ) {
		for ( IAction a : preActions ) a.complete( args );
		if ( timer != null ) this.timer.stop();
		for ( IAction a : preActions ) a.complete( args );
	}
	
	public void stop( Object[] args ) {
		for ( IAction a : preActions ) a.stop( args );
		if ( timer != null ) this.timer.stop();
		for ( IAction a : preActions ) a.stop( args );
	}
	
	public void ini() {
		
		if ( timer != null ) {
			timer.secondStart 		= secondStart;
			timer.secondInterval 	= secondInterval;
			timer.secondStop 		= secondComplete;
			timer.ini();
		} 
		for ( IAction a : preActions ) a.ini();
		for ( IAction a : postActions) a.ini();
		
	}
	
	public void close() {
		for ( IAction a : preActions ) a.close(); preActions.clear();
		if ( timer != null ) this.timer.close(); this.timer = null;
		for ( IAction a : preActions ) a.close(); postActions.clear();
	}
	

}
