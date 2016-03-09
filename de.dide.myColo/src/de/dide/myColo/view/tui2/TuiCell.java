package de.dide.myColo.view.tui2;

import java.util.LinkedList;

import de.dide.myColo.controller.Controller;
import de.dide.myColo.controller.impl.MainController;
import de.dide.myColo.model.gameField.impl.GameCell;
import de.dide.myColo.model.terrain.AbstractTerrain;
import de.dide.myColo.model.units.Unit;

public class TuiCell {
	Controller controller = new MainController();
	private static Integer cellSize = null;
	public static Integer infoAreaSize = null;
	private static Integer charsStillEmpty = null; 
	private  AbstractTerrain cellType = null;
	private  StringBuilder[] infoSBArray = null;
	private  StringBuilder[] cellSBArray = null;
	private int coordX;
	private int coordY;
	private LinkedList<Unit> unitList;
	
	public static final char BORDERCHAR = '+';
	public static final char FILLINCHAR = '-';
	public static final int BORDERSIZE = 1;
	private static final int CELLSIZE_MIN = 8;
	private static final int CELLSIZE_MAX = 40;
	private static final int CELLSIZE_DEFAULT = 20;	
	public static final int INFOAREASIZE_MIN = 8;
	public static final int INFOAREASIZE_MAX = 25;
	public static final double INFOAREASIZE_FAKTOR = 0.7;
	
	public TuiCell(GameCell cell, LinkedList<Unit> list) {
		initializeSizeVariables();
		coordX = cell.getCellIdx_X();
		coordY = cell.getCellIdx_Y();
		cellType = cell.getCellType();
//		unitList = new LinkedList<Unit>();
//		unitList.add(new Unit(0, 0, true, new Civilian(1)));
		unitList = list;
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
		System.out.println("cellSize: " + cellSize);
		System.out.println("infoAreaSize: " + infoAreaSize);
		System.out.println("charsStillEmpty: " + charsStillEmpty);
	}
	
	public void repaintCell() {
		paintCellViaColoredStringArray();
	};

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
		int manualLines = 4;
		infoSBArray = new StringBuilder[infoAreaSize - manualLines];
		String type = controller.getNameOfTerrainType(cellType);
		StringBuilder row0 = new StringBuilder();
		StringBuilder row1 = new StringBuilder();
		StringBuilder row2 = new StringBuilder();
		StringBuilder row3 = new StringBuilder();

		for (int i = 0; i < infoSBArray.length; i++) {
			infoSBArray[i] = new StringBuilder();
		}
		row0.append("Coord: " + coordX + "," + coordY);
 		row1.append("moveCost: " + cellType.getMoveCost());
 		row2.append("cellType: " + cellType.getNameOfTerrainType());
 		row3.append("unitList: ");

		infoSBArray[0] = row0;
		infoSBArray[1] = row1;
		infoSBArray[2] = row2;
		infoSBArray[3] = row3;
 		
 		if (unitList != null) {
	 		String[] unitLines = new String[unitList.size()];
	 		
	 		for (int i = 0; i < unitList.size(); i++) {
	 			unitLines[i] = "unit: " + unitList.get(i).getName();
	 			infoSBArray[manualLines + i] = new StringBuilder(unitLines[i]);
	 		}
 		}

		for (int i = 0; i < infoSBArray.length; i++) {
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
	

	//******************************************************	
	//******************************************************
	//	GETTERS AND SETTERS
	//******************************************************	
	//******************************************************
	
	
	public static char getBorderChar() {
		return BORDERCHAR;
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

}