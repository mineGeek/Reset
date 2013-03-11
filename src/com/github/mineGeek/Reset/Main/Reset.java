package com.github.mineGeek.Reset.Main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


import com.github.mineGeek.Areas.Events.AreasListeners;
import com.github.mineGeek.Areas.Main.AreasRegistry;
import com.github.mineGeek.Areas.Structs.Area.PVPMode;
import com.github.mineGeek.Reset.Actions.MessageCountdown;
import com.github.mineGeek.Reset.Actions.ResetItem;
import com.github.mineGeek.Reset.Events.Listeners;
import com.github.mineGeek.Timers.Main.TimersRegistry;


public class Reset extends JavaPlugin {

	private static JavaPlugin plugin = null;
	private static ResetRegistry registry = null;
	
	public static JavaPlugin getInstance() {
		return plugin;
	}
	
	public static ResetRegistry getRegistry() {
		return registry;
	}
	
	@Override
	public void onDisable() {
		
		AreasRegistry.close();
		TimersRegistry.close();
		registry.close();		
		registry = null;
		plugin = null;
	}
	
	@Override
	public void onEnable() {

		plugin = this;
		registry = new ResetRegistry();
		
		Location ne1 = new Location( Bukkit.getWorld("pickleMasherD"), 923, 4, -1674);
		Location sw1 = new Location( Bukkit.getWorld("pickleMasherD"), 895, 10, -1702 );
		
		AreasRegistry.areaHandler = getRegistry();
		TimersRegistry.plugin = this;
		TimersRegistry.persistTimerData = true;		
		
		ResetItem reset1 = new ResetItem("ted");
		reset1.area.setNE(ne1);
		reset1.area.setSW(sw1);
		reset1.area.pvpMode = PVPMode.ON;

		
		
		
		reset1.length = 15;
				
		
		List<String> list = new ArrayList<String>();
		list.add( "1s"); list.add( "2s" ); list.add("3s"); list.add("4s"); list.add("5s");
		list.add( "10s"); list.add( "11s" ); list.add("12s"); list.add("13s"); list.add("14s");
		MessageCountdown m = new MessageCountdown( list, "hi there 1- %1$s 2- %2$s 3- %3$s");

		reset1.addPostAction( m );

		

		//reset1.timer.start = 0;
		//reset1.timer.end = 15;
		
		//AreasRegistry.resets.addReset( reset1 );
		
    	/**
    	 * Listeners
    	 */
		this.getServer().getPluginManager().registerEvents( new Listeners(), this);
    	this.getServer().getPluginManager().registerEvents( new AreasListeners(), this);
    	
    	
    	//Make some new zones for testing.
    	
		
		for( Player p : getServer().getOnlinePlayers() ) {
			AreasRegistry.updatePlayerMove(p);
		}
		
		reset1.ini();
		reset1.start();
		

	}
	
}
