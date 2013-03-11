package com.github.mineGeek.Reset.Structs;

import java.util.List;

import com.github.mineGeek.Timers.Structs.TimerCollection;

public interface IAction {
	
	public void close();
	public void ini();
	public void start( Object[] args );
	public void complete( Object[] args );
	public void stop( Object[] args );
	public void incriment( Object[] args );
	public List<IAction> preActions();
	public List<IAction> postActions();
	public TimerCollection getClock();
	public void setClock( TimerCollection clock );
	public void addPreAction( IAction action );
	public void addPostAction( IAction action);
	
}
