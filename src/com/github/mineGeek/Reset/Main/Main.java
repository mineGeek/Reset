package com.github.mineGeek.Reset.Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.mineGeek.Areas.Events.AreaListeners;
import com.github.mineGeek.Areas.Main.Registry;
import com.github.mineGeek.Areas.Structs.Reset;
import com.github.mineGeek.Areas.Structs.Area.PVPMode;
import com.github.mineGeek.Reset.Events.Listeners;


public class Main extends JavaPlugin {

	@Override
	public void onDisable() {
		
		Registry.close();
	}
	
	@Override
	public void onEnable() {
		
		
		Location ne1 = new Location( Bukkit.getWorld("yeppers pepps"), 923, 4, -1674);
		Location sw1 = new Location( Bukkit.getWorld("yeppers pepps"), 895, 10, -1702 );
		Reset reset1 = new Reset("ted");
		reset1.area.setNE(ne1);
		reset1.area.setSW(sw1);
		reset1.area.pvpMode = PVPMode.ON;
		Registry.resets.addReset( reset1 );
		
    	/**
    	 * Listeners
    	 */
		this.getServer().getPluginManager().registerEvents( new Listeners(), this);
    	this.getServer().getPluginManager().registerEvents( new AreaListeners(), this);
    	
    	
    	//Make some new zones for testing.
    	
		
		for( Player p : getServer().getOnlinePlayers() ) {
			Registry.updatePlayerMove(p);
		}
	}
	
}
