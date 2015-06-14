/**
 * puppy
 * Apr 26, 2015 7:12:47 PM
 * TODO
 */
package dataService;

import java.util.ArrayList;
import java.util.Observable;

import po.GamePO;
import po.LiveTextPO;
import po.PlayerPO;
import po.TeamPO;

public class Singleton extends Observable{//subject observed

	private static Singleton instance;
	
	private String testString;
	private ArrayList<LiveTextPO> liveText1 = new ArrayList<LiveTextPO>();
	private ArrayList<LiveTextPO> liveText2 = new ArrayList<LiveTextPO>();
	private ArrayList<LiveTextPO> liveText3 = new ArrayList<LiveTextPO>();
	private ArrayList<LiveTextPO> liveText4 = new ArrayList<LiveTextPO>();
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

	
	public ArrayList<LiveTextPO> getLiveText1() {
		return liveText1;
	}

	public void setLiveText1(ArrayList<LiveTextPO> liveText1) {
		this.liveText1 = liveText1;
		setChanged();
		notifyObservers(liveText1);//sudo
	}

	public ArrayList<LiveTextPO> getLiveText2() {
		return liveText2;
	}

	public void setLiveText2(ArrayList<LiveTextPO> liveText2) {
		this.liveText2 = liveText2;
		setChanged();
		notifyObservers(liveText2);//sudo
	}

	public ArrayList<LiveTextPO> getLiveText3() {
		return liveText3;
	}

	public void setLiveText3(ArrayList<LiveTextPO> liveText3) {
		this.liveText3 = liveText3;
		setChanged();
		notifyObservers(liveText3);//sudo
	}

	public ArrayList<LiveTextPO> getLiveText4() {
		return liveText4;
	}

	public void setLiveText4(ArrayList<LiveTextPO> liveText4) {
		this.liveText4 = liveText4;
		setChanged();
		notifyObservers(liveText4);//sudo
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
