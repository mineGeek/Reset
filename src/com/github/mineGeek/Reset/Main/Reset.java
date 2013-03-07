package com.github.mineGeek.Reset.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.mineGeek.Areas.Events.AreasListeners;
import com.github.mineGeek.Areas.Main.AreasRegistry;
import com.github.mineGeek.Areas.Structs.Area.PVPMode;
import com.github.mineGeek.Reset.Actions.MessageCountdown;
import com.github.mineGeek.Reset.Events.Listeners;
import com.github.mineGeek.Reset.Structs.ResetItem;


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
		registry.close();
		registry = null;
		plugin = null;
	}
	
	@Override
	public void onEnable() {
		
		plugin = this;
		registry = new ResetRegistry();
		
		Location ne1 = new Location( Bukkit.getWorld("yeppers pepps"), 923, 4, -1674);
		Location sw1 = new Location( Bukkit.getWorld("yeppers pepps"), 895, 10, -1702 );
		
		ResetItem reset1 = new ResetItem("ted");
		reset1.area.setNE(ne1);
		reset1.area.setSW(sw1);
		reset1.area.pvpMode = PVPMode.ON;
		AreasRegistry.areaHandler = getRegistry();
		
		List<String> list = new ArrayList<String>();
		list.add( "1s"); list.add( "2s" ); list.add("3s"); list.add("4s"); list.add("5s");
		MessageCountdown m = new MessageCountdown( list, "hi there %s");
		reset1.postActions.add( m );
		
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
		

	}
	
}
