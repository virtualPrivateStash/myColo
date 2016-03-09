package de.dide.myColo.model.gameField.impl;

import java.util.LinkedList;

import de.dide.myColo.model.terrain.AbstractTerrain;
import de.dide.myColo.model.terrain.impl.Water;
import de.dide.myColo.model.units.Unit;

public class GameCell {

	private  AbstractTerrain cellType = null;
	private final int coordX;
	private final int coordY;
	private LinkedList<Unit> unitList;
	
	public GameCell(int idx_row, int idx_col, LinkedList<Unit> list) {
		cellType = new Water();
		//type bebauung = road, colony, fort, ...
		//List<TransportUnit> trnspList
		//List<Military> befestigung
		//boolean canBeTravelled
				
		coordX = idx_row;
		coordY = idx_col;
		LinkedList<Unit> unitList = list;
//		unitList.add(new Unit(0, 0, true, new Civilian(1)));
	}
	
	public void addUnit(Unit addMe) {
		if (unitList == null) {
			unitList = new LinkedList<Unit>();
		}
		unitList.add(addMe);
	}


	public int getCellIdx_X() {
		
		return coordX;
	}

	public int getCellIdx_Y() {

		return coordY;
	}

	public AbstractTerrain getCellType() {
		return cellType;
	}

	public void setCellType(AbstractTerrain cellType) {
		this.cellType = cellType;
	}

}