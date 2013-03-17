package com.github.mineGeek.Reset.Main;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.github.mineGeek.Areas.Structs.Area;
import com.github.mineGeek.Areas.Structs.AreaChunks;
import com.github.mineGeek.Areas.Structs.IAreaEventsHandler;


import com.github.mineGeek.Reset.Actions.RepeatingResetAction;
import com.github.mineGeek.Reset.Structs.MessageItem;

public class ResetRegistry implements IAreaEventsHandler {

	public enum Scope { AREA, SURROUND, WORLD, SERVER, NONE };
	public static Set<RepeatingResetAction> resets;
	public static Map< String, RepeatingResetAction > tagged = new WeakHashMap< String, RepeatingResetAction >();
	AreaChunks areaChunks = new AreaChunks();	
	
	public void broadCastMessageItem( MessageItem item, Object[] args ) {
		Bukkit.broadcastMessage( String.format( item.message, args) );
	}
	
	public void broadCastMessageItems( List<MessageItem> items, Object[] args ) {
		for ( MessageItem item : items ) { broadCastMessageItem( item, args ); }
		
	}
	
	public void clearReset( String tag ) {
		
		if ( tagged.containsKey( tag ) ) {
			tagged.get( tag ).stop();
			tagged.get( tag ).close();
		}
		
	}
		
	public void startReset( String tag ) {
		
		if ( tagged.containsKey( tag ) ) tagged.get( tag ).start();
		
	}
	
	public void stopReset( String tag ) {
		
		if ( tagged.containsKey( tag ) ) tagged.get( tag ).stop();
	}
	
	public void addReset( RepeatingResetAction reset ) {
		
		removeReset( reset );
		areaChunks.add( reset.area );
		
	}
	
	public void playerMove( Player p ) {

		Set<Area> areas = areaChunks.getByChunkHash( p.getLocation().getChunk().hashCode() );
		
		if ( areas != null ) {
			
			for ( Area a : areas ) { a.updatePlayerLocation(p); }
			
		}
		
	}
	
	public void playerMoveChunk( Player p, Integer oldHash, Integer newHash ) {

		Set<Area> set = new HashSet<Area>();
		Set<Area> add = null;
		
		add = areaChunks.getByChunkHash( oldHash );
		
		if ( add != null ) set.addAll( add );
		
		add = areaChunks.getByChunkHash( newHash );
		if ( add != null ) set.addAll( add );
		
	}
	
	public void removeReset( RepeatingResetAction reset ) {
		Area area = reset.area;
		areaChunks.remove( area );
		if ( tagged.containsKey( reset.tag ) ) resets.remove( reset.tag );
		resets.remove( reset );
		
	}	
	
	public void close() {

		for ( RepeatingResetAction r : tagged.values() ) r.close();
		if ( resets != null ) resets.clear();
		if ( tagged != null) tagged.clear();
	}

	
}
