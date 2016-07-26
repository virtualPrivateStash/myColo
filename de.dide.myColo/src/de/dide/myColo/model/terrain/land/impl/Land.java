package de.dide.myColo.model.terrain.land.impl;

import de.dide.myColo.model.terrain.AbstractTerrain;
import de.dide.myColo.util.Colors;

public class Land extends AbstractTerrain{
	
	private static final String terrainColor = Colors.COL_GREEN;
	
	public Land() {
		super(terrainColor);
		this.setNameOfTerrainType("land");
		setIsLand(true);
		
	}
}
