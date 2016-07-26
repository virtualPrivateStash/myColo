package de.dide.myColo.model.game;

import java.util.LinkedList;

import de.dide.myColo.model.gameField.impl.GameCell;
import de.dide.myColo.model.units.Unit;
import de.dide.myColo.view.tui2.Tui;

public class GameState{

	private int year;
	private int STARTYEAR_DEFAULT = 1492;
	private boolean isOnTurn = true;
	private LinkedList<Unit> allUnitsInGame;
	private GameCell[][] gameCellMatrix;


	public GameState(LinkedList<Unit> list, int year) {
		//UNIT LIST SETTEN
		if (list == null) {
			//this.unitList = new LinkedList<Unit>(Arrays.asList(new Unit(0, 0, true, new Civilian(1))));
			this.allUnitsInGame = new LinkedList<Unit>();
		} 
		else {
			this.allUnitsInGame = list;
		}
		//SET YEAR
		if (year < 1 ) {
			year = STARTYEAR_DEFAULT;
		}
		this.year = year;
		//SET ISUNTURN
		isOnTurn = true;
		
		gameCellMatrix = new GameCell[Tui.getGameFieldSize()][Tui.getGameFieldSize()];
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
	

	public LinkedList<Unit> getAllUnitsInGame() {
		return allUnitsInGame;
	}

	public void setAllUnitsInGame(LinkedList<Unit> list) {
		allUnitsInGame = list;
	}
	
	public boolean isOnTurn() {
		return isOnTurn;
	}

	public void setIsOnTurn(boolean onTurn) {
		this.isOnTurn = onTurn;
	}

	public GameCell[][] getGameCellMatrix() {
		return gameCellMatrix;
	}

	public void setGameCellMatrix(GameCell[][] gameCellMatrix) {
		this.gameCellMatrix = gameCellMatrix;
	}

}