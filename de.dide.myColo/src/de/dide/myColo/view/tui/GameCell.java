package de.dide.myColo.view.tui;

import de.dide.myColo.controller.Controller;
import de.dide.myColo.controller.impl.MainController;
import de.dide.myColo.model.terrain.AbstractTerrain;
import de.dide.myColo.model.terrain.impl.Water;

public class GameCell {

	Controller controller = new MainController();
	private char[][] cellCharMatrix;
	public static final char BORDERCHAR = '+';
	public static final int BORDERSIZE = 1;
	public static final int INFOAREASIZE = Tui.getCellSize() - 2 * BORDERSIZE	;
	private  AbstractTerrain cellType;
	private  StringBuilder[] infoSBArray;
	private  StringBuilder[] cellSBArray;
	
	public GameCell() {
		cellCharMatrix = new char[Tui.getCellSize()][Tui.getCellSize()];
		cellType = new Water();
//		fillCell();
//		fillCellWithSymbolChars();
		paintCellViaColoredStringArray();
		
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
				cellCharMatrix[idxOfLine][i] = BORDERCHAR;
			}
		} 
		else {
			for (int i = 0; i < Tui.getCellSize(); i++) {
				cellCharMatrix[i][idxOfLine] = BORDERCHAR;
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
	 * @return the bORDERCHAR
	 */
	public static char getBorderChar() {
		return BORDERCHAR;
	}
	
	
	/**
	 * @return the cell
	 */
	public char[][] getCell() {
		return cellCharMatrix;
	}

	public StringBuilder[] getCellSBArray() {
		return cellSBArray;
	}

	private void paintCellViaColoredStringArray() {
		buildInfoArea();
		integrateInfoAreaAndBorderChars();
	}	
	
	/**
	 * takes infoArea[] and fills the remaining chars before and after with Borderchar in cellType-Color
	 */
	private StringBuilder[] integrateInfoAreaAndBorderChars() {
		cellSBArray = new StringBuilder[Tui.getCellSize()];
		
		//fill one line with borderChars as copy-paste material
		StringBuilder borderLine= new StringBuilder(Tui.getCellSize());
		for (int i=0; i< Tui.getCellSize(); i++) {
			borderLine.append(BORDERCHAR);
		}
		
		////lines with space covered by infoArray		
		int coveredLines = infoSBArray.length;
		StringBuilder preAndPost = new StringBuilder();
		for (int i = 0; i < BORDERSIZE; i++) {
			preAndPost.append(BORDERCHAR);
		}
		for (int i=0; i<coveredLines; i++) {
			//System.out.println(infoSBArray[0].toString());
			//cellSBArray[BORDERSIZE + i].append(preAndPost.toString() + infoSBArray[i].toString() + preAndPost.toString());
			cellSBArray[BORDERSIZE + i] = new StringBuilder(Tui.getCellSize());
			cellSBArray[BORDERSIZE + i].append("asdfsd");
		}
		
		////add non-covered lines (borderCharLines)
		for (int i = 0; i < BORDERSIZE; i++) {
			cellSBArray[i] = new StringBuilder(Tui.getCellSize());
			cellSBArray[i].append(borderLine.toString());
			
			cellSBArray[Tui.getCellSize() -i-1] = new StringBuilder("asdfsd");
		}
		return cellSBArray;
	}

	private StringBuilder[] buildInfoArea() {
		infoSBArray = new StringBuilder[INFOAREASIZE];
		String type = controller.getNameOfTerrainType(cellType);

		StringBuilder row1 = new StringBuilder();
		row1.append("Terrain: " +type);
		row1 = VisualConstants.getColoredString(VisualConstants.colorName.DEFAULT, row1);
		for (int i = 0; i < infoSBArray.length; i++) {
			infoSBArray[i] = row1;
		}
		
		return infoSBArray;
	}
	
}