package de.dide.myColo.view.tui;

import de.dide.myColo.view.tui.Tui;

public class GameString {

	private static GameString instance;
	private GameCell[][] gameCellMatrix; 
	char[][] gameFieldAsCharMatrix;
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
		for (int y = 0; y < Tui.getGameFieldSize(); y++) {
			for (int x = 0; x < Tui.getGameFieldSize(); x++) {
				gameCellMatrix[x][y] = new GameCell();
			}
		}
	}

	public static void paint() {
		checkDimensionParams();
		//copyAllGameCellsToCharMatrix();
		//String s = buildFinalGameString();
		
		//hier noch string ausgeben aber irgendwie die zeilen geloescht ausversehen. eventuell ein paar zeilen dazuschreiben..
	}

	public void paint2() {
		buildRowSBArrayFromGameCells();
		
	}
	
	/**
	 * 
	 */
	private void buildRowSBArrayFromGameCells() {
		int nrOfRows = Tui.getCellSize() * Tui.getGameFieldSize();
		int nrOfCols = Tui.getGameFieldSize();
		gameFieldSB = new StringBuilder[nrOfCols][Tui.getGameFieldSize()];
		
		//copy single Rows of each cells sbArray into gameFieldSB
		for (int i=0; i < (nrOfRows); i++) {
			for (int j=0; j < nrOfCols; j++) {
				gameFieldSB[i][j] = gameCellMatrix[ (i % Tui.getGameFieldSize()) ][ j ].getCellSBArray()[ (i % Tui.getCellSize()) ];								
			}
		}
		
	}

	private void copyAllGameCellsToCharMatrix() {
		gameFieldAsCharMatrix = new char[length_row][length_col];
		for (int x = 0; x < Tui.getGameFieldSize(); x++) {
			for (int y = 0; y < Tui.getGameFieldSize(); y++) {
				copyOneCellToGameCharMatrix(x,y);
			}
		}		
	}

	private static void checkDimensionParams() {
		length_row = Tui.getGameFieldSize() * Tui.getCellSize();
		length_col = Tui.getGameFieldSize() *  Tui.getCellSize();
	}

	/**
	 * builds the final string that the console prints as visualization of the gameField 
	 * @return gameField as String
	 */
	private String buildFinalGameString() {
		StringBuilder gameSB = new StringBuilder();

		for (int x = 0; x < length_row; x++) {
			for (int y = 0; y < length_col; y++) {
				char fillChar = gameFieldAsCharMatrix[x][y];
					//wenn char ='\0' wird leider nichts appended bzw. println gibt nichts mehr aus ( entspricht \0 dem newline-zeichen??).
					//daher muss fillChar mit Leerzeichen gefüllt werden.
					//sonst wird die Zeile nur bis zum ersten char mit '\0' ausgegeben.
				if (fillChar == '\0') {
					fillChar = ' ';
				}
				//BUILD COLOR STRING				
				gameSB.append(fillChar);
				//bei Erreichen des Zeilenendes "\n" anhängen für Zeilenumbruch des Ausgabestrings
				if (y == (length_col - 1) ) {
					gameSB.append("\n");
				} 
			}
		}		
		return gameSB.toString();
	}

	/**
	 * copy chars of one gameCell at correct position in the gameCharMatrix
	 * @param cellIdx_x
	 * @param cellIdx_y
	 */
	private void copyOneCellToGameCharMatrix(int cellIdx_x, int cellIdx_y) {
		char[][] tmpArray = gameCellMatrix[cellIdx_x][cellIdx_y].getCell();
		for (int x = 0; x < Tui.getCellSize(); x++) {
			for (int y = 0; y < Tui.getCellSize(); y++) {
				gameFieldAsCharMatrix[(cellIdx_x * Tui.getCellSize() ) + x ][(cellIdx_y * Tui.getCellSize() ) + y] = tmpArray[x][y];   
			}
		}
	}	

}
