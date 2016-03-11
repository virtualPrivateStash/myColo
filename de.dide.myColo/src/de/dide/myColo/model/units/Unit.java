package de.dide.myColo.model.units;

import de.dide.myColo.model.units.unitType.IUnitType;

public class Unit {

	private IUnitType unitType;
	private boolean isUnitOnField;
	private int coordX;
	private int coordY;
	private int movesPerRound;
	private int movesRemaining;
	private String name;
	private boolean toBeProcessed;
	
	public Unit(int x, int y, Boolean visible, IUnitType unitType, int moves) {
		coordX = x;
		coordY = y;
		if (visible == null) {
		isUnitOnField = false;
		} else {
			isUnitOnField = visible;
		}
		this.unitType = unitType;
		name = unitType.getName();
		toBeProcessed = true;
		movesRemaining = moves;
		movesPerRound = moves; 
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
		return movesPerRound;
	}
	public String getName() {
		return name;
	}
	
	public boolean isToBeProcessed() {
		return toBeProcessed;
	}

	public void setToBeProcessed(boolean toBeProcessed) {
		this.toBeProcessed = toBeProcessed;
	}

	public int getMovesPerRound() {
		return movesPerRound;
	}

	public void setMovesPerRound(int movesPerRound) {
		this.movesPerRound = movesPerRound;
	}

	public int getMovesRemaining() {
		return movesRemaining;
	}

	public void decrMovesLeft() {
		--movesRemaining ;
		if (movesRemaining<= 0) {
			this.setToBeProcessed(false);
		}
	}

}