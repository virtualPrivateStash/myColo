package de.dide.myColo.app;

import java.util.Scanner;

import de.dide.myColo.controller.impl.MainController;
import de.dide.myColo.view.tui2.Tui;


public class ColGame {

	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String TMP = "\u001B[38;5;155m";
	public static final String TMP2 = "\u001B[39;2;150;25;25;m";
	public static final String RESET = "\u001B[0m";

	private static Tui tui;
	private static MainController controller;

	public ColGame() {
		controller = new MainController();
		tui = Tui.getInstance(controller);
		tui.printTuiToConsole();
//		// Set up logging through log4j
//		PropertyConfigurator.configure("log4j.properties");
		
		//continue to read user input on the TUI until the user decides to quit
		boolean continueGame = true;
		Scanner scanner = new Scanner(System.in);
		while (continueGame) {
			continueGame = tui.processInputLine(scanner.next());
		}
		
//		System.out.println(RED + " This text must be in red " + RESET);
//		System.out.println(GREEN + " This text must be in green " + RESET);
//		System.out.println(TMP + " This text must be in tmp " + RESET);
//		System.out.println(TMP2 + " This text must be in tmp " + RESET);
//		System.out.println(" This text must be a colourless string ");
	}

	public Tui getTui() {
		return tui;
	}
	
}
