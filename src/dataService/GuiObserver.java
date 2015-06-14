package dataService;

import java.util.Observable;
import java.util.Observer;

public class GuiObserver implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("i am notified by " + arg + "! update method running");
	}
	
	public static void main(String[] args){
		 
		GuiObserver guiObserver = new GuiObserver();
		Singleton.getInstance().addObserver(guiObserver);
//		new InfoDaoImpl().updateTestString("tttt");
	 }

}
