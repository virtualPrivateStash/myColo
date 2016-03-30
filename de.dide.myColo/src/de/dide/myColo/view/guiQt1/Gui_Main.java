package de.dide.myColo.view.guiQt1;

import com.trolltech.qt.gui.*;

import de.dide.myColo.controller.impl.MainController;
import de.dide.myColo.model.game.GameState;
import sun.security.jca.GetInstance.Instance;

public class Gui_Main {

	private static Gui_Main instance;
	
	private Gui_Main(String[] args) {
        //QApplication.initialize(args);
		QApplication.initialize(args);
        QPushButton colo = new QPushButton("MyColo");
        QPushButton quit = new QPushButton("Quit");
 
        quit.resize(80, 40);
        quit.setFont(new QFont("Times", 18, QFont.Weight.Bold.value()));
        quit.clicked.connect(QApplication.instance(), "quit()");

        quit.setWindowTitle("Calling It Quits");
        quit.show();
        colo.show();
        QApplication.exec();

	}

	public static Gui_Main getInstance(String[] args) {
		if (instance == null) {
			instance = new Gui_Main(args);
		} 
		return instance;
	}
	
}
