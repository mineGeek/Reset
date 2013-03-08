package com.github.mineGeek.Reset.Actions;



import org.bukkit.Bukkit;

import com.github.mineGeek.Areas.Structs.Area;
import com.github.mineGeek.Reset.Events.TimerEventStart;
import com.github.mineGeek.Reset.Events.TimerEventStop;



public class ResetItem extends ActionBase {

	public String tag = null;
	public Area area = new Area();
	
	public ResetItem( String tag ) { 
		this.tag = tag;
	}
	
	@Override
	public void ini() {
		super.ini();
		this.timer.startHandler = new TimerEventStart( this );
		this.timer.endHandler = new TimerEventStop( this );
	}
	
	@Override
	public void stop() {
		super.stop();
		Bukkit.broadcastMessage("resetting...................................");
		start();
	}
	
	public void close() {

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}










}
