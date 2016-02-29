package de.dide.myColo.util.observer;

import de.dide.myColo.util.observer.Event;
import de.dide.myColo.util.observer.IObserver;

public interface IObservable {
	 void addObserver(IObserver s);
	 void removeObserver(IObserver s);
	 void removeAllObservers();
	 void notifyObservers();
	 void notifyObservers(Event e);
}
