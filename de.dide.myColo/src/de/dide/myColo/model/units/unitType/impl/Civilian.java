package de.dide.myColo.model.units.unitType.impl;

import de.dide.myColo.model.units.unitType.IUnitType;

public class Civilian implements IUnitType {

	int unitNr;
	Type type;
	String name;
	int agility;	
	
	
	public Civilian(int nr) {
		unitNr = nr;
		name = "Civilian";
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

	
}
