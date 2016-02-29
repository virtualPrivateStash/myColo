package de.dide.myColo.controller.impl;

import de.dide.myColo.controller.Controller;
import de.dide.myColo.model.terrain.AbstractTerrain;
import de.dide.myColo.util.observer.Observable;

public class MainController  extends Observable implements Controller{

	public MainController() {}
	
	public String getNameOfTerrainType(AbstractTerrain cell) {
		return(cell.getNameOfTerrainType());
	}
}
