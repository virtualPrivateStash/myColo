package de.dide.myColo.view.tui2;

public class VisualConstants {
	
	private static final String COL_RESET = "\u001B[0m";
	private static final String COL_RED = "\u001B[31m";
	private static final String COL_GREEN = "\u001B[32m";
	private static final String COL_TMP = "\u001B[38;5;155m";
	private static final String COL_BLUE_OWN = "\u001B[38;5;21m";
	//tut so nicht, leider farbausgabe nur im xterm256 modus möglich
	//public static final String WATER = "\u001B[39;2;150;25;25;m";
	
	
	public enum colorName { RESET , DEFAULT, WATER};
	

	

	public static StringBuilder getColoredString(colorName color, StringBuilder message) {
		StringBuilder colorString = new StringBuilder();
		switch (color) {
			case DEFAULT:
				colorString.append(COL_GREEN);
				break;
			case WATER:
				colorString.append(COL_BLUE_OWN);
				break;		
			case RESET:
				colorString.append(COL_RESET);
				break;
			default:	
				colorString.append(COL_RESET);
			}

		colorString.append(message + COL_RESET);
		return colorString;
	}
	
}
	