package de.dide.myColo.app;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import de.dide.myColo.controller.impl.MainController;
import de.dide.myColo.model.game.GameState;
import de.dide.myColo.model.units.Unit;
import de.dide.myColo.model.units.unitType.impl.Civilian;
import de.dide.myColo.util.observer.Event;
import de.dide.myColo.util.observer.IObserver;
import de.dide.myColo.view.tui2.Tui;
import de.dide.myColo.view.tui2.VisualConstants;

public class ColGame{

	public static final String RED = "\u001B[91m";
	public static final String GREEN = "\u001B[32m";
	public static final String TMP = "\u001B[38;5;155m";
	public static final String TMP2 = "\u001B[39;2;150;25;25;m";
	public static final String RESET = "\u001B[0m";
	private static String askForInputString;
	private static boolean gameOver = false;

	private static GameState gameState;

	private Tui tui;
	private static MainController controller;
	
	public ColGame() {
		//INITIALIZE
		controller = new MainController();
		gameState = createFirstGameState();
		tui = Tui.getInstance(controller, gameState);
		askForInputString = createAskForInputString();
//		PropertyConfigurator.configure("log4j.properties");
	}
	
	public void playGame() {
		//READ USER INPUT FROM TUI UNTIL GAME ENDS
		boolean continueGame = true;
		Scanner scanner = new Scanner(System.in);
		while (!ColGame.isGameOver()) {
			controller.initializeVarsForNewYear(gameState);
			playOneRound(scanner, gameState);
			
			//end game at specified year 
			if (gameState.getYear() > 1020) {
				break;
			}
		}
		//LAST LINES BEFORE GAME ENDS
		System.out.println(GREEN + "Hier endet das Spiel.." + RESET);
		System.out.println(VisualConstants.getColoredString(VisualConstants.colorName.ALERT, new StringBuilder("Hier endet das Spiel..")).toString());
	}

	/**
	 * Play one round of the game meaning action with each unit of Player.
	 * @param scanner
	 * @param gameState
	 * @return
	 */
	private void playOneRound(Scanner scanner, GameState gameState) {
		System.out.println("Year " +gameState.getYear()+ " just started :)");

//HIER KOPIEREN STATT REFERENZIEREN!!!
		LinkedList<Unit> unitsToProcess = new LinkedList<Unit>(gameState.getAllUnitsInGame());
		Unit unit;
		controller.initializeVarsForNewYear(gameState);
		boolean yearNotOver = true;
		
		while (!ColGame.isGameOver() && yearNotOver) {	
			//for each unit in unitList ask user for input action
			try {
				unit = unitsToProcess.pop();
				System.out.println("unitList nicht leer");
				while (unit.isToBeProcessed()) {
					processUnit(unit, scanner);					
				}
				
//				if (!unit.isToBeProcessed()) {
//					System.out.println("not to be processed...");
//				}
				
			} 
			//if UnitList is empty then end turn
			catch (Exception e) {
				yearNotOver = false;
				System.out.println("unitList ist abgearbeitet...");
			}
			if (yearNotOver == false) {
				gameState.incrementYear();
				System.out.println("Das Jahr ist jetzt zu Ende...");
				break;
			}
				
		}
		setGameOver(yearNotOver);
	}
	
	private void processUnit(Unit unit, Scanner scanner) {
		System.out.println("Unit " + unit.getCoordX() +","+ unit.getCoordY() +" ist am Zug.");
		//Eingabe-Aufforderung ausgeben
		System.out.println(createAskForInputString());
		tui.processInputLine(unit, scanner.next(), gameState);
	}
	
	public Tui getTui() {
		return tui;
	}
	
	public static GameState getGameState() {
		return gameState;
	}
	
	public static boolean isGameOver() {
		return gameOver;
	}

	public static void setGameOver(boolean gameOver) {
		ColGame.gameOver = gameOver;
	}


	private String createAskForInputString() {
		StringBuilder sb = new StringBuilder(); 
		sb.append("Es stehen folgende Keys zur Verfügung:\n");
		String[] commandArray = new String[20];
		//MOVE DIRECTIONS
		commandArray[1] = "\t--> 1 <--\tgo DOWN LEFT";
		commandArray[2] = "\t--> 2 <--\tgo 	 DOWN";
		commandArray[3] = "\t--> 3 <--\tgo DOWN RIGHT";
		commandArray[4] = "\t--> 4 <--\tgo 	 LEFT";
		commandArray[5] = "\t--> 6 <--\tgo 	 RIGHT";
		commandArray[6] = "\t--> 7 <--\tgo  UP  LEFT";
		commandArray[7] = "\t--> 8 <--\tgo      UP";
		commandArray[8] = "\t--> 9 <--\tgo  UP  RIGHT";
		commandArray[9] = "\t--> 5 <--\t NOT IMPLEMENTED YET";
		//OTHER COMMANDS
		String q = "\t--> q <--\t Beendet das Programm";
		String h = "\t--> h <--\t zeigt irgendwann die Hilfe zu MyColo an";
		commandArray[10] = q;
		commandArray[11] = h;
		
		for (int i = 0; i < commandArray.length; i++) {
			if (commandArray[i] != null) {
				sb.append(commandArray[i] + "\n");	
			}
		}
		return sb.toString();
	}

///////////////////////////////////////////////////////////////////	
///////////////////////////////////////////////////////////////////
	
	private GameState createFirstGameState() {
		GameState newState = new GameState(null, 1000);
		Unit unit1 = new Unit(0, 0, true, new Civilian(1), 1);
		Unit unit2 = new Unit(1, 1, true, new Civilian(2), 1);
		LinkedList<Unit> newUnitList = new LinkedList<Unit>();
		newUnitList.add(unit1);
		newUnitList.add(unit2);

		for (int i=0; i < 3; i++) {
			newUnitList.add(new Unit(2, 2, true, new Civilian(3), 1));			
		}

		newState.setAllUnitsInGame(newUnitList);
		return newState;
	}

}