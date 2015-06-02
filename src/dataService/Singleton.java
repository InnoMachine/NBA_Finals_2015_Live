/**
 * puppy
 * Apr 26, 2015 7:12:47 PM
 * TODO
 */
package dataService;

import java.util.ArrayList;
import java.util.Observable;

import po.GamePO;
import po.PlayerPO;
import po.TeamPO;

public class Singleton extends Observable{//subject observed

	private static Singleton instance;
	
	private String testString;
	private ArrayList<String> liveText = new ArrayList<String>();
	private ArrayList<PlayerPO> hostPlayers = new ArrayList<PlayerPO>();
	private ArrayList<PlayerPO> guestPlayers = new ArrayList<PlayerPO>();
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

	public ArrayList<String> getLiveText() {
		return liveText;
	}

	public void setLiveText(ArrayList<String> liveText) {
		this.liveText = liveText;
		setChanged();
		notifyObservers(liveText);//sudo
	}

	public ArrayList<PlayerPO> getHostPlayers() {
		return hostPlayers;
	}

	public void setHostPlayers(ArrayList<PlayerPO> hostPlayers) {
		this.hostPlayers = hostPlayers;
		setChanged();
		notifyObservers(hostPlayers);
	}

	public ArrayList<PlayerPO> getGuestPlayers() {
		return guestPlayers;
	}

	public void setGuestPlayers(ArrayList<PlayerPO> guestPlayers) {
		this.guestPlayers = guestPlayers;
		setChanged();
		notifyObservers(guestPlayers);
	}

	public TeamPO getHostTeam() {
		return hostTeam;
	}

	public void setHostTeam(TeamPO hostTeam) {
		this.hostTeam = hostTeam;
		setChanged();
		notifyObservers(hostTeam);
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

	public String getTestString() {
		return testString;
	}

	public void setTestString(String testString) {
		this.testString = testString;
		setChanged();
		notifyObservers(testString);
	}
	
}
