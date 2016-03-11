package de.dide.myColo.view.tui2;

import java.util.ArrayList;
import java.util.LinkedList;

import de.dide.myColo.app.ColGame;
import de.dide.myColo.model.game.GameState;
import de.dide.myColo.model.gameField.impl.GameCell;
import de.dide.myColo.model.units.Unit;
import de.dide.myColo.model.units.unitType.impl.Civilian;
import de.dide.myColo.view.tui2.Tui;

public class GameString {

	private static GameString instance;
	private GameCell[][] gameCellMatrix; 
	private StringBuilder[][] gameFieldSB; 
	private GameState gameState;
	private static TuiCell[][] tuiCellMatrix;
			
	private GameString() {
		gameState = ColGame.getGameState();
		gameCellMatrix = new GameCell[Tui.getGameFieldSize()][Tui.getGameFieldSize()];
		this.makeNewTuiCellMatrix();
	}
	
	public static GameString getInstance() {
		if (instance == null) {	
			instance = new GameString();
		}
		return instance;
	}
	
	private LinkedList<Unit> getUnitListFromCell(int row, int col) {
		LinkedList<Unit> allCellsUnits = new LinkedList<Unit>();
		LinkedList<Unit> oneCellsUnits = new LinkedList<Unit>();
		allCellsUnits = gameState.getAllUnitsInGame();
		
		//check unitList for units with position matching the given cell coordinates
		for (int i=0; i < allCellsUnits.size(); i++) {
			Unit tmpUnit = allCellsUnits.get(i);
			if (tmpUnit.getCoordX() == row ) {
				if (tmpUnit.getCoordY() == col ) {
					oneCellsUnits.add(tmpUnit);
				}
			}
		}
		return oneCellsUnits;
	}

	public void paint() {
		makeNewTuiCellMatrix();
		buildRowSBArrayFromGameCells();
		printGameFieldSB();
	}
	
	private void makeNewTuiCellMatrix() {
		tuiCellMatrix = new TuiCell[Tui.getGameFieldSize()][Tui.getGameFieldSize()];
		for (int col = 0; col < Tui.getGameFieldSize(); col++) {
			for (int row = 0; row < Tui.getGameFieldSize(); row++) {
				LinkedList<Unit> list = getUnitListFromCell(row, col);
				gameCellMatrix[row][col] = new GameCell(row, col, list);
				tuiCellMatrix[row][col] = new TuiCell(gameCellMatrix[row][col], list);
			}
		}
	}

	/**
	 * copy single Rows of each cells sbArray to the correct position of gameFieldSB
	 */
	private void buildRowSBArrayFromGameCells() {
		int nrOfRows = TuiCell.getCellSize() * Tui.getGameFieldSize();
		gameFieldSB = new StringBuilder[nrOfRows][Tui.getGameFieldSize()];
		
		//loop over a row of cells (if 3*3, then 3 repetitions)
		for (int rowInGameCellMatrix=0; rowInGameCellMatrix < Tui.getGameFieldSize(); rowInGameCellMatrix++) {
			for (int rowOfSingleCell = 0; rowOfSingleCell < TuiCell.getCellSize(); rowOfSingleCell++) {
				for (int col=0; col < Tui.getGameFieldSize(); col++) {
					gameFieldSB [ rowInGameCellMatrix * TuiCell.getCellSize() + rowOfSingleCell] [col]  
						= tuiCellMatrix[rowInGameCellMatrix][col].getCellSBArray()[rowOfSingleCell];
				}
			}		
		}
	}

	/**
	 * prints gameFieldSB  
	 */
	private void printGameFieldSB() {
		StringBuilder tmpLine = new StringBuilder();
		int nrOfRows = TuiCell.getCellSize() * Tui.getGameFieldSize();
		int nrOfCols = Tui.getGameFieldSize();
		
		for (int row=0; row < nrOfRows; row++) {
			for (int col=0; col < Tui.getGameFieldSize(); col++) {
				tmpLine.append(gameFieldSB[row][col]);
			}
			tmpLine.append("\n");
		}
		System.out.println(tmpLine);
	}
}
