package de.dide.myColo.view.tui;

import de.dide.myColo.view.tui.Tui;

public class GameString {

	private static GameString instance;
	private GameCell[][] cellArray; 
	char[][] gameStringInChars;
		
	private GameString() {
		buildGameString();
		paint();
	}
	
	private void buildGameString() {
		cellArray = new GameCell[Tui.getGAMEFIELDSIZE()][Tui.getGAMEFIELDSIZE()];
		for (int y = 0; y < Tui.getGAMEFIELDSIZE(); y++) {
			for (int x = 0; x < Tui.getGAMEFIELDSIZE(); x++) {
				cellArray[x][y] = new GameCell();
			}
		}
	}

	public static GameString getInstance() {
		if (instance == null) {
			instance = new GameString();
		}
		return instance;
	}

	public void paint() {
		int gameFieldLen_X = Tui.getGAMEFIELDSIZE() * Tui.getCellSize();
		int gameFieldLen_Y = Tui.getGAMEFIELDSIZE() *  Tui.getCellSize();
		gameStringInChars = new char[gameFieldLen_X][gameFieldLen_Y];

		//copy all chars from each single gameCell into gameStringInChars 
		for (int x = 0; x < Tui.getGAMEFIELDSIZE(); x++) {
			for (int y = 0; y < Tui.getGAMEFIELDSIZE(); y++) {
				oneCellToCharArray(x,y); 
			}
		}
		
		StringBuilder gameSB = new StringBuilder();
	
		for (int x = 0; x < gameFieldLen_X; x++) {
			for (int y = 0; y < gameFieldLen_Y; y++) {
				gameSB.append(gameStringInChars[x][y]);
				if (y == (gameFieldLen_Y - 1) ) {
					gameSB.append("\n");
				} 
			}
		}
		
			
		System.out.println(gameSB.toString());
	
		
	}

	private void oneCellToCharArray(int cellIdx_x, int cellIdx_y) {
		
		char[][] tmpArray = cellArray[cellIdx_x][cellIdx_y].getCell();
		
		for (int x = 0; x < Tui.getCellSize(); x++) {
			for (int y = 0; y < Tui.getCellSize(); y++) {
				gameStringInChars[(cellIdx_x * Tui.getCellSize() ) + x ][(cellIdx_y * Tui.getCellSize() ) + y] = tmpArray[x][y];   
			}
		}
	}	
	
	
}
