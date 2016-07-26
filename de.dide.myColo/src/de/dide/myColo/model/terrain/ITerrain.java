package de.dide.myColo.model.terrain;

public interface ITerrain {
	boolean isLand();
	boolean isRoadOverTerrain();
	void setRoadOverTerrain(boolean hasRoad);
	boolean isLandConvertable();	//AreaType kann durch Pioniere noch verändert werden
	double getMovecost();
}
  