package com.github.mineGeek.Reset.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.github.mineGeek.Areas.Events.OnAreaEnter;

public class Listeners implements Listener {
	
	@EventHandler(priority = EventPriority.LOWEST )
	public void onPlayerEnter( OnAreaEnter evt ) {
		
		evt.getPlayer().sendMessage("hiiii");
		
	}
	
}
