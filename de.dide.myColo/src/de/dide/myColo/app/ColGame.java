package de.dide.myColo.app;

import de.dide.myColo.view.tui2.Tui;

public class ColGame {

	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String TMP = "\u001B[38;5;155m";
	public static final String TMP2 = "\u001B[39;2;150;25;25;m";
	public static final String RESET = "\u001B[0m";

	
	public static void main(String[] args) {
		Tui tui = Tui.getInstance();
		tui.printTuiToConsole();
		
//		System.out.println(RED + " This text must be in red " + RESET);
//		System.out.println(GREEN + " This text must be in green " + RESET);
//		System.out.println(TMP + " This text must be in tmp " + RESET);
//		System.out.println(TMP2 + " This text must be in tmp " + RESET);
//		System.out.println(" This text must be a colourless string ");
	}

}
