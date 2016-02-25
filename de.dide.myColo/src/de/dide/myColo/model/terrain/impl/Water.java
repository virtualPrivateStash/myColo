package de.dide.myColo.model.terrain.impl;

import de.dide.myColo.model.terrain.AbstractTerrain;

public class Water extends AbstractTerrain {

	public Water() {
		this.setNameOfTerrainType("water");
		setIsLand(false);
	}

}
