package de.dide.myColo.model.terrain;

public abstract class AbstractTerrain {

	private String nameOfTerrainType;
	private final int MOVECOST_STD = 1;
	private int movecost = MOVECOST_STD;
	private boolean isFree = true;
	private boolean isLand = true;
	
	public AbstractTerrain() {
				
	}
	
	public int getMoveCost() {
		return getMovecost();
	}

	public boolean isLand() {
		return isLand;
	}
	
	public void setIsLand(boolean isLand) {
		this.isLand = isLand;
	}

	public boolean isFree() {
		return true;
	}

	public int getMovecost() {
		return movecost;
	}

	public void setMovecost(int movecost) {
		this.movecost = movecost;
	}

	public String getNameOfTerrainType() {
		return nameOfTerrainType;
	}

	public void setNameOfTerrainType(String name) {
    	this.nameOfTerrainType = name;
	}
}
