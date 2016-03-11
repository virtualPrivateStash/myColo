package de.dide.myColo.controller.impl;

import java.util.LinkedList;

import de.dide.myColo.controller.Controller;
import de.dide.myColo.model.game.GameState;
import de.dide.myColo.model.gameField.impl.GameCell;
import de.dide.myColo.model.terrain.AbstractTerrain;
import de.dide.myColo.model.units.Unit;
import de.dide.myColo.util.observer.Observable;
import de.dide.myColo.view.tui2.Tui;

public class MainController extends Observable implements Controller{

	private final String LEFT = "LEFT";
	private final String RIGHT = "RIGHT";
	private final String UP = "UP";
	private final String DOWN = "DOWN";
	
	public MainController() {}

	
	public String getNameOfTerrainType(AbstractTerrain cell) {
		//return(cell.getNameOfTerrainType());
		return "dummyString";
	} 

	public void moveUnit_Left(Unit unit) {
		moveUnit(unit, LEFT);
	}

	public void moveUnit_Right(Unit unit) {
		moveUnit(unit, RIGHT);
	}
	
	public void moveUnit_Up(Unit unit) {
		moveUnit(unit, UP);
	}
	
	public void moveUnit_Down(Unit unit) {
		moveUnit(unit, DOWN);
	}

	private void moveUnit(Unit unit, String direction) {

		switch (direction) {
			case LEFT:
				if (unit.getCoordY() > 0) {
				unit.setCoordY(unit.getCoordY() - 1);	
				unit.decrMovesRemaining();
				}			
				break;
			case RIGHT:
				if (unit.getCoordY() < Tui.getGameFieldSize() - 1) {
				unit.setCoordY(unit.getCoordY() + 1);	
				unit.decrMovesRemaining();
				}			
				break;
			case DOWN:
				if (unit.getCoordX() < Tui.getGameFieldSize() - 1) {
				unit.setCoordX(unit.getCoordX() + 1);	
				unit.decrMovesRemaining();
				}			
				break;
			case UP:
				if (unit.getCoordX() > 0) {
				unit.setCoordX(unit.getCoordX() - 1);	
				unit.decrMovesRemaining();
				}			
				break;
		}
		this.notifyObservers();
	}


	@Override
	public GameCell getGameCell(int row, int col) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGameCell(GameCell cell) {
		// TODO Auto-generated method stub
	}

	@Override
	public LinkedList<Unit> getUnitList() {
		// TODO Auto-generated method stub		
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
		// TODO Auto-generated method stub		
		return 0;
	}

	@Override
	public int getCellIdx_Y() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void initializeVarsForNewYear(GameState gameState) {
		LinkedList<Unit> unitList = gameState.getAllUnitsInGame();
		//for all Units of Game set ToBeProcessed=true
		for (int i=0; i < gameState.getAllUnitsInGame().size(); i++) {
			unitList.get(i).setToBeProcessed(true);
		}
		
		//GameCells aktualisieren mit unitlist usw
		gameState.setAllUnitsInGame(unitList);
		//notifyObservers();
	}
	
	
	
	

}