package de.dide.myColo.controller.impl;

import java.util.LinkedList;

import de.dide.myColo.controller.Controller;
import de.dide.myColo.model.gameField.impl.GameCell;
import de.dide.myColo.model.terrain.AbstractTerrain;
import de.dide.myColo.model.units.Unit;
import de.dide.myColo.util.observer.Observable;

public class MainController extends Observable implements Controller{

	public MainController() {}
	
	public String getNameOfTerrainType(AbstractTerrain cell) {
		//return(cell.getNameOfTerrainType());
		return "dummyString";
	}

	public void moveUnit_Left() {
		//schreibe units coordinaten für x = x-1 
	System.out.println("leeeefttt!");
	}
//	public void moveUnit_Up(){}
//	public void moveUnit_Right(){}
//	public void moveUnit_Down(){}

	@Override
	public GameCell getGameCell(int row, int col) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGameCell(GameCell cell) {
	}

	@Override
	public LinkedList<Unit> getUnitList() {
		
		return null;
	}

	@Override
	public void setUnitList(LinkedList<Unit> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AbstractTerrain getCellType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCellType(AbstractTerrain terrain) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCellIdx_X() {
		
		return 0;
	}

	@Override
	public int getCellIdx_Y() {

		return 0;
	}
	
//	public void startGame(){} //start + neustart
//	public void exitGame(){}

	

	

}