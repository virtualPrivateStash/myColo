package de.dide.myColo.view.tui2;

import java.util.ArrayList;

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
		buildGameString();
	}
	
	public static GameString getInstance() {
		if (instance == null) {	
			instance = new GameString();
		}
		return instance;
	}

	/**
	 * fills cellArray with n*n new cells
	 */
	private void buildGameString() {
		createAndFillGameCellMatrix();
	}
	
	
	private void createAndFillGameCellMatrix() {
		gameCellMatrix = new GameCell[Tui.getGameFieldSize()][Tui.getGameFieldSize()];
		tuiCellMatrix = new TuiCell[Tui.getGameFieldSize()][Tui.getGameFieldSize()];
		for (int col = 0; col < Tui.getGameFieldSize(); col++) {
			for (int row = 0; row < Tui.getGameFieldSize(); row++) {
				gameCellMatrix[row][col] = new GameCell(row, col, null);
				tuiCellMatrix[row][col] = new TuiCell(gameCellMatrix[row][col]);
//				if (col == 1 && row == 1) {
//					System.out.println(gameCellMatrix[1][1].toString());
//					gameCellMatrix[1][1].addUnit(new Unit(0, 0, true, new Civilian(1)));
			}
		}
//		gameCellMatrix[1][1].addUnit(new Unit(0, 0, true, new Civilian(1)));
		//gameCellMatrix[1][1].ge   repaintCell();
	}
	


	public void paint2() {
		buildRowSBArrayFromGameCells();
		printGameFieldSB();
	}

	/**
	 * copy single Rows of each cells sbArray to the correct position of gameFieldSB
	 */
	private void buildRowSBArrayFromGameCells() {
		int nrOfRows = TuiCell.getCellSize() * Tui.getGameFieldSize();
		gameFieldSB = new StringBuilder[nrOfRows][Tui.getGameFieldSize()];
		
		//loop over a row of cells (if 3*3, then 3 repetitions)
		for (int rowIngameCellMatrix=0; rowIngameCellMatrix < Tui.getGameFieldSize(); rowIngameCellMatrix++) {
			for (int rowOfSingleCell = 0; rowOfSingleCell < TuiCell.getCellSize(); rowOfSingleCell++) {
				for (int col=0; col < Tui.getGameFieldSize(); col++) {
					gameFieldSB [ rowIngameCellMatrix * TuiCell.getCellSize() + rowOfSingleCell] [col]  
						= tuiCellMatrix[rowIngameCellMatrix][col].getCellSBArray()[rowOfSingleCell];
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
