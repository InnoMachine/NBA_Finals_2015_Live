package dataService;

import java.util.ArrayList;
import po.GamePO;
import po.LiveTextPO;
import po.PlayerPO;
import po.TeamPO;

public interface InfoDao {
	
	public LiveTextPO getLiveText();
	
	public GamePO getGame();	
	
	public ArrayList<PlayerPO> getHostPlayers();
	
	public ArrayList<PlayerPO> getGuestPlayers(); 
	
	public ArrayList<PlayerPO> getPlayers();
	
	public TeamPO getHostTeam();
	
	public TeamPO getGuestTeam();
	
	//----------------------data preparation part----------------------------------------
	
	public void addLiveText();
	
	public void clearLiveText();
	
	public void updateGame();
	
	public void updatePlayer(String name, PlayerPO player);
	
	public void updateHostTeam();
	
	public void updateGuestTeam();
	
	public void updateTeam(String teamAbbr);
	
	public void dataInit();
}
