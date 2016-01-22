package de.dide.myColo.view.tui;

public class Tui {
	
	private final int CELLSIZE = 5;
	private final int GAMEFIELDSIZE= 5;

	
	private static Tui instance;
	
	private Tui() {}
	
	public static Tui getInstance () {
	  if (instance == null) {
		  instance = new Tui ();
	  }
	  return instance;
	}
	
	public void printTuiToConsole() {
		
		
		
	}
	
	
}


