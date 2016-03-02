package de.dide.myColo.app;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import de.dide.myColo.controller.impl.MainController;
import de.dide.myColo.model.game.GameState;
import de.dide.myColo.model.units.Unit;
import de.dide.myColo.model.units.unitType.impl.Civilian;
import de.dide.myColo.view.tui2.Tui;
import de.dide.myColo.view.tui2.VisualConstants;

public class ColGame {

	public static final String RED = "\u001B[91m";
	public static final String GREEN = "\u001B[32m";
	public static final String TMP = "\u001B[38;5;155m";
	public static final String TMP2 = "\u001B[39;2;150;25;25;m";
	public static final String RESET = "\u001B[0m";

	private static GameState gameState = null;
	private Tui tui;
	private static MainController controller;

	
	public ColGame() {
		//INITIALIZE
		controller = new MainController();
		gameState = createFirstGameState();
		tui = Tui.getInstance(controller, gameState);
		tui.printTuiToConsole();
//		PropertyConfigurator.configure("log4j.properties");
		
		//read user input from the TUI until game quits
		boolean continueGame = true;
		Scanner scanner = new Scanner(System.in);
		while (continueGame) {
			continueGame = playTheGame(scanner, gameState);
		}
		
		//LAST LINES BEFORE GAME ENDS
		System.out.println(GREEN + "Hier endet das Spiel.." + RESET);
		System.out.println(VisualConstants.getColoredString(VisualConstants.colorName.ALERT, new StringBuilder("Hier endet das Spiel..")).toString());
	}

	private boolean playTheGame(Scanner scanner, GameState gameState) {
		boolean continueGame = true;
		continueGame = playOneRound(scanner, gameState);
		return continueGame;
	}
		
		
	private boolean playOneRound(Scanner scanner, GameState gameState) {
		boolean yearNotOver = true;
		LinkedList<Unit> unitList = gameState.getUnitList();
		Unit unit;
		//nur zum allerersten mal??
		//Zellen ihre Units per unitList mitteilen 
		System.out.println("Year " +gameState.getYear()+ " just started :)\n");
		
		while (yearNotOver) {	
			
			
			try 
			{
				unit = unitList.pop();
				
			} 
			catch (Exception e) 
			{

				yearNotOver = false;
				
			}

		}
		
//		//UNITLIST ABARBEITEN (ALLE UNITS DES SPIELERS PER LOOP VERARBEITEN) 
//		while ( unitList.size() > 0 ) 
// 
//			
//		dann
//		for unit in unitList 
//			controller.execUnitsActions(unit)
//	
//			wann unitList aktualisieren bzw. was ist die funktion von unitList
//				
//			
		
//		for (int i=0; i<unitList.size(); i++) {
//			//for every unit in UnitList
			for (int i = 0; i < gameState.getUnitList().size(); i++) {
				//while unit (still) has turn processInputLine 
				while (gameState.isOnTurn()) {
					System.out.println("\nBitte geben Sie einen Buchstaben ein: ");
					yearNotOver = tui.processInputLine(scanner.next(), gameState);
					if (!yearNotOver) {
						gameState.setIsOnTurn(false);
						break;
					}
				}
			}
			//add for example a gui or logger or whatever here
//		}
//				
		return yearNotOver;
	}

	private GameState createFirstGameState() {
		GameState newState = new GameState();
		newState.setYear(2000);;
		newState.setIsOnTurn(true);
		
		Unit unit1 = new Unit(0, 0, true, new Civilian(1));
		Unit unit2 = new Unit(1, 1, true, new Civilian(2));
		Unit unit3 = new Unit(2, 2, true, new Civilian(3)); 
				
		List<Unit> newUnitList = Arrays.asList(unit1, unit2, unit3);
		//newState.setUnitList(newUnitList);
		return newState;
	}
	
	public Tui getTui() {
		return tui;
	}
	
}
