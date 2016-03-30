	package de.dide.myColo.app;

public class AppCol {
	
	public static void main(String[] args) {
		firstGameSetUp(args);
	}

	private static void firstGameSetUp(String[] args) {
		System.out.println("WELCOME TO THE FIRST STEPS OF MYCOLO");
		ColGame erstesSpiel = new ColGame(args);
		erstesSpiel.playGame();
		//hier können logger usw. eingefügt werden 
	}
}	
