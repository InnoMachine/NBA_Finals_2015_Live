package dataService;

import java.util.ArrayList;

import po.GamePO;
import po.LiveTextPO;
import po.PlayerPO;
import po.TeamPO;

public class InfoDaoImpl implements InfoDao {

	Singleton singleton = Singleton.getInstance();

	@Override
	public ArrayList<LiveTextPO> getLiveText1() {
		return singleton.getLiveText1().getLiveTexts();
	}

	@Override
	public ArrayList<LiveTextPO> getLiveText2() {
		return singleton.getLiveText2().getLiveTexts();
	}

	@Override
	public ArrayList<LiveTextPO> getLiveText3() {
		return singleton.getLiveText3().getLiveTexts();
	}

	@Override
	public ArrayList<LiveTextPO> getLiveText4() {
		return singleton.getLiveText4().getLiveTexts();
	}

	@Override
	public GamePO getGame() {
		return singleton.getGame();
	}

	@Override
	public ArrayList<PlayerPO> getHostPlayers() {
		return singleton.getHostPlayers().getPlayers();
	}

	@Override
	public ArrayList<PlayerPO> getGuestPlayers() {
		return singleton.getGuestPlayers().getPlayers();
	}

	@Override
	public TeamPO getHostTeam() {
		return singleton.getHostTeam();
	}

	@Override
	public TeamPO getGuestTeam() {
		return singleton.getGuestTeam();
	}
	
	
	
}