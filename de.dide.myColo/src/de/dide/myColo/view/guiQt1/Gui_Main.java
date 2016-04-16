package de.dide.myColo.view.guiQt1;

import com.trolltech.qt.gui.*;

import de.dide.myColo.controller.impl.MainController;
import de.dide.myColo.model.game.GameState;
import sun.applet.Main;
import sun.security.jca.GetInstance.Instance;
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

public class Gui_Main extends QWidget{

	private static Gui_Main instance;
	protected QGridLayout layout;
	private static String styleSheet;
	private static int gameFieldSize = 12;
	
	private Gui_Main(String[] args)  throws IOException {
		File file = new File("/home/xy/PROG/workspaces/java/JambiTest1/src/stylesheet.qss");
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
		
		this.resize(new QSize(900, 900));
		layout = new QGridLayout(this);
		layout.setSpacing(0);
		layout.setContentsMargins(0, 0, 0, 0);
//	    QSizePolicy sizePolicy = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.resolve(1), com.trolltech.qt.gui.QSizePolicy.Policy.resolve(1));
//	    sizePolicy.setHorizontalStretch((byte)0);
//	    sizePolicy.setVerticalStretch((byte)0);
//	    sizePolicy.setHeightForWidth(button1.sizePolicy().hasHeightForWidth());
//	    button1.setSizePolicy(sizePolicy);
//		layout.setColumnStretch(10, 1);

		for (int i = 0; i < gameFieldSize; i++) {
			for (int j = 0; j < gameFieldSize; j++) {
//				QPushButton button = new ColoButton("1", this);
			 	QPushButton button = new QPushButton("Cell: "+i+","+j , this);
		        button.setSizePolicy(QSizePolicy.Policy.Expanding, QSizePolicy.Policy.Expanding);
		        layout.addWidget(button, i, j);
			}
		}
		this.show();
		QApplication.execStatic();
	}

	
	public static Gui_Main getInstance(String[] args) {
		if (instance == null) {
			try {
				instance = new Gui_Main(args);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		return instance;
	}
	
}