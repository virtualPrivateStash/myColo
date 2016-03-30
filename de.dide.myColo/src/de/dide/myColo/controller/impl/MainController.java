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
	
	//////////////////////////////
	//FUNCTIONS FOR MOVING UNITS//
	//////////////////////////////
	public void moveUnit_Left(Unit unit) {
		moveUnit(unit, LEFT, null);
	}	

	public void moveUnit_Right(Unit unit) {
		moveUnit(unit, RIGHT, null);
	}
	
	public void moveUnit_Up(Unit unit) {
		moveUnit(unit, UP, null);
	}
	
	public void moveUnit_Down(Unit unit) {
		moveUnit(unit, DOWN, null);
	}
	
	public void moveUnit_Up_Left(Unit unit) {
		moveUnit(unit, UP, LEFT);	}
	
	public void moveUnit_Up_Right(Unit unit) {
		moveUnit(unit, UP, RIGHT);
	}
	
	public void moveUnit_Down_Left(Unit unit) {
		moveUnit(unit, DOWN, LEFT);
	}
	
	public void moveUnit_Down_Right(Unit unit) {
		moveUnit(unit, DOWN, RIGHT);
	}
	
		
	private void moveUnit(Unit unit, String direction1, String direction2) {
		boolean dontDecr = true;
		boolean positionChanged = false;
		boolean twoDirections =  false;
		boolean makeMove = false;
		
		//check if one or two directions were given
		if (direction2 != null) {
			twoDirections = true;
		}
		
		if (!isMovePossible(unit, direction1)) {
			makeMove = false;
		} 
		else {
			if (!twoDirections) {
				makeMove = true;
			} else {
				if (isMovePossible(unit, direction2)) {
					makeMove = true;
				}
			}
		}
		
		//do the actual moves in this if clause
		if (makeMove) {
	
			//STEP 1
			moveUnitOneStep(unit, direction1);
	
			//STEP 2 IF GIVEN
			if (twoDirections) {
				moveUnitOneStep(unit, direction2);
			}
	
			//UPDATE MODELL AND NOTIFY OBSERVERS
			unit.decrMovesRemaining();
			this.notifyObservers();					
		}
	}
			
			
	private boolean isMovePossible(Unit unit, String direction) {
		boolean possible = false;
		switch (direction) {
			case LEFT:
				if (unit.getCoordY() > 0) {
				possible = true; 	
				}			
				break;
			case RIGHT:
				if (unit.getCoordY() < Tui.getGameFieldSize() - 1) {
				possible = true;
				}			
				break;
			case DOWN:
				if (unit.getCoordX() < Tui.getGameFieldSize() - 1) {
				possible = true;
				}			
				break;
			case UP:
				if (unit.getCoordX() > 0) {
				possible = true;
				}			
		}
		return possible;
	}
	
	
	private void moveUnitOneStep(Unit unit, String direction) {
		switch (direction) {
			case LEFT:
				unit.setCoordY(unit.getCoordY() - 1);	
				break;
			case RIGHT:
				unit.setCoordY(unit.getCoordY() + 1);	
				break;
			case DOWN:
				unit.setCoordX(unit.getCoordX() + 1);
				break;
			case UP:
				unit.setCoordX(unit.getCoordX() - 1);
				break;
		}
	}
	
			
	
//	public void moveUnit_Up_Left(Unit unit) {
//		moveUnit(unit, UP, true);
//		moveUnit(unit, LEFT, true);
//		unit.decrMovesRemaining();
//	}
//
//	public void moveUnit_Up_Right(Unit unit) {
//		moveUnit(unit, UP, true);
//		moveUnit(unit, RIGHT, true);
//		unit.decrMovesRemaining();
//	}
//	
//	public void moveUnit_Down_Left(Unit unit) {
//		moveUnit(unit, DOWN, true);
//		moveUnit(unit, LEFT, true);
//		unit.decrMovesRemaining();
//	}
//
//	public void moveUnit_Down_Right(Unit unit) {
//		moveUnit(unit, DOWN, true);
//		moveUnit(unit, RIGHT, true);
//		unit.decrMovesRemaining();
//	}
//	
//	private boolean moveUnit(Unit unit, String direction, boolean dontDecr) {
//		boolean positionChanged = false;
//		switch (direction) {
//			case LEFT:
//				if (unit.getCoordY() > 0) {
//				unit.setCoordY(unit.getCoordY() - 1);	
//				positionChanged = true;
//				}			
//				break;
//			case RIGHT:
//				if (unit.getCoordY() < Tui.getGameFieldSize() - 1) {
//				unit.setCoordY(unit.getCoordY() + 1);	
//				positionChanged = true;
//				}			
//				break;
//			case DOWN:
//				if (unit.getCoordX() < Tui.getGameFieldSize() - 1) {
//				unit.setCoordX(unit.getCoordX() + 1);
//				positionChanged = true;
//				}			
//				break;
//			case UP:
//				if (unit.getCoordX() > 0) {
//				unit.setCoordX(unit.getCoordX() - 1);
//				positionChanged = true;
//				}			
//				break;
//		}
//		if ( dontDecr == false) {
//			unit.decrMovesRemaining();
//		}
//		this.notifyObservers();
//		return positionChanged;
//	}
//
//	
	
	
	

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
	}
	
	
	
	

}