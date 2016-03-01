package de.dide.myColo.view.tui2;

import de.dide.myColo.controller.impl.MainController;
import de.dide.myColo.model.game.GameState;
import de.dide.myColo.util.observer.Event;

public class Tui implements de.dide.myColo.util.observer.IObserver{
	private static final int GAMEFIELDSIZE = 3;
	private GameString gameString; 
	private static Tui instance;
	private MainController controller;
	private boolean continueGame;
	private static GameState gameState;
	
	private Tui(MainController controller, GameState gameState) {
			this.controller = controller;
			gameState = gameState;
			controller.addObserver(this);
		gameString = GameString.getInstance();
	}
	
	
	public static Tui getInstance(MainController controller, GameState gameState) {
	  if (instance == null) {
		  instance = new Tui(controller, gameState);
	  }
	  return instance;
	}
	
	public void printTuiToConsole() {
		gameString.paint2();
	}

	/**
	 * @return the gAMEFIELDSIZE
	 */
	public static int getGameFieldSize() {
		return GAMEFIELDSIZE;
	}

	@Override
	public void update(Event e) {
		printTuiToConsole();
	}

	
	public boolean processInputLine(String line, GameState gamestate) {
		int yearCurrentRound = gamestate.getYear();
		while ( yearCurrentRound == gamestate.getYear()) {
	
			continueGame = true;
			char c = line.charAt(0);
			continueGame = checkArgsForGameOptions(c, line);
			continueGame = checkArgsForMoveCommands(c, line);

		}
		return continueGame;
	}


	private boolean checkArgsForGameOptions(char c, String line) {
		boolean continueGame = true; 
		switch (c) {
			case 'q':		//quit
				System.out.println("Quit Program (command q was given)");
				continueGame = false;
				break;
			case 'h':		//show help
				System.out.println("Hier mal die Hilfe anzeigen...");
				break;
			default:		//if none of the above cases applied 
//				finishedAfterSwitchCase = false;
		}
		return continueGame;
	}
	
	private boolean checkArgsForMoveCommands(char c, String line) {
		
		continueGame = true; 
		switch (c) {
			case '4':	//LEFT
				System.out.println("trying to move unit to:\t\tLEFT");
				controller.moveUnit_Left();
				break;
//			case '6':	//RIGHT
//				System.out.println("trying to move unit to:\t\tRIGHT");
//				controller.moveUnit_Right();
//				break;
//			case '8':	//UP
//				System.out.println("trying to move unit to:\t\tUP");
//				controller.moveUnit_Up();
//				break;
//			case '2':	//DOWN
//				System.out.println("trying to move unit to:\t\tDOWN");
//				controller.moveUnit_Down();
//				break;
			case '7':	//UP_LEFT
				System.out.println("trying to move unit to:\t\tUP_LEFT");
				break;
			case '9':	//UP_RIGHT
				System.out.println("trying to move unit to:\t\tUP_RIGHT");
				break;	
			case '1':	//DOWN_LEFT
				System.out.println("trying to move unit to:\t\tDOWN_LEFT");
				break;	
			case '3':	//DOWN_RIGHT
				System.out.println("trying to move unit to:\t\tDOWN_RIGHT");
				break;	
				
			case 'q':		//quit
				System.out.println("Quit Program (command q was given)");
				continueGame = false;
				break;				
				
			default:		//if none of the above cases applied 
//				finishedAfterSwitchCase = false;
		}
		
		return continueGame;
	}

}