package de.dide.myColo.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import de.dide.myColo.controller.impl.MainController;
import de.dide.myColo.model.game.GameState;
import de.dide.myColo.model.units.Unit;
import de.dide.myColo.model.units.unitType.impl.Civilian;
import de.dide.myColo.view.tui2.Tui;


public class ColGame {

	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String TMP = "\u001B[38;5;155m";
	public static final String TMP2 = "\u001B[39;2;150;25;25;m";
	public static final String RESET = "\u001B[0m";
	private static GameState gameState = null;

	private Tui tui;
	private static MainController controller;

	
	public ColGame() {
		controller = new MainController();
		gameState = new GameState();
		
		createFirstGameState(gameState);
		
		tui = Tui.getInstance(controller, gameState);
		//tui.printTuiToConsole();
//		// Set up logging through log4j
//		PropertyConfigurator.configure("log4j.properties");
		
		//continue to read user input on the TUI until the user decides to quit
		boolean continueGame = true;
//		System.out.println(System.in.getClass());
		Scanner scanner = new Scanner(System.in);
		while (continueGame) {
			continueGame = playTheGame(scanner, gameState);
		}
		
//		System.out.println(RED + " This text must be in red " + RESET)
	}

	private boolean playTheGame(Scanner scanner, GameState gameState) {
		boolean continueGame = true;

		while (continueGame) {
			
			//for every unit in UnitList
			for (int i = 0; i < gameState.getUnitList().size(); i++) {
				//while unit (still) has turn processInputLine 
				while (gameState.isOnTurn()) {
					System.out.println("vor processInputLine");
					continueGame = tui.processInputLine(scanner.next() , gameState);
					System.out.println("aaaaaaaaa");
				}
			}
			//add for example a gui or logger or whatever here
		}
		return continueGame;
	}

	private void createFirstGameState(GameState gameState) {
		gameState.setYear(2000);;
		gameState.setTurnFinished(true);
		
		Unit unit1 = new Unit(0, 0, true, new Civilian(1));
		Unit unit2 = new Unit(1, 1, true, new Civilian(2));
		Unit unit3 = new Unit(2, 2, true, new Civilian(3)); 
				
		List<Unit> newUnitList = Arrays.asList(unit1, unit2, unit3);
		gameState.setUnitList(newUnitList);
	}
	
	public Tui getTui() {
		return tui;
	}
	
}
