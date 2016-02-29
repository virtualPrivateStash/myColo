package de.dide.myColo.view.tui2;

import de.dide.myColo.controller.Controller;
import de.dide.myColo.controller.impl.MainController;

public class Tui {
	private static final int GAMEFIELDSIZE = 3;
	private GameString gameString; 
	private static Tui instance;
	private Controller controller;

	
	private Tui() {
		controller = new MainController();
		gameString = GameString.getInstance();
	}
	
	
	public static Tui getInstance () {
	  if (instance == null) {
		  instance = new Tui();
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
}