package de.dide.myColo.view.tui2;

import java.util.ArrayList;

import javax.swing.border.Border;

import de.dide.myColo.controller.Controller;
import de.dide.myColo.controller.impl.MainController;
import de.dide.myColo.model.terrain.AbstractTerrain;
import de.dide.myColo.model.terrain.impl.Water;
import de.dide.myColo.model.units.Unit;
import de.dide.myColo.model.units.unitType.impl.Civilian;

public class GameCell {

	Controller controller = new MainController();
	private char[][] cellCharMatrix;
	private static Integer cellSize = null;
	public static Integer infoAreaSize = null;
	private static Integer charsStillEmpty = null; 
	private  AbstractTerrain cellType = null;
	private  StringBuilder[] infoSBArray = null;
	private  StringBuilder[] cellSBArray = null;
	private int coordX;
	private int coordY;
	private ArrayList<Unit> unitList;
	
	public static final char BORDERCHAR = '+';
	public static final char FILLINCHAR = '-';
	public static final int BORDERSIZE = 1;
	private static final int CELLSIZE_MIN = 8;
	private static final int CELLSIZE_MAX = 40;
	private static final int CELLSIZE_DEFAULT = 30;	
	public static final int INFOAREASIZE_MIN = 6;
	public static final int INFOAREASIZE_MAX = 25;
	public static final double INFOAREASIZE_FAKTOR = 0.7;
	
	

	public GameCell(int idx_row, int idx_col) {
		initializeSizeVariables();
		cellCharMatrix = new char[cellSize][cellSize];
		cellType = new Water();
		coordX = idx_row;
		coordY = idx_col;
//		fillCell();
//		fillCellWithSymbolChars();
		unitList = new ArrayList<Unit>();
		unitList.add(new Unit(0, 0, true, new Civilian(1)));
		
		
		paintCellViaColoredStringArray();

	}

	private static void initializeSizeVariables() {
		
		if (cellSize == null || infoAreaSize == null || charsStillEmpty == null) {
			
			cellSize = CELLSIZE_DEFAULT;

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
		
		int charsToFill = cellSize - (2 * BORDERSIZE) - infoAreaSize;
	
		StringBuilder pre = new StringBuilder();
		StringBuilder post = new StringBuilder();

		for (int i = 0; i < (charsToFill / 2); i++) {
			pre.append(FILLINCHAR);
			post.append(FILLINCHAR);
		}		
		
		if ( (charsToFill % 2) == 1 ) {
			post.append(FILLINCHAR);
		}
		
		for (int i = 0; i < BORDERSIZE; i++) {
			post.append(BORDERCHAR);
			pre.insert(0, BORDERCHAR);
		}
		
		for (int i=0; i<coveredLines; i++) {
			cellSBArray[BORDERSIZE + i] = new StringBuilder(cellSize);
			cellSBArray[BORDERSIZE + i].append(pre.toString() + infoSBArray[i].toString() + post.toString());
		}
		
		//fill empty rows after...
		int rowsWithNull = cellSize - 2*BORDERSIZE - infoSBArray.length;
		
		if (rowsWithNull > 0) {
			for (int i=0; i < rowsWithNull; i++) {
				StringBuilder fillLine = new StringBuilder();
				
				int fillWithFillChar = cellSize - 2 * BORDERSIZE;
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
			cellSBArray[i] = new StringBuilder(cellSize);
			cellSBArray[i].append(borderLine);
			
			cellSBArray[cellSize -i-1] = new StringBuilder(borderLine);
		}
		return cellSBArray;
	}
	

	private StringBuilder[] buildInfoArea() {
		infoSBArray = new StringBuilder[4];
//		infoSBArray = new StringBuilder[infoAreaSize];
		String type = controller.getNameOfTerrainType(cellType);
		StringBuilder row1 = new StringBuilder();
		StringBuilder row2 = new StringBuilder();
		StringBuilder row3 = new StringBuilder();
		StringBuilder row4 = new StringBuilder();

		//System.out.println(infoSBArray.length);
		
		for (int i = 0; i < infoSBArray.length; i++) {
			infoSBArray[i] = new StringBuilder();
		}
		
		
		row1.append("Coord: " + coordX + "," + coordY);
 		row2.append("moveCost: " + cellType.getMoveCost());
 		row3.append("cellType: " + cellType.getNameOfTerrainType());

 		row4.append("unitList: ");
 		for (int i = 0; i < unitList.size(); i++) {
// 			System.out.println("Under ńame ischt: " + unitList.get(i).getName());
 			System.out.println("size von  unitList.size(): " + unitList.size());
 			row4.append(unitList.get(i).getName() + " ");
 		}
 		
 		//overwrite empty stringBuiĺders with manually written one from above
		infoSBArray[0] = row1;
		infoSBArray[1] = row2;
		infoSBArray[2] = row3;
		infoSBArray[3] = row4;
		
		for (int i = 0; i < infoSBArray.length; i++) {
//			System.out.println("infoSBArray[i].length: " + infoSBArray[i].length());
//			System.out.println("infoSBArray[i]: " + infoSBArray[i]);
			infoSBArray[i] = applyInfoAreaFormat(infoSBArray[i]);
		}
		return infoSBArray;
	}
	
	private StringBuilder applyInfoAreaFormat(StringBuilder row) {

		//wenn noch chars frei dann mit FILLINCHAR auffüllen 
		if (row.length() < infoAreaSize) {
			int diff = infoAreaSize - row.length();
			for (int i=0; i<diff; i++) {
				row.append(FILLINCHAR);
			}	
		//sonst String kürzen 
		} else if ( row.length() > infoAreaSize) {
			String s = row.substring(0,  infoAreaSize);
			row = null;
			row = new StringBuilder(s);			
		}
		
		row = VisualConstants.getColoredString(VisualConstants.colorName.ALERT, row);
		return row;
	}
	
	void addUnit(Unit addMe) {
		if (unitList == null) {
			unitList = new ArrayList<Unit>();
		}
		unitList.add(addMe);
	}
	
	
	
	//******************************************************	
	//******************************************************
	//******************************************************
//			GETTERS AND SETTERS
	//******************************************************
	//******************************************************	
	//******************************************************
	
	
	public static char getBorderChar() {
		return BORDERCHAR;
	}
	
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
		if (infoAreaSize == null) {
			initializeSizeVariables();
		}
		return infoAreaSize;
	}

	public ArrayList<Unit> getUnitList() {
		return unitList;
	}

}