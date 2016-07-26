package de.dide.myColo.model.terrain.land.impl;

import de.dide.myColo.model.terrain.AbstractTerrain;
import de.dide.myColo.util.Colors;

public class Water extends AbstractTerrain {
	
	public Water() {
		super(Colors.COL_LANDTYPE_WATER);
		this.setNameOfTerrainType("water");
		setMovecost(1);
		setIsLand(false);
	}

}
