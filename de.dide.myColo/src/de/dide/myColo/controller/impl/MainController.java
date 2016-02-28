package de.dide.myColo.controller.impl;

import de.dide.myColo.controller.Controller;
import de.dide.myColo.model.terrain.AbstractTerrain;

public class MainController implements Controller{

	public MainController() {
		
	}
	
	public String getNameOfTerrainType(AbstractTerrain cell) {
		return(cell.getNameOfTerrainType());
	}
}
