package de.dide.myColo.model.terrain.land.impl;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;

import de.dide.myColo.model.terrain.land.AbstractLand;

public class GrassLand extends AbstractLand{

	private static final int MOVECOST_GRASS = 2;
	
	public GrassLand() {
		super();
		movecost = MOVECOST_GRASS;
		this.nameOfTerrainType = "GrassLand";
		
		//LOAD BACKGROUND 
		try {
			backgroundImage = new ImageIcon(new URL("/mnt/disk_win_musik/=DATEN/=DOWNLOADS/MEDIA/BILDER/" +
			" divinDuke.jpg"));
		} catch (MalformedURLException e) {
			System.err.println("Background Image konnte nicht geladen werden.");
			e.printStackTrace();
		}
		
	}


	
}
