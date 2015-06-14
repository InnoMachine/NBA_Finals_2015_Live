package dataService;

import java.util.ArrayList;
import po.GamePO;
import po.LiveTextPO;
import po.PlayerPO;
import po.TeamPO;

public interface InfoDao {
	
	public ArrayList<LiveTextPO> getLiveText1();
	
	public ArrayList<LiveTextPO> getLiveText2();
	
	public ArrayList<LiveTextPO> getLiveText3();
	
	public ArrayList<LiveTextPO> getLiveText4();
	
	public GamePO getGame();	
	
	public ArrayList<PlayerPO> getHostPlayers();
	
	public ArrayList<PlayerPO> getGuestPlayers(); 
	
	public TeamPO getHostTeam();
	
	public TeamPO getGuestTeam();
	
}
