package dataService;

import java.util.ArrayList;

import po.GamePO;
import po.LiveTextPO;
import po.PlayerPO;
import po.TeamPO;

public class InfoDaoImpl implements InfoDao {

	Singleton singleton = Singleton.getInstance();
	
	@Override
	public LiveTextPO getLiveText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GamePO getGame() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PlayerPO> getHostPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PlayerPO> getGuestPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PlayerPO> getPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamPO getHostTeam() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamPO getGuestTeam() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addLiveText() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearLiveText() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePlayer(String name, PlayerPO player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateHostTeam() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateGuestTeam() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTeam(String teamAbbr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dataInit() {
		// TODO Auto-generated method stub
		
	}

}