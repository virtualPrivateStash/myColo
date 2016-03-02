package de.dide.myColo.controller;

import java.util.LinkedList;

import de.dide.myColo.model.gameField.impl.GameCell;
import de.dide.myColo.model.terrain.AbstractTerrain;
import de.dide.myColo.model.units.Unit;

public interface Controller{
	String getNameOfTerrainType(AbstractTerrain cell); 
	 	 
	GameCell getGameCell(int row, int col);
	void setGameCell(GameCell cell); 
	
	LinkedList<Unit> getUnitList();
	void setUnitList(LinkedList<Unit> list);
	
	AbstractTerrain getCellType();
	void setCellType(AbstractTerrain terrain);
	
	int getCellIdx_X();
	int getCellIdx_Y();
}