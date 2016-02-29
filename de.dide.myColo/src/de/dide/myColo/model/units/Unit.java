package de.dide.myColo.model.units;

import de.dide.myColo.model.units.unitType.IUnitType;

public class Unit {

	private IUnitType unitType;
	private boolean isUnitOnField;
	private int coordX;
	private int coordY;
	private int speedOfUnit;
	private String name;
	
	public Unit(int x, int y, Boolean visible, IUnitType unitType ) {
		coordX = x;
		coordY = y;
		if (visible == null) {
		isUnitOnField = false;
		} else {
			isUnitOnField = visible;
		}
		this.unitType = unitType;
		name = unitType.getName();
	}
	
	public IUnitType getUnitType() {
		return unitType;
	}
	public void setUnitType(IUnitType unitType) {
		this.unitType = unitType;
	}
	public boolean isUnitOnField() {
		return isUnitOnField;
	}
	public void setUnitOnField(boolean isUnitOnField) {
		this.isUnitOnField = isUnitOnField;
	}
	public int getCoordX() {
		return coordX;
	}
	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}
	public int getCoordY() {
		return coordY;
	}
	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
	public int getSpeedOfUnit() {
		return speedOfUnit;
	}
	public String getName() {
		return name;
	}

}
