package ui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;

import dataService.MyNBALives;
import dataService.Singleton;

public class LiveFrame extends JFrame {

	private static final long serialVersionUID = 1L;
//	static StartPanel startPanel = null;
	public LiveInfoPanel liveInfoPanel;

	public static String currentPanel = "";

	public static void main(String[] args) {

		LiveFrame lf = new LiveFrame();
		lf.setVisible(true);
		LiveInfoPanel.LiveText1Observer livetext1ob = new LiveInfoPanel(lf).new LiveText1Observer();
		LiveInfoPanel.LiveText2Observer livetext2ob = new LiveInfoPanel(lf).new LiveText2Observer();
		LiveInfoPanel.LiveText3Observer livetext3ob = new LiveInfoPanel(lf).new LiveText3Observer();
		LiveInfoPanel.LiveText4Observer livetext4ob = new LiveInfoPanel(lf).new LiveText4Observer();
		LiveInfoPanel.GameObserver gameob = new LiveInfoPanel(lf).new GameObserver();
		LiveInfoPanel.GuestPlayersObserver guestplayersob = new LiveInfoPanel(lf).new GuestPlayersObserver();
		LiveInfoPanel.HostPlayersObserver hostplayerob = new LiveInfoPanel(lf).new HostPlayersObserver();
		LiveInfoPanel.GuestTeamObserver guestteamob = new LiveInfoPanel(lf).new GuestTeamObserver();
		LiveInfoPanel.HostTeamObserver hostteamob = new LiveInfoPanel(lf).new HostTeamObserver();
		
		Singleton.getInstance().getLiveText1().addObserver(livetext1ob);
		Singleton.getInstance().getLiveText2().addObserver(livetext2ob);
		Singleton.getInstance().getLiveText3().addObserver(livetext3ob);
		Singleton.getInstance().getLiveText4().addObserver(livetext4ob);
		Singleton.getInstance().getGame().addObserver(gameob);;
		Singleton.getInstance().getGuestPlayers().addObserver(guestplayersob);
		Singleton.getInstance().getHostPlayers().addObserver(hostplayerob);
		Singleton.getInstance().getGuestTeam().addObserver(guestteamob);;
		Singleton.getInstance().getHostTeam().addObserver(hostteamob);
		
		while(true) {
			MyNBALives.live();
		}
		
	}

	public LiveFrame() {
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle bounds = new Rectangle(screenSize);
		this.setBounds(bounds);

		liveInfoPanel = new LiveInfoPanel(this);
		this.add(liveInfoPanel);

	}
}
