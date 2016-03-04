package de.dide.myColo.model.game;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import de.dide.myColo.model.units.Unit;
import de.dide.myColo.model.units.unitType.impl.Civilian;

public class GameState {

	private int year;
	private boolean isOnTurn = true;
	private Unit activeUnit;
	private LinkedList<Unit> unitList;

	
	public GameState(LinkedList<Unit> unitList, int year) {
		if (unitList == null) {
			this.unitList = new LinkedList<Unit>(Arrays.asList(new Unit(0, 0, true, new Civilian(1))));
		}
		if (year < 1 ) {
			year = 1492;
		}
		this.year = year;
		this.unitList = unitList;
		isOnTurn = true;
		
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void incrementYear() {
		year++;
	}
	
	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	boolean finished = false;
	
	public GameState(){
		year = 1492;
//		unitList = new LinkedList<Unit>(); 
//		getPreparedUnitList();
	}
	
	public LinkedList<Unit> getUnitList() {
		return unitList;
	}

	public void setUnitList(LinkedList<Unit> list) {
		unitList = list;
	}
	
	public boolean isOnTurn() {
		return isOnTurn;
	}

	public void setIsOnTurn(boolean onTurn) {
		this.isOnTurn = onTurn;
	}	
}
