package de.dide.myColo.view.tui;

import de.dide.myColo.controller.Controller;
import de.dide.myColo.controller.impl.MainController;
import de.dide.myColo.model.terrain.AbstractTerrain;
import de.dide.myColo.model.terrain.impl.Water;
import de.dide.myColo.view.tui2.Tui;

public class GameCell {

	Controller controller = new MainController();
	private char[][] cellCharMatrix;
	public static final char BORDERCHAR = '+';
	public static final int BORDERSIZE = 1;
	private static Integer cellSize = null;
	private static final int CELLSIZE_MIN = 8;
	private static final int CELLSIZE_MAX = 40;
	public static final int INFOAREASIZE_MIN = 6;
	public static final int INFOAREASIZE_MAX = 25;
	public static final double INFOAREASIZE_FAKTOR = 0.7;
	public static Integer infoAreaSize = null;
	private static Integer charsStillEmpty = null; 
	private  AbstractTerrain cellType = null;
	private  StringBuilder[] infoSBArray = null;
	private  StringBuilder[] cellSBArray = null;
	
	public GameCell() {
		initializeSizeVariables();
		cellCharMatrix = new char[cellSize][cellSize];
		cellType = new Water();
//		fillCell();
//		fillCellWithSymbolChars();
		paintCellViaColoredStringArray();
	}

	private static void initializeSizeVariables() {
		
		if (cellSize == null || infoAreaSize == null || charsStillEmpty == null) {

			if (cellSize < CELLSIZE_MIN || cellSize > CELLSIZE_MAX || cellSize <= 0) {
				cellSize = CELLSIZE_MIN;
			}
			infoAreaSize = (int) (cellSize * INFOAREASIZE_FAKTOR);
							
			if (infoAreaSize < INFOAREASIZE_MIN) {
				infoAreaSize = INFOAREASIZE_MIN;
			}
			
			charsStillEmpty = cellSize - 2 * BORDERSIZE - infoAreaSize;
			if (charsStillEmpty <= 0) {
				charsStillEmpty = 0;
			}
		}
	}

	private void fillCellWithSymbolChars() {
		writeBorderChars();
	}

	private void writeBorderChars() {

		for (int i = 0; i < BORDERSIZE ; i++) {
			//copying border with i distance top and bottom
			fillWholeLine(i, true);
			fillWholeLine( cellSize -1 -i , true);
			//copying border with i distance from left and right
			fillWholeLine(i, false);
			fillWholeLine( cellSize - i -1 , false);
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
			for (int i = 0; i < cellSize; i++) {
				cellCharMatrix[idxOfLine][i] = BORDERCHAR;
			}
		} 
		else {
			for (int i = 0; i < cellSize; i++) {
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
	
	public static Integer getCellSize() {
		if (cellSize == null) {
			initializeSizeVariables();
		}
		return cellSize;
	}



	public static Integer getInfoAreaSize() {
		return infoAreaSize;
	}


	private void paintCellViaColoredStringArray() {
		buildInfoArea();
		integrateInfoAreaAndBorderChars();
	}	
	
	/**
	 * takes infoArea[] and fills the remaining chars before and after with Borderchar in cellType-Color
	 */
	private StringBuilder[] integrateInfoAreaAndBorderChars() {
		cellSBArray = new StringBuilder[cellSize];
		
		//fill one line with borderChars as copy-paste material
		StringBuilder borderLine= new StringBuilder(cellSize);
		for (int i=0; i< cellSize; i++) {
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
			cellSBArray[BORDERSIZE + i] = new StringBuilder(cellSize);
			cellSBArray[BORDERSIZE + i].append("asdfsd");
		}
		
		////add non-covered lines (borderCharLines)
		for (int i = 0; i < BORDERSIZE; i++) {
			cellSBArray[i] = new StringBuilder(cellSize);
			cellSBArray[i].append(borderLine.toString());
			
			cellSBArray[cellSize -i-1] = new StringBuilder("asdfsd");
		}
		return cellSBArray;
	}

	private StringBuilder[] buildInfoArea() {
		infoSBArray = new StringBuilder[infoAreaSize];
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