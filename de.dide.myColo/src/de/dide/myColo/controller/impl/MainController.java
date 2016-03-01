package de.dide.myColo.controller.impl;

import de.dide.myColo.controller.Controller;
import de.dide.myColo.model.terrain.AbstractTerrain;
import de.dide.myColo.util.observer.Observable;

public class MainController extends Observable implements Controller{

	public MainController() {}
	
	public String getNameOfTerrainType(AbstractTerrain cell) {
		return(cell.getNameOfTerrainType());
	}

	public void moveUnit_Left() {
		//schreibe units coordinaten für x = x-1 
	System.out.println("leeeefttt!");
	}
	
//	public void moveUnit_Up(){}
//	public void moveUnit_Right(){}
//	public void moveUnit_Down(){}
//	
//	public void startGame(){} //start + neustart
//	public void exitGame(){}

}