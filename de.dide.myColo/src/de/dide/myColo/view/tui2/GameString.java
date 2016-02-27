package de.dide.myColo.view.tui2;

import de.dide.myColo.view.tui2.Tui;

public class GameString {

	private static GameString instance;
	private GameCell[][] gameCellMatrix; 
	private StringBuilder[][] gameFieldSB; 
	private static int length_row;
	private static int length_col;
		
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
		for (int col = 0; col < Tui.getGameFieldSize(); col++) {
			for (int row = 0; row < Tui.getGameFieldSize(); row++) {
				gameCellMatrix[row][col] = new GameCell();
			}
		}
	}

	public void paint2() {
		buildRowSBArrayFromGameCells();
		printGameFieldSB();
	}
	
	/**
	 * copy single Rows of each cells sbArray to the correct position of gameFieldSB
	 */
	private void buildRowSBArrayFromGameCells() {
		int nrOfRows = Tui.getCellSize() * Tui.getGameFieldSize();
		gameFieldSB = new StringBuilder[nrOfRows][Tui.getGameFieldSize()];
		
		//loop over a row of cells (if 3*3, then 3 repetitions)
		for (int rowIngameCellMatrix=0; rowIngameCellMatrix < Tui.getGameFieldSize(); rowIngameCellMatrix++) {
			for (int rowOfSingleCell=0; rowOfSingleCell < Tui.getCellSize(); rowOfSingleCell++) {
				for (int col=0; col < Tui.getGameFieldSize(); col++) {
					gameFieldSB [ rowIngameCellMatrix * Tui.getCellSize() + rowOfSingleCell] [col]  
						= gameCellMatrix[rowIngameCellMatrix][col].getCellSBArray()[rowOfSingleCell];
				}
			}		
		}
	}

	/**
	 * prints gameFieldSB  
	 */
	private void printGameFieldSB() {
		StringBuilder tmpLine = new StringBuilder();
		int nrOfRows = GameCell.getCellSize() * Tui.getGameFieldSize();
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
