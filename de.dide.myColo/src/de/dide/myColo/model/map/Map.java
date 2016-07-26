package de.dide.myColo.model.map;

import de.dide.myColo.app.ColGame;
import de.dide.myColo.view.guiQt1.Gui_Main;

public class Map implements IMap{
	
	private final int size;
	
	public Map() {
		size = Gui_Main.getGameFieldSize();
				
		
	}

}
