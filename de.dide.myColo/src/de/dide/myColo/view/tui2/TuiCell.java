package de.dide.myColo.view.tui2;

import java.util.LinkedList;

import de.dide.myColo.util.observer.Event;
import de.dide.myColo.util.observer.IObserver;
import de.dide.myColo.controller.Controller;
import de.dide.myColo.controller.impl.MainController;
import de.dide.myColo.model.gameField.impl.GameCell;
import de.dide.myColo.model.terrain.AbstractTerrain;
import de.dide.myColo.model.units.Unit;
import de.dide.myColo.util.Colors;

public class TuiCell{
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
	private static final int CELLSIZE_DEFAULT = 16;	
	public static final int INFOAREASIZE_MIN = 10;
	public static final int INFOAREASIZE_MAX = 25;
	public static final double INFOAREASIZE_FAKTOR = 0.7;
	
	public TuiCell(GameCell cell, LinkedList<Unit> list) {
		initializeSizeVariables();
		coordX = cell.getCellIdx_X();
		coordY = cell.getCellIdx_Y();
		cellType = cell.getCellType();
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
		String borderColor = Colors.COL_BORDER;
		String coloredBorderChar = Colors.createColorStr(borderColor, String.valueOf(BORDERCHAR));
		insertformattedInfoStrings(borderColor);
		fillRowsThatAreStillEmpty(coloredBorderChar, borderColor);
		return cellSBArray;
	}

	private void fillRowsThatAreStillEmpty(String coloredBorderChar, String borderColor) {
		String borderLineStr = createBorderLineStr(borderColor);
		int rowsWithNull = cellSize - 2*BORDERSIZE - infoSBArray.length;
		String fillCharColor = Colors.createColorStr(cellType.getColor(), String.valueOf(BORDERCHAR));
		
		if (rowsWithNull > 0) {
			for (int i=0; i < rowsWithNull; i++) {
				StringBuilder fillLine = new StringBuilder();
				int fillWithFillChar = cellSize - 2 * BORDERSIZE;
				
				for (int k=0; k< (BORDERSIZE); k++) {
					fillLine.append(coloredBorderChar);
				}
				for (int j=0; j< (fillWithFillChar); j++) {
					fillLine.append(fillCharColor);
				}
				for (int k=0; k< (BORDERSIZE); k++) {
					fillLine.append(coloredBorderChar);
				}
				//insert rowsWithNull into cellSBArray
				int tmpIndex = BORDERSIZE + infoSBArray.length + i;
				cellSBArray[tmpIndex] = fillLine; 
			}
		}
		addBorderCharLines(borderLineStr);
	}

	private String createBorderLineStr(String borderColor) {
		//fill one line with borderChars as copy-paste material
		StringBuilder borderLine= new StringBuilder();
		for (int i=0; i< cellSize; i++) {
			borderLine.append(BORDERCHAR);
		}
		String borderLineStr = Colors.createColorStr(borderColor, borderLine.toString());
		return borderLineStr;
	}

	private void addBorderCharLines(String borderLineStr) {
		//add non-covered lines (borderCharLines)
		for (int i = 0; i < BORDERSIZE; i++) {
			cellSBArray[i] = new StringBuilder(borderLineStr);
			cellSBArray[cellSize -i-1] = new StringBuilder(borderLineStr);
		}
	}

	private void insertformattedInfoStrings(String borderColor){
		//lines with space covered by infoArray	
		int coveredLines = infoSBArray.length;
		StringBuilder pre = new StringBuilder();
		StringBuilder post = new StringBuilder();
		
		for (int i = 0; i < (charsStillEmpty / 2); i++) {
			pre.append(Colors.createColorStr(cellType.getColor(), new String(String.valueOf(FILLINCHAR))));
			post.append(Colors.createColorStr(cellType.getColor(), new String(String.valueOf(FILLINCHAR))));
		}		
		if ( (charsStillEmpty % 2) == 1 ) {
			post.append(FILLINCHAR);
		}
		StringBuilder tmpSb = new StringBuilder();
		//append chars for left and right border
		for (int i = 0; i < BORDERSIZE; i++) {
			tmpSb.append(BORDERCHAR);
		}
		String borderPart = Colors.createColorStr(borderColor, tmpSb.toString());
		pre = new StringBuilder().append(borderPart).append(pre);
		post.append(borderPart);
		String str_pre = pre.toString();
		String str_post = post.toString();
		
		//fill cellSBArray with "pre + infoString + post" 
		for (int i=0; i<coveredLines; i++) {
			cellSBArray[BORDERSIZE + i] = new StringBuilder(cellSize);
			cellSBArray[BORDERSIZE + i].append(str_pre + infoSBArray[i].toString() + str_post);
		}
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
 		row1.append("moveC: " + cellType.getMoveCost());
 		row2.append("cellT: " + cellType.getNameOfTerrainType());
 		row3.append("unitL: ");

		infoSBArray[0] = row0;
		infoSBArray[1] = row1;
		infoSBArray[2] = row2;
		infoSBArray[3] = row3;
 		
 		if (unitList != null) {
	 		String[] unitLines = new String[unitList.size()];
	 		
	 		int loopNr = unitList.size();
	 		if (unitList.size() + manualLines >= infoSBArray.length) {
	 			loopNr = infoSBArray.length - manualLines;
	 		} 
	 		for (int i = 0; i < loopNr; i++) {
	 			unitLines[i] = "unit:  " + unitList.get(i).getName();
	 			infoSBArray[manualLines + i ] = new StringBuilder(unitLines[i]);
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
		row = VisualConstants.getColoredString(VisualConstants.colorName.DEFAULT, row);
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