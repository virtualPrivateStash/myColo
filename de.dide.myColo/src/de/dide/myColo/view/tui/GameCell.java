package de.dide.myColo.view.tui;

public class GameCell {

	private char[][] cell;
	public static final char BORDERCHAR = '+';
	public static final int BORDERSIZE = 1;
	
	public GameCell() {
		cell = new char[Tui.getCellSize()][Tui.getCellSize()];
//		fillCell();
		fillCellWithSymbolChars();
		
	}

	private void fillCellWithSymbolChars() {
		writeBorderChars();
	}

	private void writeBorderChars() {

		for (int i = 0; i < BORDERSIZE ; i++) {
			//copying border with i distance top and bottom
			fillWholeLine(i, true);
			fillWholeLine( Tui.getCellSize() -1 -i , true);
			//copying border with i distance from left and right
			fillWholeLine(i, false);
			fillWholeLine( Tui.getCellSize() - i -1   , false);
		}
	}
		

	 /**
	  * fills one whole row or column of the char[][]; 
	  * 
	  * @param idxOfLine - contains the index of the row or column that is to be printed. 
	  * @param row - boolean with true= line is a row, false= line is a column.
	  */
	private void fillWholeLine(int idxOfLine, boolean row) {
		if (row) {
			for (int i = 0; i < Tui.getCellSize(); i++) {
				cell[idxOfLine][i] = BORDERCHAR;
			}
		} 
		else {
			for (int i = 0; i < Tui.getCellSize(); i++) {
				cell[i][idxOfLine] = BORDERCHAR;
			}
		}
	}
	
	/**
	 * fills whole cell with the same char 
	 */
	private void fillCell() {
		for (int y = 0; y < getCell().length; y++) {
			for (int x = 0; x < getCell().length; x++) {
				getCell()[x][y] = getBorderChar();
			}
		}
	}

	/**
	 * @return the cell
	 */
	public char[][] getCell() {
		return cell;
	}

	/**
	 * @return the bORDERCHAR
	 */
	public static char getBorderChar() {
		return BORDERCHAR;
	}
	
}