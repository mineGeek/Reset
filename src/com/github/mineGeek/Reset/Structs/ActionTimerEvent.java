package com.github.mineGeek.Reset.Structs;

import com.github.mineGeek.Timers.Structs.ITimer;

public class ActionTimerEvent implements ITimer, Runnable {
	
	public IAction parent = null;
	
	public ActionTimerEvent( IAction parent ) {
		this.parent = parent;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getStart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getInterval() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getEnd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getTaskId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getEndTaskId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getLastStartTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRunning() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setTaskId(Integer taskId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEndTaskId(Integer taskId) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void ding(Object[] args) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void preStop(Object[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop(Object[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postStop(Object[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset(Object[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paused(Object[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume(Object[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void setTotalSeconds(Integer totalSeconds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTimerStart(Integer start) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTimerComplete(Integer complete) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTimerInterval(Integer interval) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preStart(Object[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start(Object[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postStart(Object[] args) {
		// TODO Auto-generated method stub
		
	}
}
