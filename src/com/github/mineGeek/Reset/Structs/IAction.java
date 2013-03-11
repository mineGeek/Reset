package com.github.mineGeek.Reset.Structs;

import java.util.List;

import com.github.mineGeek.Timers.Structs.Timers;

public interface IAction {
	
	public void close();
	public void ini();
	public void start( Object[] args );
	public void complete( Object[] args );
	public void stop( Object[] args );
	public void incriment( Object[] args );
	public List<IAction> preActions();
	public List<IAction> postActions();
	public Timers getClock();
	public void setClock( Timers clock );
	public void addPreAction( IAction action );
	public void addPostAction( IAction action);
	
}
