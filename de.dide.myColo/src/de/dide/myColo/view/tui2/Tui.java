package de.dide.myColo.view.tui2;

import de.dide.myColo.controller.impl.MainController;
import de.dide.myColo.util.observer.Event;

public class Tui implements de.dide.myColo.util.observer.IObserver{
	private static final int GAMEFIELDSIZE = 3;
	private GameString gameString; 
	private static Tui instance;
	private MainController controller;
	private static boolean continueGame;

	
	private Tui(MainController controller) {
			this.controller = controller;
			controller.addObserver(this);
		gameString = GameString.getInstance();
	}
	
	
	public static Tui getInstance(MainController controller) {
	  if (instance == null) {
		  instance = new Tui(controller);
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

	
	public boolean processInputLine(String line) {
		
//		Player xy = GameSettings.getWhoseTurnItIs();
		continueGame = true;
		char c = line.charAt(0);
		inputCheckForGameOptions(c, line);
		return continueGame;
	}


	private boolean inputCheckForGameOptions(char c, String line) {

		switch (c) {

			case 'q':		//quit
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

}