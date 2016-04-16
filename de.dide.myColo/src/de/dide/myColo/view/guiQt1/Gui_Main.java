package de.dide.myColo.view.guiQt1;

import de.dide.myColo.controller.impl.MainController;
import de.dide.myColo.model.game.GameState;
import de.dide.myColo.util.observer.Event;
import de.dide.myColo.util.observer.IObserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.trolltech.qt.core.QSize;
import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QSizePolicy;
import com.trolltech.qt.gui.QWidget;

public class Gui_Main extends QWidget implements IObserver{

	private static Gui_Main instance;
	protected QGridLayout layout;
	private static String styleSheet;
	private final int GAMEFIELDSIZE = 10;
	private QPushButton[][] buttonArray;
	private MainController controller;
	
	private Gui_Main(String[] args, GameState state)  throws IOException {
		loadStylesheetFile();
		this.resize(new QSize(900, 900));
		layout = new QGridLayout(this);
		layout.setSpacing(0);
		layout.setContentsMargins(0, 0, 0, 0);
		buttonArray = new QPushButton[GAMEFIELDSIZE][GAMEFIELDSIZE];
		addButtonsToLayout();
		this.show();
		QApplication.execStatic();
	}

	private void loadStylesheetFile() throws IOException {
		File file = new File(
				"/home/xy/PROG/workspaces/java/JambiTest1/src/stylesheet.qss");
		FileReader fr;
		try {
			fr = new FileReader(file);
		    BufferedReader br = new BufferedReader(fr);
		    StringBuilder sb = new StringBuilder();
		    String line;
		    while ( (line = br.readLine()) != null) {
		    	sb.append(line);
		    }
			styleSheet = sb.toString();
			this.setStyleSheet(styleSheet);
		} catch (FileNotFoundException e) {
			System.out.println("Stylesheet konnte nicht geladen werden");
			e.printStackTrace();
		}
	}
	
	public static Gui_Main getInstance(String[] args, 
			GameState state) {
		if (instance == null) {
			try {
				instance = new Gui_Main(args, state);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		return instance;
	}
	
	private void addButtonsToLayout() {
		for (int i = 0; i < GAMEFIELDSIZE; i++) {
			for (int j = 0; j < GAMEFIELDSIZE; j++) {
//				QPushButton button = new ColoButton("1", this);
//				QPushButton button = new QPushButton("Cell: "+i+","+j , this);			 	
				QPushButton button = new QPushButton( i + "," + j , this);
		        button.setSizePolicy(QSizePolicy.Policy.Expanding,
		        		QSizePolicy.Policy.Expanding);
		        
		        String color_backGround = null; 
//		        button.setStyleSheet("background-color: white");
		        buttonArray[i][j] = button;		        
		        
		        layout.addWidget(button, i, j);
			}
		}
	}

	@Override
	public void update(Event e) {

	}
	
}