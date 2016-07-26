package de.dide.myColo.model.terrain.land;

import de.dide.myColo.model.terrain.AbstractTerrain;

public class AbstractLand extends AbstractTerrain{

	protected boolean hasRoad;
	protected boolean isLandConvertable;
	
	
	public AbstractLand() {
		super();
		hasRoad = false;
	}
	
	@Override
	public boolean isRoadOverTerrain() {
		return hasRoad;
	}

	@Override
	public void setRoadOverTerrain(boolean hasRoad) {
		hasRoad = true;
	}

	@Override
	public boolean isLandConvertable() {
		return isLandConvertable;
	}
	
}
