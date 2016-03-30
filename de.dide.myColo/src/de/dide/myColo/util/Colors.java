package de.dide.myColo.util;

import de.dide.myColo.view.tui2.VisualConstants.colorName;

public class Colors {
	
	public static final String COL_RESET = "\u001B[0m";		// \\u001B is actually ONE unicode-char!
	public static final String COL_STD_BLACK = "\u001B[30m";

	public static final String COL_RED = "\u001B[31m";
	public static final String COL_GREEN = "\u001B[32m";
	public static final String COL_SUPERGREEN = "\u001B[38;5;155m";
	public static final String COL_BLUE_OWN = "\u001B[38;5;21m";
	public static final String COL_ALERT = "\u001B[91m";

	public static final String COL_TMP = COL_ALERT;
	public static final String COL_DEFAULT = COL_STD_BLACK;
	public static final String COL_BORDER = COL_SUPERGREEN;
	public static final String COL_INFO_AREA = COL_STD_BLACK;
	public static final String COL_ACTIVE = COL_RED;
	
	//IUnitType Colors:
	public static final String COL_CIVILIAN = COL_DEFAULT;	
	
	//AbstractTerrain Colors:
	public static final String COL_LANDTYPE_DEFAULT = COL_DEFAULT;
	public static final String COL_LANDTYPE_WATER = COL_BLUE_OWN;
	

	
	
	public static String createColorStr(String colorStr, String message) {
		StringBuilder colorString = new StringBuilder();
		//colorString.append(colorStr + message + COL_RESET);
		colorString.append(colorStr + message);
		return colorString.toString();
	}
	
}
	