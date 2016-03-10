package de.dide.myColo.model.units.unitType.impl;

import de.dide.myColo.model.units.unitType.IUnitType;
import de.dide.myColo.view.tui2.VisualConstants;

public class Civilian implements IUnitType {

	int unitNr;
	Type type;
	String name;
	int agility;
	String color;
	
	
	public Civilian(int nr) {
		unitNr = nr;
		name = "Civilian";
		color = VisualConstants.COL_CIVILIAN;
	}

	@Override
	public int getUnitNr() {
		return unitNr;
	}

	public Type getType() {
		return type;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getAgility() {
		return agility;
	}

	@Override
	public String getColor() {
		return color;
	}

	
}
