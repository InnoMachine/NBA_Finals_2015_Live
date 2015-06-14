package po;

import java.util.ArrayList;
import java.util.Observable;

public class Players extends Observable{

	private ArrayList<PlayerPO> players;
	
	public Players() {
		this.players = null;
	}

	public Players(ArrayList<PlayerPO> players) {
		this.players = players;
	}
	
	public ArrayList<PlayerPO> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<PlayerPO> players) {
		this.players = players;
	}

}
