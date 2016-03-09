package de.dide.myColo.app;

import de.dide.myColo.view.tui2.Tui;

public class AppCol {
	
	public static void main(String[] args) {
		firstGameSetUp();
	}

	private static void firstGameSetUp() {
		System.out.println("WELCOME TO THE FIRST STEPS OF MYCOLO");
		ColGame erstesSpiel = new ColGame();
		erstesSpiel.playGame();
		//hier können logger usw. eingefügt werden 
	}
}
