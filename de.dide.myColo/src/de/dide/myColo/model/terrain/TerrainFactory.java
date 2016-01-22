package de.dide.myColo.model.terrain;

import de.dide.myColo.model.terrain.impl.Land;
import de.dide.myColo.model.terrain.impl.Water;

public class TerrainFactory {

	public AbstractTerrain makeTerrainType(String newType) {
		AbstractTerrain newTerrain = null;
		
		switch (newType) {
			case "land":
				return new Land();
			case "water":
				return new Water();
			default:
				return new Land();
		}
	}
}
