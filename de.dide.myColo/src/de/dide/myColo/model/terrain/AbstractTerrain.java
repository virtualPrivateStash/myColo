package de.dide.myColo.model.terrain;

import javax.swing.ImageIcon;

public abstract class AbstractTerrain implements ITerrain{

	protected String nameOfTerrainType;
	protected final double MOVECOST_STD = 1;
	protected double movecost;
	protected boolean isFree = true;
	protected boolean isLand;
	protected final String terrainColor = null;
	protected ImageIcon backgroundImage;
	
	public ImageIcon getBackgroundImage() {
		return backgroundImage;
	}

	public AbstractTerrain() {
		movecost = MOVECOST_STD;
		isFree = true;
		
	}  

	public double getMoveCost() {
		return getMovecost();
	}

	public boolean isLand() {
		return isLand;
	}
	
	public boolean isFree() {
		return true;
	}

	@Override
	public double getMovecost() {
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

	public String getTerrainColor() {
		return terrainColor;
	}
}
