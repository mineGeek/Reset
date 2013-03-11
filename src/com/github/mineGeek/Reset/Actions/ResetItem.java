package com.github.mineGeek.Reset.Actions;



import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;

import com.github.mineGeek.Areas.Structs.Area;
import com.github.mineGeek.Reset.Events.TimerEventStart;
import com.github.mineGeek.Reset.Events.TimerEventsComplete;
import com.github.mineGeek.Reset.Structs.IAction;
import com.github.mineGeek.Timers.Structs.TimerCollection;

public class ResetItem extends TimerCollection implements IAction {

	public String 	tag = null;
	public Area 	area = new Area();
	
	public List<IAction> preActions = new ArrayList<IAction>();
	public List<IAction> postActions = new ArrayList<IAction>();	
	
	public ResetItem( String tag ) { 
		this.tag = tag;
	}
	
	public void addPreAction( IAction action ) {
		
		action.setClock( this );
		preActions.add( action );
		
	}
	
	public void addPostAction( IAction action ) { 
		action.setClock( this );
		postActions.add( action );
	}
	
	@Override
	public void ini() {
		super.ini();
		this.clock.handlerComplete 	= new TimerEventsComplete( this );
		this.clock.handlerStart 	= new TimerEventStart( this );
		for( IAction a : preActions ) a.ini();
		for (IAction a : postActions ) a.ini();
	}
	
	public void complete( Object[] args ) {
		for ( IAction a : preActions ) a.start(args);
		super.stop();
		Bukkit.broadcastMessage("resetting...................................");
		start();
		for ( IAction a : postActions ) a.complete(args);
		
	}

	@Override
	public void start(Object[] args) {}

	@Override
	public void stop(Object[] args) {}

	@Override
	public void incriment(Object[] args) {}

	@Override
	public List<IAction> preActions() { return null; }

	@Override
	public List<IAction> postActions() { return null; }

	@Override
	public TimerCollection getClock() { return null; }

	@Override
	public void setClock(TimerCollection clock) {}











}
