package com.github.mineGeek.Reset.Actions;

import java.util.List;

import com.github.mineGeek.Reset.Structs.IAction;
import com.github.mineGeek.Timers.Structs.ITimer;

abstract class ActionBase implements Runnable, ITimer, IAction {

	public Integer start 	= null;
	public Integer interval = null;
	public Integer end 		= null;
	
	public void run() {}
	
	public List<ITimer> getTimerItems() { return null; }
	
	public Integer getStart() { return null; }

	public Integer getInterval() { return null;	}

	public Integer getEnd() { return null; }

	public Integer getTaskId() { return null; }

	public Integer getEndTaskId() { return null; }

	public Long getLastStartTime() { return null; }

	public boolean isRunning() { return false; }

	public void setTaskId(Integer taskId) {}

	public void setEndTaskId(Integer taskId) {}

	public void setTimerStart(Long start) {}

	public void setTimerEnd(Long end) {}

	public void ding(Object[] args) {}

	public void start(Object[] args) {}

	public void end(Object[] args) {}

	public void reset(Object[] args) {}

	public void paused(Object[] args) {}

	public void resume(Object[] args) {}
	
	public void close() {}
}
