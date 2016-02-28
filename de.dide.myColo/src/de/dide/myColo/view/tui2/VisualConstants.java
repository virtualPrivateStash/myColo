package de.dide.myColo.view.tui2;

public class VisualConstants {
	
	private static final String COL_RESET = "\u001B[0m";
	private static final String COL_RED = "\u001B[31m";
	private static final String COL_GREEN = "\u001B[32m";
	private static final String COL_TMP = "\u001B[38;5;155m";
	private static final String COL_BLUE_OWN = "\u001B[38;5;21m";

	private static final String COL_ALERT = "\u001B[91m";
	//private static final String COL_STANDARD = "\u001B[37m";
	private static final String COL_STD_BLACK = "\u001B[30M";
	private static final String COL_INFO_AREA = "\u001B[37m";	

	
	
	//tut so nicht, leider farbausgabe nur im xterm256 modus möglich
	//public static final String WATER = "\u001B[39;2;150;25;25;m";
	
	
	public enum colorName { 
						RESET, 
						
						DEFAULT, 
						WATER,
						
						//TEXT COLORS
						ALERT,
						INFOAREA,
						STD_BLACK;
	
	};
	

	

	public static StringBuilder getColoredString(colorName color, StringBuilder message) {
		StringBuilder colorString = new StringBuilder();
		switch (color) {
			case RESET:
				colorString.append(COL_RESET);
				break;
				
				//GAMEFIELD-COLORS
			case DEFAULT:
				colorString.append(COL_GREEN);
				break;
			case WATER:
				colorString.append(COL_BLUE_OWN);
				break;		

				//TEXT-COLORS
			case ALERT:
				colorString.append(COL_ALERT);
				break;
			case INFOAREA:
				colorString.append(COL_INFO_AREA);
				break;
			case STD_BLACK:
				colorString.append(COL_STD_BLACK);
				break;				
			default:	
				colorString.append(COL_RESET);
			}

		colorString.append(message + COL_RESET);
		return colorString;
	}
	
}
	