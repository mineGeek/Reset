package com.github.mineGeek.Reset.Structs;

import java.util.ArrayList;
import java.util.List;

import com.github.mineGeek.Areas.Structs.Area;
import com.github.mineGeek.Areas.Structs.IArea;

public class ResetItem implements IArea {

	public String tag = null;
	public Area area = new Area();
	public List<IAction> preActions = new ArrayList<IAction>();
	public List<IAction> postActions = new ArrayList<IAction>();
	
	public ResetItem( String tag ) { this.tag = tag; }

	@Override
	public void startReset(Area area) {
		area.players.add("ted is on " + area.worldName );
		
	}
}
