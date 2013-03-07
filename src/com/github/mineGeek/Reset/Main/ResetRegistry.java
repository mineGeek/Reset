package com.github.mineGeek.Reset.Main;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.bukkit.entity.Player;

import com.github.mineGeek.Areas.Structs.Area;
import com.github.mineGeek.Areas.Structs.AreaChunks;
import com.github.mineGeek.Areas.Structs.IAreaEventsHandler;
import com.github.mineGeek.Reset.Structs.MessageItem;
import com.github.mineGeek.Reset.Structs.ResetItem;
import com.github.mineGeek.Timers.Structs.ITimer;
import com.github.mineGeek.Timers.Structs.Timer;

public class ResetRegistry implements IAreaEventsHandler {

	public enum Scope { AREA, SURROUND, WORLD, SERVER, NONE };
	public static Map<String, Timer> timers = new WeakHashMap<String, Timer>();
	AreaChunks areaChunks = new AreaChunks();
	public Map<String, ResetItem> resets = new WeakHashMap<String, ResetItem>();	
	
	public void broadCastMessageItem( MessageItem item, Object[] args ) {
		
	}
	
	public void broadCastMessageItems( List<MessageItem> items, Object[] args ) {
		for ( MessageItem item : items ) { broadCastMessageItem( item, args ); }
	}
	
	public void clearTimer( String tag ) {
		
		if ( timers.containsKey( tag ) ) {
			timers.get( tag ).stop();
			timers.get( tag ).close();
		}
		
	}
	
	public void addTimer( String tag ) {
		
		Timer timer = null;
		if ( timers.containsKey( tag ) ) {
			timer = timers.get( tag );
		} else {
			timer = new Timer();
			timers.put( tag, timer );
		}
		
	}
	
	public void addToTimer( String tag, ITimer action ) {
		
		Timer timer = null;
		if ( timers.containsKey( tag ) ) {
			timer = timers.get( tag );
		} else {
			timer = new Timer();
			timers.put( tag, timer );
		}
		
		timer.timerItems.add( action );
		
	}
	
	public void startTimer( String tag ) {
		
		Timer t = null;
		if ( timers.containsKey( tag ) ) t = timers.get( tag );
		if ( t == null ) return;
		t.start();		
		
	}
	
	public void stopTimer( String tag ) {
		Timer t = null;
		if ( timers.containsKey( tag ) ) t = timers.get( tag );
		if ( t == null ) return;
		t.stop();			
	}
	
	public void addReset( ResetItem reset ) {
		
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
	
	public void removeReset( ResetItem reset ) {
		
		
		Area area = reset.area;
		areaChunks.remove( area );
		if ( resets.containsKey( reset.tag ) ) resets.remove( reset.tag );
		
	}	
	
	public void close() {
		//areaChunks.close();
		//resets.clear();		
		for( String x : timers.keySet() ) stopTimer( x );
		for( Timer t : timers.values() ) t.timerItems.clear();
		timers.clear();
	}

	
}
