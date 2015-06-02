package dataService;

import java.util.Observable;
import java.util.Observer;

public class GuiObserver implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("i am notified by " + arg + "! update method running");
	}
	
	public static void main(String[] args){
		 
		Singleton dataObserved = Singleton.getInstance();
		System.out.println("1------");
		GuiObserver guiObserver = new GuiObserver();
		dataObserved.addObserver(guiObserver);
		new InfoDaoImpl().updateTestString("tttt");
	 }

}
