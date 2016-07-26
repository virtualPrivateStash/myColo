package de.dide.myColo.model.gameField.impl.Terrain;

import de.dide.myColo.model.terrain.AbstractTerrain;
import de.dide.myColo.util.Colors;

public class Terrain_Default extends AbstractTerrain {

	private final static String terrainColor = Colors.COL_GREEN;
	private final static String name = "Default";
	
	public Terrain_Default() {
		super(terrainColor, name);
		setMovecost(1);
		setNameOfTerrainType("default");

	}
}
