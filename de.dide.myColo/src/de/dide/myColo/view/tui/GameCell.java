package de.dide.myColo.view.tui;

public class GameCell {

	private char[][] cell;
	
	public GameCell() {
		cell = new char[Tui.getCellSize()][Tui.getCellSize()];
		fillCell();
	}

	private void fillCell() {
		for (int y = 0; y < getCell().length; y++) {
			for (int x = 0; x < getCell().length; x++) {
				getCell()[x][y] = 'X';
			}
		}
	}

	/**
	 * @return the cell
	 */
	public char[][] getCell() {
		return cell;
	}
	
}