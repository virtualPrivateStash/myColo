package de.dide.myColo.view.tui2;

import javax.swing.border.Border;

import de.dide.myColo.controller.Controller;
import de.dide.myColo.controller.impl.MainController;
import de.dide.myColo.model.terrain.AbstractTerrain;
import de.dide.myColo.model.terrain.impl.Water;

public class GameCell {

	Controller controller = new MainController();
	private static final int CELLSIZE_MINIMUM = 12;
	private char[][] cellCharMatrix;
	private static final int BORDERSIZE = 1;
	private static final int INFOAREASIZE_MIN = 6;
	private static final int INFOAREASIZE_MAX = 30;
	private static final int CELLSIZE_MIN = 8;
	private static int FILLINLINES = 0;
	private static int INFOAREASIZE = INFOAREASIZE_MIN;
	private static int cellSize = Tui.getCellSize();
	
	public static final char BORDERCHAR = '+';
	public static final char FILLINCHAR = '-';
	
	private  AbstractTerrain cellType;
	private  StringBuilder[] infoSBArray;
	private  StringBuilder[] cellSBArray;
	
	public GameCell() {
		calculateSizes();
		cellCharMatrix = new char[getCellSize()][getCellSize()];
		cellType = new Water();
//		fillCell();
//		fillCellWithSymbolChars();
		paintCellViaColoredStringArray();
		
	}

	private static void calculateSizes() {
		
		cellSize = getCellSize();
		
		int factorized = (int) (getCellSize() * 0.7);
		INFOAREASIZE = factorized;
		if (INFOAREASIZE_MIN > factorized) {
			INFOAREASIZE = INFOAREASIZE_MIN;
		}
		
		
		FILLINLINES = getCellSize() - INFOAREASIZE - 2 * BORDERSIZE;
		
		
				
		//cellSize = ...		
	}

	private void fillCellWithSymbolChars() {
		writeBorderChars();
	}

	private void writeBorderChars() {

		for (int i = 0; i < BORDERSIZE ; i++) {
			//copying border with i distance top and bottom
			fillWholeLine(i, true);
			fillWholeLine( getCellSize() -1 -i , true);
			//copying border with i distance from left and right
			fillWholeLine(i, false);
			fillWholeLine( getCellSize() - i -1   , false);
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
			for (int i = 0; i < getCellSize(); i++) {
				cellCharMatrix[idxOfLine][i] = BORDERCHAR;
			}
		} 
		else {
			for (int i = 0; i < getCellSize(); i++) {
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
		cellSBArray = new StringBuilder[getCellSize()];
		
		//fill one line with borderChars as copy-paste material
		StringBuilder borderLine= new StringBuilder(getCellSize());
		for (int i=0; i< getCellSize(); i++) {
			borderLine.append(BORDERCHAR);
		}
		
		////lines with space covered by infoArray		
		int coveredLines = infoSBArray.length;
		
		int charsToFill = getCellSize() - (2 * BORDERSIZE) - INFOAREASIZE;
	
		StringBuilder pre = new StringBuilder();
		StringBuilder post = new StringBuilder();

		for (int i = 0; i < (charsToFill / 2); i++) {
			pre.append(FILLINCHAR);
			post.append(FILLINCHAR);
		}		
		
		for (int i = 0; i < BORDERSIZE; i++) {
			post.append(BORDERCHAR);
			pre.insert(0, BORDERCHAR);
		}
		
		
		for (int i=0; i<coveredLines; i++) {
			cellSBArray[BORDERSIZE + i] = new StringBuilder(getCellSize());
			cellSBArray[BORDERSIZE + i].append(pre.toString() + infoSBArray[i].toString() + post.toString());
		}
		
		//fill empty rows after...
		int rowsWithNull = getCellSize() - 2*BORDERSIZE - infoSBArray.length;
		
		if (rowsWithNull > 0) {
			for (int i=0; i < rowsWithNull; i++) {
				StringBuilder fillLine = new StringBuilder();
				
				int fillWithFillChar = getCellSize() - 2 * BORDERSIZE;
				for (int j=0; j< (fillWithFillChar); j++) {
					fillLine.append(FILLINCHAR);
				}
				for (int k=0; k< (BORDERSIZE); k++) {
					fillLine.insert(k, BORDERCHAR);
					fillLine.append(BORDERCHAR);
				}
				//rowsWithNull einfügen in cellSBArray
				int tmpIndex = BORDERSIZE + infoSBArray.length + i;

				cellSBArray[tmpIndex] = fillLine; 
				
			}
		}
		
		////add non-covered lines (borderCharLines)
		for (int i = 0; i < BORDERSIZE; i++) {
			cellSBArray[i] = new StringBuilder(getCellSize());
			cellSBArray[i].append(borderLine);
			
			cellSBArray[getCellSize() -i-1] = new StringBuilder(borderLine);
		}
		return cellSBArray;
	}

	private StringBuilder[] buildInfoArea() {
		infoSBArray = new StringBuilder[INFOAREASIZE];
		String type = controller.getNameOfTerrainType(cellType);

		StringBuilder row1 = new StringBuilder();

		row1.append(VisualConstants.getColoredString(VisualConstants.colorName.DEFAULT, new StringBuilder("hoi")));
//		row1 = new StringBuilder();
//		row1.append("Terrain: " + type); 

//		System.out.println("cellSize"+ cellSize);
//		System.out.println("INFOAREASIZE: " + INFOAREASIZE);
//		System.out.println("FILLINLINES: " + FILLINLINES);
//		System.out.println("BORDERSIZE: " + BORDERSIZE);

		
		//wenn noch chars frei dann mit FILLINCHAR auffüllen 
		if (row1.length() < INFOAREASIZE) {
			int diff = INFOAREASIZE - row1.length();
			for (int i=0; i<diff; i++) {
				row1.append(FILLINCHAR);
			}	
		//sonst String kürzen 
		} else if ( row1.length() > INFOAREASIZE) {
			String s = row1.substring(0,  INFOAREASIZE);
			row1 = null;
			row1 = new StringBuilder(s);			
		}
		

		row1 = VisualConstants.getColoredString(VisualConstants.colorName.DEFAULT, row1);
		for (int i = 0; i < infoSBArray.length; i++) {
			infoSBArray[i] = row1;
		}

		return infoSBArray;
	}
	

	public static int getInfoareasize() {
		return INFOAREASIZE;
	}

	/**
	 * @return the cellSize
	 */
	public static int getCellSize() {
		if ( cellSize < CELLSIZE_MIN) {
			cellSize = CELLSIZE_MIN;
		}
				
		
		return cellSize;
	}

	/**
	 * @param cellSize the cellSize to set
	 */
	public static void setCellSize(int cellSize) {
		GameCell.cellSize = cellSize;
	}
	
}