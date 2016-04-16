package de.dide.myColo.view.guiQt1;

import com.trolltech.qt.gui.*;

import de.dide.myColo.controller.impl.MainController;
import de.dide.myColo.model.game.GameState;
import sun.security.jca.GetInstance.Instance;

public class Gui_Main {

	private static Gui_Main instance;
	
	private Gui_Main(String[] args) {
		QApplication.initialize(args);
		QPushButton button1 = new QPushButton("One");
	    QPushButton button2 = new QPushButton("Two");
	    QPushButton buttonQuit = new QPushButton("QUIT");
	    QVBoxLayout layout = new QVBoxLayout();
	    
	    buttonQuit.clicked.connect(QApplication.instance(), "quit()");
	    
	    layout.addWidget(button1);
	    layout.addWidget(button2);
	    layout.addWidget(buttonQuit);
	    QWidget window = new QWidget();
	    window.setLayout(layout);
	    window.setMinimumSize(400,400);
	    window.show();
	    QApplication.execStatic();
	}

	public static Gui_Main getInstance(String[] args) {
		if (instance == null) {
			instance = new Gui_Main(args);
		} 
		return instance;
	}
	
}