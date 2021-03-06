package de.dide.myColo.view.tui2;

import de.dide.myColo.app.ColGame;
import de.dide.myColo.controller.impl.MainController;
import de.dide.myColo.model.game.GameState;
import de.dide.myColo.model.units.Unit;
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
		this.gameState = gameState;
		gameString = GameString.getInstance();
		controller.addObserver(this);
	}
	
	public static Tui getInstance(MainController controller, GameState gameState) {
	  if (instance == null) {
		  instance = new Tui(controller, gameState);
	  }
	  return instance;
	}
	
	public void printGameString() {
		gameString.paint();
	}

	/**
	 * @return the gAMEFIELDSIZE
	 */
	public static int getGameFieldSize() {
		return GAMEFIELDSIZE;
	}

	@Override
	public void update(Event e) {
		printGameString();
	}

	
	public void processInputLine(Unit unit, String line, GameState gamestate) {
		char c = line.charAt(0);
		checkArgsForGameOptions(c, line, unit);
		checkArgsForMoveCommands(unit, c, line);
	}


	private void checkArgsForGameOptions(char c, String line, Unit unit) {
		boolean continueGame = true; 
		switch (c) {
			case 'q':		//quit
				System.out.println("Quit Program (command q was given)");
				ColGame.setGameOver(true);
				unit.setToBeProcessed(false);
				break;
			case 'h':		//show help
				System.out.println("Hier mal die Hilfe anzeigen...");
				break;
			default:		//if none of the above cases applied 
//				finishedAfterSwitchCase = false;
		}
	}
	
	private void checkArgsForMoveCommands(Unit unit, char c, String line) {
		
		continueGame = true; 
		switch (c) {
			case '4':	//LEFT
				System.out.println("trying to move unit to:\t\tLEFT");
				controller.moveUnit_Left(unit);
				break;
			case '6':	//RIGHT
				System.out.println("trying to move unit to:\t\tRIGHT");
				controller.moveUnit_Right(unit);
				break;
			case '8':	//UP
				System.out.println("trying to move unit to:\t\tUP");
				controller.moveUnit_Up(unit);
				break;
			case '2':	//DOWN
				System.out.println("trying to move unit to:\t\tDOWN");
				controller.moveUnit_Down(unit);
				break;
			case '7':	//UP_LEFT
				System.out.println("trying to move unit to:\t\tUP_LEFT");
				controller.moveUnit_Up_Left(unit);
				break;
			case '9':	//UP_RIGHT
				System.out.println("trying to move unit to:\t\tUP_RIGHT");
				controller.moveUnit_Up_Right(unit);
				break;	
			case '1':	//DOWN_LEFT
				System.out.println("trying to move unit to:\t\tDOWN_LEFT");
				controller.moveUnit_Down_Left(unit);
				break;	
			case '3':	//DOWN_RIGHT
				System.out.println("trying to move unit to:\t\tDOWN_RIGHT");
				controller.moveUnit_Down_Right(unit);
				break;	
				
			case 'q':		//quit
				System.out.println("Quit Program (command q was given)");
				ColGame.setGameOver(true);
				break;				
			default: 
		}
	}

}