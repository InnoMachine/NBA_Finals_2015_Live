/**
 * puppy
 * Apr 26, 2015 7:12:47 PM
 * TODO
 */
package dataService;

import java.util.Observable;
import po.GamePO;
import po.LiveTexts;
import po.Players;
import po.TeamPO;

public class Singleton extends Observable{//subject observed

	private static Singleton instance;
	
	private LiveTexts liveText1 = new LiveTexts();
	private LiveTexts liveText2 = new LiveTexts();
	private LiveTexts liveText3 = new LiveTexts();
	private LiveTexts liveText4 = new LiveTexts();
	private Players hostPlayers = new Players();
	private Players guestPlayers = new Players();
	private TeamPO hostTeam = new TeamPO();
	private TeamPO guestTeam = new TeamPO();
	private GamePO game = new GamePO();
	
	private Singleton() {//private constructor
		System.out.println("singleton database initialized");
	}
	
	public static Singleton getInstance() {//static method to get an instance
		if(instance == null) {
			instance = new Singleton();
		}
		return instance;
	}


	public TeamPO getHostTeam() {
		return hostTeam;
	}

	public void setHostTeam(TeamPO hostTeam) {
		this.hostTeam = hostTeam;
		setChanged();
		hostTeam.notifyObservers(hostTeam);
	}

	public TeamPO getGuestTeam() {
		return guestTeam;
	}

	public void setGuestTeam(TeamPO guestTeam) {
		this.guestTeam = guestTeam;
		setChanged();
		notifyObservers(guestTeam);
	}

	public GamePO getGame() {
		return game;
	}

	public void setGame(GamePO game) {
		this.game = game;
		setChanged();
		notifyObservers(game);
	}

	public static void setInstance(Singleton instance) {
		Singleton.instance = instance;
	}

	public Players getHostPlayers() {
		return hostPlayers;
	}

	public void setHostPlayers(Players hostPlayers) {
		this.hostPlayers = hostPlayers;
		setChanged();
		notifyObservers(hostPlayers);
	}

	public Players getGuestPlayers() {
		return guestPlayers;
	}

	public void setGuestPlayers(Players guestPlayers) {
		this.guestPlayers = guestPlayers;
		setChanged();
		notifyObservers(guestPlayers);
	}

	public LiveTexts getLiveText1() {
		return liveText1;
	}

	public void setLiveText1(LiveTexts liveText1) {
		this.liveText1 = liveText1;
		setChanged();
		notifyObservers(liveText1);
	}

	public LiveTexts getLiveText2() {
		return liveText2;
	}

	public void setLiveText2(LiveTexts liveText2) {
		this.liveText2 = liveText2;
		setChanged();
		notifyObservers(liveText2);
	}

	public LiveTexts getLiveText3() {
		return liveText3;
	}

	public void setLiveText3(LiveTexts liveText3) {
		this.liveText3 = liveText3;
		setChanged();
		notifyObservers(liveText3);
	}

	public LiveTexts getLiveText4() {
		return liveText4;
	}

	public void setLiveText4(LiveTexts liveText4) {
		this.liveText4 = liveText4;
		setChanged();
		notifyObservers(liveText4);
	}

	
}
