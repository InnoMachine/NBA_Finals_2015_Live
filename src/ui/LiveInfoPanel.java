package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import dataService.InfoDaoImpl;
import po.GamePO;
import po.LiveTextPO;
import po.PlayerPO;
import po.TeamPO;

public class LiveInfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	static GamePO gamePO = null;
	JFrame mainFrame;
	InfoDaoImpl infoDaoImpl = new InfoDaoImpl();

	String currentState = "live";
	static ArrayList<LiveTextPO> liveTextList = null;
	static ArrayList<TeamPO> teamDataList = null;
	static ArrayList<PlayerPO> guestPlayerDataList = null;
	static ArrayList<PlayerPO> hostPlayerDataList = null;

	TeamPO guestTeamPo = null;
	TeamPO hostTeamPo = null;

	private JTable liveTable;
	private JScrollPane liveScrollPane;
	Vector<Vector<String>> liveRowData;

	static int X;
	static int Y;

	JLabel bgLabel;
	private JButton guestTeambtn;
	private JButton hostTeambtn;
	private JTextField txtGTPoint;
	private JTextField txtHTPoint;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField guestTeamtF;
	private JTextField guestTeam1;
	private JTextField guestTeam2;
	private JTextField guestTeam3;
	private JTextField guestTeam4;
	private JTextField guestScoreSum;
	private JTextField txtState;
	private JTextField hostTeamtF;
	private JTextField hostScore1;
	private JTextField hostScore2;
	private JTextField hostScore3;
	private JTextField hostScore4;
	private JTextField hostScoreSum;
	private JLabel courtlbl;
	private JLabel liveTextlbl;
	private JButton btnG1;
	private JButton btnG2;
	private JButton btnG3;
	private JButton btnG4;
	private JButton btnG5;
	private JButton btnH1;
	private JButton btnH2;
	private JButton btnH3;
	private JButton btnH4;
	private JButton btnH5;

	private JLabel summarylbl;
	JLabel gameInfolbl;

	private JLabel datalbl;
	private JLabel guestTeamDatalbl;
	private JLabel hostTeamDatalbl;
	private String[] compareCriterias;

	Vector<Vector<String>> guestRowData;
	Vector<String> guestColumn;
	private JTable guestTable;
	private JScrollPane guestScrollPane;
	Vector<Vector<String>> hostRowData;
	Vector<String> hostColumn;
	private JTable hostTable;
	private JScrollPane hostScrollPane;

	public LiveInfoPanel(JFrame mainFrame) {
		this.mainFrame = mainFrame;
		X = mainFrame.getWidth();
		Y = mainFrame.getHeight();
		this.setBounds(0, 0, X, Y);

		this.setVisible(true);
		this.setLayout(null);

		bgLabel = new JLabel();
		bgLabel.setBounds(0, 0, X, Y);

		ImageIcon bg = new ImageIcon(new ImageIcon("Image/screeningPlayer.png")
				.getImage().getScaledInstance(this.getWidth(),
						this.getHeight(), Image.SCALE_SMOOTH));
		bgLabel.setIcon(bg);
		this.add(bgLabel);

		JButton minimize = new JButton();
		ImageIcon minimizeIcon = new ImageIcon(new ImageIcon(
				"Image/minimizeIcon.png").getImage().getScaledInstance(X / 25,
				X / 25, Image.SCALE_SMOOTH));
		minimize.setBounds(18 * X / 20, Y * 15 / 768, X / 25, X / 25);
		minimize.setIcon(minimizeIcon);
		minimize.setOpaque(false);
		minimize.setContentAreaFilled(false);
		minimize.setBorderPainted(false);
		minimize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mainFrame.setExtendedState(JFrame.ICONIFIED);
			}
		});

		bgLabel.add(minimize);

		JButton close = new JButton();
		ImageIcon closeIcon = new ImageIcon(
				new ImageIcon("Image/closeIcon.png").getImage()
						.getScaledInstance(X / 25, X / 25, Image.SCALE_SMOOTH));
		close.setBounds(19 * X / 20, Y * 15 / 768, X / 25, X / 25);
		close.setIcon(closeIcon);
		close.setOpaque(false);
		close.setContentAreaFilled(false);
		close.setBorderPainted(false);
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mainFrame.dispose();
			}
		});
		bgLabel.add(close);

		mainFrame.getContentPane().add(this);

		int spaceX = 565;
		int spaceY = 50;
		int tempX = 30;
		int tempY = 25;
		textField = new MyTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("球队");
		textField.setBounds(spaceX, spaceY, 60, tempY);
		textField.setOpaque(false);
		textField.setBorder(BorderFactory.createEmptyBorder());
		bgLabel.add(textField);

		textField_1 = new MyTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setText("1");
		textField_1.setBounds(spaceX + 60, spaceY, tempX, tempY);
		textField_1.setOpaque(false);
		textField_1.setBorder(BorderFactory.createEmptyBorder());
		bgLabel.add(textField_1);

		textField_2 = new MyTextField();
		textField_2.setText("2");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setBounds(spaceX + 60 + tempX, spaceY, tempX, tempY);
		textField_2.setOpaque(false);
		textField_2.setBorder(BorderFactory.createEmptyBorder());
		bgLabel.add(textField_2);

		textField_3 = new MyTextField();
		textField_3.setText("3");
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setBounds(spaceX + 60 + 2 * tempX, spaceY, tempX, tempY);
		textField_3.setOpaque(false);
		textField_3.setBorder(BorderFactory.createEmptyBorder());
		bgLabel.add(textField_3);

		textField_4 = new MyTextField();
		textField_4.setText("4");
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setBounds(spaceX + 60 + 3 * tempX, spaceY, tempX, tempY);
		textField_4.setOpaque(false);
		textField_4.setBorder(BorderFactory.createEmptyBorder());
		bgLabel.add(textField_4);

		textField_5 = new MyTextField();
		textField_5.setText("总分");
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setBounds(spaceX + 60 + 4 * tempX, spaceY, 60, tempY);
		textField_5.setOpaque(false);
		textField_5.setBorder(BorderFactory.createEmptyBorder());
		bgLabel.add(textField_5);

		JButton startingbtn = new ChooseButton("文字直播");
		startingbtn.setBounds(X * 450 / 1366, Y * 155 / 768, X * 155 / 1366,
				Y * 30 / 768);
		startingbtn.addActionListener(e -> {
			currentState = "live";
			live(liveTextList);
			VisionController("live");
		});
		bgLabel.add(startingbtn);

		JButton summarybtn = new ChooseButton("球队数据");
		summarybtn.setBounds(X * 605 / 1366, Y * 155 / 768, X * 155 / 1366,
				Y * 30 / 768);
		summarybtn.addActionListener(e -> {
			currentState = "teamDataLive";
			teamDataLive(guestTeamPo, hostTeamPo);
			VisionController("teamDataLive");
		});
		bgLabel.add(summarybtn);

		JButton databtn = new ChooseButton("球员数据");
		databtn.setBounds(X * 760 / 1366, Y * 155 / 768, X * 155 / 1366,
				Y * 30 / 768);
		databtn.addActionListener(e -> {
			currentState = "playerDataLive";
			playerDataLive(guestPlayerDataList, hostPlayerDataList);
			VisionController("PlayerDataLive");
		});
		bgLabel.add(databtn);

		// -------------------------------获取初始gamePO---------------------------------
		gamePO = infoDaoImpl.getGame();
		// --------------------------------获取初始主客队球员阵容---------------------------
		guestPlayerDataList = infoDaoImpl.getGuestPlayers();
		hostPlayerDataList = infoDaoImpl.getHostPlayers();
		// ------------------------------获取初始LiveText--------------------------------
		// liveTextList=infoDaoImpl;

		gameDataLive(gamePO);

		currentState="live";
		live(liveTextList);
		VisionController("live");
		mainFrame.getContentPane().add(this);

	}

	public void gameDataLive(GamePO gamePO) {

		
		
		int spaceX = 565;
		int spaceY = 50;
		int tempX = 30;
		int tempY = 25;

		guestTeambtn = new JButton();
		guestTeambtn.setHorizontalAlignment(SwingConstants.CENTER);

		guestTeambtn.setBounds(X * 230 / 1366, Y * 34 / 768, X * 160 / 1366,
				Y * 160 / 768);
		ImageIcon teamImg1 = new ImageIcon(new ImageIcon("CSEdata/teams_png/"
				+ gamePO.getGuestTeam() + ".png").getImage().getScaledInstance(
				X * 160 / 1366, Y * 160 / 768, Image.SCALE_AREA_AVERAGING));
		guestTeambtn.setIcon(teamImg1);
		guestTeambtn.setContentAreaFilled(false);
		guestTeambtn.setBorderPainted(false);
		guestTeambtn.setOpaque(false);

		bgLabel.add(guestTeambtn);

		hostTeambtn = new JButton();
		hostTeambtn.setHorizontalAlignment(SwingConstants.CENTER);
		hostTeambtn.setText(gamePO.getHomeTeam());
		hostTeambtn.setBounds(X * 970 / 1366, Y * 34 / 768, X * 160 / 1366,
				Y * 160 / 768);
		ImageIcon teamImg2 = new ImageIcon(new ImageIcon("CSEdata/teams_png/"
				+ gamePO.getHomeTeam() + ".png").getImage().getScaledInstance(
				X * 160 / 1366, Y * 160 / 768, Image.SCALE_AREA_AVERAGING));
		hostTeambtn.setIcon(teamImg2);
		hostTeambtn.setContentAreaFilled(false);
		hostTeambtn.setBorderPainted(false);
		hostTeambtn.setOpaque(false);
		bgLabel.add(hostTeambtn);

		txtState = new MyTextField();
		// --------------------------------比赛进行情况------------------------------------
		// txtState.setText(gamePO.get);
		txtState.setHorizontalAlignment(SwingConstants.CENTER);
		txtState.setBounds(X * 651 / 1366, Y * 25 / 768, X * 66 / 1366,
				Y * 25 / 768);
		txtState.setFont(new Font("幼圆", 1, 15));
		txtState.setBorder(null);
		bgLabel.add(txtState);

		txtGTPoint = new MyTextField();
		txtGTPoint.setText(String.valueOf(gamePO.getScoreOverall()
				.getGuestScore()));
		txtGTPoint.setHorizontalAlignment(SwingConstants.CENTER);
		txtGTPoint.setBounds(X * 430 / 1366, Y * 67 / 768, X * 120 / 1366,
				Y * 60 / 768);
		txtGTPoint.setFont(new Font("微软雅黑", 1, 40));
		bgLabel.add(txtGTPoint);

		txtHTPoint = new MyTextField();
		txtHTPoint.setHorizontalAlignment(SwingConstants.CENTER);
		txtHTPoint.setText(String.valueOf(gamePO.getScoreOverall()
				.getHomeScore()));
		txtHTPoint.setBounds(X * 830 / 1366, Y * 67 / 768, X * 120 / 1366,
				Y * 60 / 768);
		txtHTPoint.setFont(new Font("微软雅黑", 1, 40));
		bgLabel.add(txtHTPoint);

		guestTeamtF = new MyTextField();
		guestTeamtF.setText(gamePO.getGuestTeam());
		guestTeamtF.setHorizontalAlignment(SwingConstants.CENTER);
		guestTeamtF.setColumns(X * 10 / 1366);
		guestTeamtF.setBounds(spaceX, spaceY + tempY, 60, tempY);
		guestTeamtF.setBackground(Color.DARK_GRAY);
		guestTeamtF.setOpaque(true);
		bgLabel.add(guestTeamtF);

		guestTeam1 = new MyTextField();
		guestTeam1
				.setText(String.valueOf(gamePO.getScore1st().getGuestScore()));
		guestTeam1.setBackground(Color.DARK_GRAY);
		guestTeam1.setOpaque(true);
		guestTeam1.setHorizontalAlignment(SwingConstants.CENTER);
		guestTeam1.setBounds(spaceX + 60, spaceY + tempY, tempX, tempY);
		bgLabel.add(guestTeam1);

		guestTeam2 = new MyTextField();
		guestTeam2
				.setText(String.valueOf(gamePO.getScore2nd().getGuestScore()));
		guestTeam2.setBackground(Color.DARK_GRAY);
		guestTeam2.setOpaque(true);
		guestTeam2.setHorizontalAlignment(SwingConstants.CENTER);
		guestTeam2.setBounds(spaceX + 60 + tempX, spaceY + tempY, tempX, tempY);
		bgLabel.add(guestTeam2);

		guestTeam3 = new MyTextField();
		guestTeam3
				.setText(String.valueOf(gamePO.getScore3rd().getGuestScore()));
		guestTeam3.setBackground(Color.DARK_GRAY);
		guestTeam3.setOpaque(true);
		guestTeam3.setHorizontalAlignment(SwingConstants.CENTER);
		guestTeam3.setBounds(spaceX + 60 + 2 * tempX, spaceY + tempY, tempX,
				tempY);
		bgLabel.add(guestTeam3);

		guestTeam4 = new MyTextField();
		guestTeam4
				.setText(String.valueOf(gamePO.getScore4th().getGuestScore()));
		guestTeam4.setBackground(Color.DARK_GRAY);
		guestTeam4.setOpaque(true);
		guestTeam4.setHorizontalAlignment(SwingConstants.CENTER);
		guestTeam4.setBounds(spaceX + 60 + 3 * tempX, spaceY + tempY, tempX,
				tempY);
		bgLabel.add(guestTeam4);

		guestScoreSum = new MyTextField();
		guestScoreSum.setText(String.valueOf(gamePO.getScoreOverall()
				.getGuestScore()));
		guestScoreSum.setBackground(Color.DARK_GRAY);
		guestScoreSum.setOpaque(true);
		guestScoreSum.setHorizontalAlignment(SwingConstants.CENTER);
		guestScoreSum.setBounds(spaceX + 60 + 4 * tempX, spaceY + tempY, 60,
				tempY);
		bgLabel.add(guestScoreSum);

		hostTeamtF = new MyTextField();
		hostTeamtF.setText(gamePO.getHomeTeam());
		hostTeamtF.setHorizontalAlignment(SwingConstants.CENTER);
		hostTeamtF.setBounds(spaceX, spaceY + 2 * tempY, 60, tempY);
		hostTeamtF.setBackground(Color.GRAY);
		hostTeamtF.setOpaque(true);

		bgLabel.add(hostTeamtF);

		hostScore1 = new MyTextField();
		hostScore1.setText(String.valueOf(gamePO.getScore1st().getHomeScore()));
		hostScore1.setBackground(Color.GRAY);
		hostScore1.setOpaque(true);
		hostScore1.setHorizontalAlignment(SwingConstants.CENTER);
		hostScore1.setBounds(spaceX + 60, spaceY + 2 * tempY, tempX, tempY);
		bgLabel.add(hostScore1);

		hostScore2 = new MyTextField();
		hostScore2.setText(String.valueOf(gamePO.getScore2nd().getHomeScore()));
		hostScore2.setBackground(Color.GRAY);
		hostScore2.setOpaque(true);
		hostScore2.setHorizontalAlignment(SwingConstants.CENTER);
		hostScore2.setBounds(spaceX + 60 + tempX, spaceY + 2 * tempY, tempX,
				tempY);
		bgLabel.add(hostScore2);

		hostScore3 = new MyTextField();
		hostScore3.setText(String.valueOf(gamePO.getScore3rd().getHomeScore()));
		hostScore3.setBackground(Color.GRAY);
		hostScore3.setOpaque(true);
		hostScore3.setHorizontalAlignment(SwingConstants.CENTER);
		hostScore3.setBounds(spaceX + 60 + 2 * tempX, spaceY + 2 * tempY,
				tempX, tempY);
		bgLabel.add(hostScore3);

		hostScore4 = new MyTextField();
		hostScore4.setText(String.valueOf(gamePO.getScore4th().getHomeScore()));
		hostScore4.setBackground(Color.GRAY);
		hostScore4.setOpaque(true);
		hostScore4.setHorizontalAlignment(SwingConstants.CENTER);
		hostScore4.setBounds(spaceX + 60 + 3 * tempX, spaceY + 2 * tempY,
				tempX, tempY);
		bgLabel.add(hostScore4);

		hostScoreSum = new MyTextField();
		hostScoreSum.setText(String.valueOf(gamePO.getScoreOverall()
				.getHomeScore()));
		hostScoreSum.setBackground(Color.GRAY);
		hostScoreSum.setOpaque(true);
		hostScoreSum.setHorizontalAlignment(SwingConstants.CENTER);
		hostScoreSum.setBounds(spaceX + 60 + 4 * tempX, spaceY + 2 * tempY, 60,
				tempY);
		bgLabel.add(hostScoreSum);

		VisionController("live");
		
	}

	
	public void courtLive(GamePO gamePO){
		if (courtlbl != null) {
			courtlbl.setVisible(false);
		}
		courtlbl = new MyLabel();
		courtlbl.setHorizontalAlignment(SwingConstants.CENTER);
		courtlbl.setBounds(X * 0 / 1366, Y * 234 / 768, X * 850 / 1366,
				Y * 386 / 768);
		ImageIcon buttonIcon = new ImageIcon(new ImageIcon("CSEdata/court_png/"
				+ gamePO.getHomeTeam() + "_court.png").getImage()
				.getScaledInstance(X * 850 / 1366, Y * 386 / 768,
						Image.SCALE_SMOOTH));
		courtlbl.setIcon(buttonIcon);

		btnG1 = new MyButton();
		btnG1.setBounds(X * 340 / 1366, Y * 165 / 768, X * 60 / 1366,
				Y * 60 / 768);
		ImageIcon playerPortraitG1 = new ImageIcon(new ImageIcon(
				"CSEdata/players/portrait/"
						+ gamePO.getGuestPlayersNameList().get(0) + ".png")
				.getImage().getScaledInstance(X * 60 / 1366, Y * 60 / 768,
						Image.SCALE_SMOOTH));
		btnG1.setOpaque(false);
		btnG1.setIcon(playerPortraitG1);
		courtlbl.add(btnG1);

		btnG2 = new MyButton();
		btnG2.setBounds(X * 260 / 1366, Y * 45 / 768, X * 60 / 1366,
				Y * 60 / 768);
		ImageIcon playerPortraitG2 = new ImageIcon(new ImageIcon(
				"CSEdata/players/portrait/"
						+ gamePO.getGuestPlayersNameList().get(1) + ".png")
				.getImage().getScaledInstance(X * 60 / 1366, Y * 60 / 768,
						Image.SCALE_AREA_AVERAGING));
		btnG2.setIcon(playerPortraitG2);
		btnG2.setOpaque(false);
		courtlbl.add(btnG2);

		btnG3 = new MyButton();
		btnG3.setBounds(X * 100 / 1366, Y * 295 / 768, X * 60 / 1366,
				Y * 60 / 768);
		ImageIcon playerPortraitG3 = new ImageIcon(new ImageIcon(
				"CSEdata/players/portrait/"
						+ gamePO.getGuestPlayersNameList().get(2) + ".png")
				.getImage().getScaledInstance(X * 60 / 1366, Y * 60 / 768,
						Image.SCALE_AREA_AVERAGING));
		btnG3.setIcon(playerPortraitG3);
		btnG3.setOpaque(false);
		courtlbl.add(btnG3);

		btnG4 = new MyButton();
		btnG4.setBounds(X * 130 / 1366, Y * 123 / 768, X * 60 / 1366,
				Y * 60 / 768);
		ImageIcon playerPortraitG4 = new ImageIcon(new ImageIcon(
				"CSEdata/players/portrait/"
						+ gamePO.getGuestPlayersNameList().get(3) + ".png")
				.getImage().getScaledInstance(X * 60 / 1366, Y * 60 / 768,
						Image.SCALE_AREA_AVERAGING));
		btnG4.setIcon(playerPortraitG4);
		btnG4.setOpaque(false);
		courtlbl.add(btnG4);

		btnG5 = new MyButton();
		btnG5.setBounds(X * 260 / 1366, Y * 270 / 768, X * 60 / 1366,
				Y * 60 / 768);
		ImageIcon playerPortraitG5 = new ImageIcon(new ImageIcon(
				"CSEdata/players/portrait/"
						+ gamePO.getGuestPlayersNameList().get(4) + ".png")
				.getImage().getScaledInstance(X * 60 / 1366, Y * 60 / 768,
						Image.SCALE_AREA_AVERAGING));
		btnG5.setIcon(playerPortraitG5);
		btnG5.setOpaque(false);
		courtlbl.add(btnG5);

		btnH1 = new MyButton();
		btnH1.setBounds(X * 440 / 1366, Y * 165 / 768, X * 60 / 1366,
				Y * 60 / 768);
		ImageIcon playerPortraitH1 = new ImageIcon(new ImageIcon(
				"CSEdata/players/portrait/"
						+ gamePO.getHomePlayersNameList().get(0) + ".png")
				.getImage().getScaledInstance(X * 60 / 1366, Y * 60 / 768,
						Image.SCALE_AREA_AVERAGING));
		btnH1.setIcon(playerPortraitH1);
		btnH1.setOpaque(false);
		courtlbl.add(btnH1);

		btnH2 = new MyButton();
		btnH2.setBounds(X * 550 / 1366, Y * 285 / 768, X * 60 / 1366,
				Y * 60 / 768);
		ImageIcon playerPortraitH2 = new ImageIcon(new ImageIcon(
				"CSEdata/players/portrait/"
						+ gamePO.getHomePlayersNameList().get(1) + ".png")
				.getImage().getScaledInstance(X * 60 / 1366, Y * 60 / 768,
						Image.SCALE_AREA_AVERAGING));
		btnH2.setIcon(playerPortraitH2);
		btnH2.setOpaque(false);
		courtlbl.add(btnH2);

		btnH3 = new MyButton();
		btnH3.setBounds(X * 640 / 1366, Y * 35 / 768, X * 60 / 1366,
				Y * 60 / 768);
		ImageIcon playerPortraitH3 = new ImageIcon(new ImageIcon(
				"CSEdata/players/portrait/"
						+ gamePO.getHomePlayersNameList().get(2) + ".png")
				.getImage().getScaledInstance(X * 60 / 1366, Y * 60 / 768,
						Image.SCALE_AREA_AVERAGING));
		btnH3.setIcon(playerPortraitH3);
		btnH3.addActionListener(e -> {
			System.out.println("click H3!!!");
		});
		btnH3.setOpaque(false);
		courtlbl.add(btnH3);

		btnH4 = new MyButton();
		btnH4.setBounds(X * 670 / 1366, Y * 207 / 768, X * 60 / 1366,
				Y * 60 / 768);
		ImageIcon playerPortraitH4 = new ImageIcon(new ImageIcon(
				"CSEdata/players/portrait/"
						+ gamePO.getHomePlayersNameList().get(3) + ".png")
				.getImage().getScaledInstance(X * 60 / 1366, Y * 60 / 768,
						Image.SCALE_AREA_AVERAGING));
		btnH4.setIcon(playerPortraitH4);
		btnH4.setOpaque(false);
		courtlbl.add(btnH4);

		btnH5 = new MyButton();
		btnH5.setBounds(X * 505 / 1366, Y * 60 / 768, X * 60 / 1366,
				Y * 60 / 768);
		ImageIcon playerPortraitH5 = new ImageIcon(new ImageIcon(
				"CSEdata/players/portrait/"
						+ gamePO.getHomePlayersNameList().get(4) + ".png")
				.getImage().getScaledInstance(X * 60 / 1366, Y * 60 / 768,
						Image.SCALE_AREA_AVERAGING));
		btnH5.setIcon(playerPortraitH5);
		courtlbl.add(btnH5);

		bgLabel.add(courtlbl);
		
		
	}
	
	
	public void live(ArrayList<LiveTextPO> liveTextList) {


		// ---------------------------------------Steve
		// Jin(在场队员）liveText里须加属性：是否为替换队员，我才能替换掉在场队员！！！--------------------------------------------

		if (liveTextlbl != null) {
			liveTextlbl.setVisible(false);
		}
		
		if (liveScrollPane != null) {
			liveScrollPane.setVisible(false);
		}
		
		liveTextlbl = new MyLabel();
		liveTextlbl.setHorizontalAlignment(SwingConstants.CENTER);
		liveTextlbl.setBounds(X * 210 / 1366, Y * 670 / 768, X * 430 / 1366,
				Y * 30 / 768);
		liveTextlbl.setOpaque(false);
		// liveTextlbl.setBackground(Color.WHITE);
		liveTextlbl.setVisible(true);
		if (liveTextList != null) {
			liveTextlbl.setText(liveTextList.get(0).getText());
		}

		ArrayList<GamePO> gamePOs = new ArrayList<GamePO>();
		for (int i = 0; i < 10; i++) {
			// gamePOs.add(game_BS.getAllGames().get(i));
		}
		if (liveRowData == null) {
			liveRowData = new Vector<Vector<String>>();
		} else {
			liveRowData.clear();
		}

		Vector<String> a;
		if (liveTextList != null) {
			for (int i = 0; i < liveTextList.size(); i++) {
				a = new Vector<String>();
				a.add(liveTextList.get(i).getRemainingTime());
				a.add(String.valueOf(liveTextList.get(i).getTeamAbbr()));
				a.add(liveTextList.get(i).getPlayerName());
				a.add(liveTextList.get(i).getText());
				liveRowData.add(a);
			}
		}
		Vector<String> column = new Vector<String>();
		column.add("时间");
		column.add("球队");
		column.add("参与球员");
		column.add("文字直播");
		/*
		 * DefaultTableModel dtm = new DefaultTableModel(liveRowData, column) {
		 * 
		 * private static final long serialVersionUID = 1L;
		 * 
		 * public boolean isCellEditable(int row, int column) { return false; }
		 * };
		 * 
		 * if (liveTable != null) { liveTable.setVisible(false); } liveTable =
		 * new JTable(dtm);
		 */
		liveTable = new JTable(liveRowData, column);
		JTableHeader header = liveTable.getTableHeader();
		header.setDefaultRenderer(new DefaultTableCellRenderer() {

			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				Component cell = super.getTableCellRendererComponent(table,
						value, isSelected, hasFocus, row, column);
				cell.setBackground(Color.DARK_GRAY);
				return cell;
			}

		});
		((DefaultTableCellRenderer) header.getDefaultRenderer())
				.setPreferredSize(new Dimension(550, 50));
		((DefaultTableCellRenderer) header.getDefaultRenderer())
				.setHorizontalAlignment(JLabel.CENTER);
		MyTableRenderer r3 = new MyTableRenderer();
		r3.setHorizontalAlignment(JLabel.CENTER);
		liveTable.setDefaultRenderer(Object.class, r3);

		liveTable.setRowHeight(Y * 30 / 768);
		liveTable.getColumnModel().getColumn(0)
				.setPreferredWidth(X * 70 / 1366);
		liveTable.getColumnModel().getColumn(1)
				.setPreferredWidth(X * 40 / 1366);
		liveTable.getColumnModel().getColumn(2)
				.setPreferredWidth(X * 60 / 1366);
		liveTable.getColumnModel().getColumn(3)
				.setPreferredWidth(X * 315 / 1366);
		liveTable.setForeground(Color.WHITE);
		liveTable.setVisible(true);
		liveTable.setCellSelectionEnabled(true);
		liveTable.setOpaque(false);
		if (liveScrollPane != null) {
			liveScrollPane.setVisible(false);
		}
		liveScrollPane = new JScrollPane(liveTable);
		liveScrollPane.getVerticalScrollBar().setUI(
				new MyScrollBarUI(Color.LIGHT_GRAY, Color.GRAY));
		liveScrollPane.setBounds(X * 865 / 1366, Y * 220 / 768, X * 485 / 1366,
				Y * 500 / 768);
		liveScrollPane.setVisible(true);
		liveScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		liveScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		liveScrollPane.getViewport().setOpaque(false);
		liveScrollPane.setOpaque(false);

		bgLabel.add(liveTextlbl);
		bgLabel.add(liveScrollPane);

		VisionController(currentState);

	}

	public void teamDataLive(TeamPO guestTeamPo, TeamPO hostTeamPo) {

		if (summarylbl != null) {
			summarylbl.setVisible(false);
		}
		
		summarylbl = new MyLabel();
		summarylbl.setHorizontalAlignment(SwingConstants.CENTER);
		summarylbl.setBounds(X * 0 / 1366, Y * 194 / 768, X * 1231 / 1366,
				Y * 560 / 768);

		MyLabel hitRatelbl = new MyLabel("命中率");
		hitRatelbl.setHorizontalAlignment(SwingConstants.CENTER);
		hitRatelbl.setBounds(X * 607 / 1366, Y * 100 / 768, X * 150 / 1366,
				Y * 68 / 768);
		hitRatelbl.setFont(new Font("微软雅黑", 1, 16));
		summarylbl.add(hitRatelbl);

		MyLabel threePointHitRatelbl = new MyLabel("三分命中率");
		threePointHitRatelbl.setHorizontalAlignment(SwingConstants.CENTER);
		threePointHitRatelbl.setBounds(X * 607 / 1366, Y * 168 / 768,
				X * 150 / 1366, Y * 68 / 768);
		threePointHitRatelbl.setFont(new Font("微软雅黑", 1, 16));
		summarylbl.add(threePointHitRatelbl);

		MyLabel freeThrowHitRatelbl = new MyLabel("罚球命中率");
		freeThrowHitRatelbl.setHorizontalAlignment(SwingConstants.CENTER);
		freeThrowHitRatelbl.setBounds(X * 607 / 1366, Y * 236 / 768,
				X * 150 / 1366, Y * 68 / 768);
		freeThrowHitRatelbl.setFont(new Font("微软雅黑", 1, 16));
		summarylbl.add(freeThrowHitRatelbl);

		MyLabel reboundlbl = new MyLabel("篮板");
		reboundlbl.setHorizontalAlignment(SwingConstants.CENTER);
		reboundlbl.setBounds(X * 607 / 1366, Y * 304 / 768, X * 150 / 1366,
				Y * 68 / 768);
		reboundlbl.setFont(new Font("微软雅黑", 1, 16));
		summarylbl.add(reboundlbl);

		MyLabel assistancelbl = new MyLabel("助攻");
		assistancelbl.setHorizontalAlignment(SwingConstants.CENTER);
		assistancelbl.setBounds(X * 607 / 1366, Y * 372 / 768, X * 150 / 1366,
				Y * 68 / 768);
		assistancelbl.setFont(new Font("微软雅黑", 1, 16));
		summarylbl.add(assistancelbl);

		JLabel lblGuestHitRate = new JLabel();
		lblGuestHitRate.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuestHitRate
				.setBounds(
						X
								* (607 - Integer
										.parseInt(new java.text.DecimalFormat(
												"0").format(guestTeamPo)) * 3)
								/ 1366,
						Y * 120 / 768,
						X
								* (Integer
										.parseInt(new java.text.DecimalFormat(
												"0").format(guestTeamPo)) * 3)
								/ 1366, Y * 28 / 768);
		if (>=) {
			lblGuestHitRate.setBackground(Color.BLUE);
		} else {
			lblGuestHitRate.setBackground(Color.LIGHT_GRAY);
		}
		lblGuestHitRate.setOpaque(true);
		summarylbl.add(lblGuestHitRate);

		JLabel lblHostHitRate = new JLabel();
		lblHostHitRate.setHorizontalAlignment(SwingConstants.CENTER);
		lblHostHitRate.setBounds(
				X * 757 / 1366,
				Y * 120 / 768,
				X
						* (Integer.parseInt(new java.text.DecimalFormat("0")
								.format(hostTeamPo)) * 3) / 1366, Y * 28 / 768);
		if (>=) {
			lblHostHitRate.setBackground(Color.LIGHT_GRAY);
		} else {
			lblHostHitRate.setBackground(Color.BLUE);
		}
		lblHostHitRate.setOpaque(true);
		summarylbl.add(lblHostHitRate);

		JLabel lblGuestThreePointHitRate = new JLabel();
		lblGuestThreePointHitRate.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuestThreePointHitRate
				.setBounds(
						X
								* (607 - Integer
										.parseInt(new java.text.DecimalFormat(
												"0").format(guestTeamPo)) * 3)
								/ 1366,
						Y * 188 / 768,
						X
								* (Integer
										.parseInt(new java.text.DecimalFormat(
												"0").format(guestTeamPo)) * 3)
								/ 1366, Y * 28 / 768);
		if (>=) {
			lblGuestThreePointHitRate.setBackground(Color.BLUE);
		} else {
			lblGuestThreePointHitRate.setBackground(Color.LIGHT_GRAY);
		}
		lblGuestThreePointHitRate.setOpaque(true);
		summarylbl.add(lblGuestThreePointHitRate);

		JLabel lblHostThreePointHitRate = new JLabel();
		lblHostThreePointHitRate.setHorizontalAlignment(SwingConstants.CENTER);
		lblHostThreePointHitRate.setBounds(
				X * 757 / 1366,
				Y * 188 / 768,
				X
						* (Integer.parseInt(new java.text.DecimalFormat("0")
								.format(hostTeamPo)) * 3) / 1366, Y * 28 / 768);
		if (>=) {
			lblHostThreePointHitRate.setBackground(Color.LIGHT_GRAY);
		} else {
			lblHostThreePointHitRate.setBackground(Color.BLUE);
		}
		lblHostThreePointHitRate.setOpaque(true);
		summarylbl.add(lblHostThreePointHitRate);

		JLabel lblGuestFreeThrowHitRate = new JLabel();
		lblGuestFreeThrowHitRate.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuestFreeThrowHitRate
				.setBounds(
						X
								* (607 - Integer
										.parseInt(new java.text.DecimalFormat(
												"0").format(guestTeamPo)) * 3)
								/ 1366,
						Y * 256 / 768,
						X
								* (Integer
										.parseInt(new java.text.DecimalFormat(
												"0").format(guestTeamPo)) * 3)
								/ 1366, Y * 28 / 768);
		if (>=) {
			lblGuestFreeThrowHitRate.setBackground(Color.BLUE);
		} else {
			lblGuestFreeThrowHitRate.setBackground(Color.LIGHT_GRAY);
		}
		lblGuestFreeThrowHitRate.setOpaque(true);
		summarylbl.add(lblGuestFreeThrowHitRate);

		JLabel lblHostFreeThrowHitRate = new JLabel();
		lblHostFreeThrowHitRate.setHorizontalAlignment(SwingConstants.CENTER);
		lblHostFreeThrowHitRate.setBounds(
				X * 757 / 1366,
				Y * 256 / 768,
				X
						* (Integer.parseInt(new java.text.DecimalFormat("0")
								.format(hostTeamPo)) * 3) / 1366, Y * 28 / 768);
		if (>=) {
			lblHostFreeThrowHitRate.setBackground(Color.LIGHT_GRAY);
		} else {
			lblHostFreeThrowHitRate.setBackground(Color.BLUE);
		}
		lblHostFreeThrowHitRate.setOpaque(true);
		summarylbl.add(lblHostFreeThrowHitRate);

		JLabel lblGuestRebound = new JLabel();
		lblGuestRebound.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuestRebound
				.setBounds(
						X
								* (607 - Integer
										.parseInt(new java.text.DecimalFormat(
												"0").format(guestTeamPo)) * 3)
								/ 1366,
						Y * 324 / 768,
						X
								* (Integer
										.parseInt(new java.text.DecimalFormat(
												"0").format(guestTeamPo)) * 3)
								/ 1366, Y * 28 / 768);
		if (>=) {
			lblGuestRebound.setBackground(Color.BLUE);
		} else {
			lblGuestRebound.setBackground(Color.LIGHT_GRAY);
		}
		lblGuestRebound.setOpaque(true);
		summarylbl.add(lblGuestRebound);

		JLabel lblHostRebound = new JLabel();
		lblHostRebound.setHorizontalAlignment(SwingConstants.CENTER);
		lblHostRebound.setBounds(
				X * 757 / 1366,
				Y * 324 / 768,
				X
						* (Integer.parseInt(new java.text.DecimalFormat("0")
								.format(hostTeamPo)) * 3) / 1366, Y * 28 / 768);
		if (>=) {
			lblHostRebound.setBackground(Color.LIGHT_GRAY);
		} else {
			lblHostRebound.setBackground(Color.BLUE);
		}
		lblHostRebound.setOpaque(true);
		summarylbl.add(lblHostRebound);

		JLabel lblGuestAssistance = new JLabel();
		lblGuestAssistance.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuestAssistance
				.setBounds(
						X
								* (607 - Integer
										.parseInt(new java.text.DecimalFormat(
												"0").format(guestTeamPo)) * 3)
								/ 1366,
						Y * 324 / 768,
						X
								* (Integer
										.parseInt(new java.text.DecimalFormat(
												"0").format(guestTeamPo)) * 3)
								/ 1366, Y * 28 / 768);
		if (>=) {
			lblGuestAssistance.setBackground(Color.BLUE);
		} else {
			lblGuestAssistance.setBackground(Color.LIGHT_GRAY);
		}
		lblGuestAssistance.setOpaque(true);
		summarylbl.add(lblGuestAssistance);

		JLabel lblHostAssistance = new JLabel();
		lblHostAssistance.setHorizontalAlignment(SwingConstants.CENTER);
		lblHostAssistance.setBounds(
				X * 757 / 1366,
				Y * 324 / 768,
				X
						* (Integer.parseInt(new java.text.DecimalFormat("0")
								.format(hostTeamPo)) * 3) / 1366, Y * 28 / 768);
		if (>=) {
			lblHostAssistance.setBackground(Color.LIGHT_GRAY);
		} else {
			lblHostAssistance.setBackground(Color.BLUE);
		}
		lblHostAssistance.setOpaque(true);
		summarylbl.add(lblHostAssistance);

		bgLabel.add(summarylbl);

		VisionController(currentState);
	}

	public void playerDataLive(ArrayList<PlayerPO> guestPlayerDataList,
			ArrayList<PlayerPO> hostPlayerDataList) {

		if (datalbl != null) {
			datalbl.setVisible(false);
		}
		
		datalbl = new JLabel();
		datalbl.setHorizontalAlignment(SwingConstants.CENTER);
		datalbl.setBounds(X * 0 / 1366, Y * 194 / 768, X * 1331 / 1366,
				Y * 560 / 768);
		datalbl.setBackground(Color.blue);

		compareCriterias = new String[4];
		compareCriterias[0] = "BASIC";
		compareCriterias[1] = "SHOOTING";
		compareCriterias[2] = "REBOUNDS";
		compareCriterias[3] = "X-FACTOR";

		guestTeamDatalbl = new JLabel();
		guestTeamDatalbl.setHorizontalAlignment(SwingConstants.CENTER);
		guestTeamDatalbl.setBounds(131, 0, 550, 100);
		guestTeamDatalbl.setBackground(Color.blue);
		datalbl.add(guestTeamDatalbl);

		hostTeamDatalbl = new JLabel();
		hostTeamDatalbl.setHorizontalAlignment(SwingConstants.CENTER);
		hostTeamDatalbl.setBounds(681, 0, 550, 100);
		hostTeamDatalbl.setBackground(Color.blue);
		datalbl.add(hostTeamDatalbl);

		/*
		 * ArrayList<PlayerPerformanceInSingleGame> guestPlayerVos = new
		 * ArrayList<PlayerPerformanceInSingleGame>();
		 * TeamPerformanceInSingleGame guestTeamPerformanceInSingleGame = gamePO
		 * .getGuestTP(); guestPlayerVos =
		 * guestTeamPerformanceInSingleGame.getPlayerList();
		 */
		if (guestRowData == null) {
			guestRowData = new Vector<Vector<String>>();
		} else {
			guestRowData.clear();
		}
		guestColumn = new Vector<String>();
		guestColumn.add("球员");
		guestColumn.add("姓名");
		guestColumn.add("位置");
		guestColumn.add("分钟");
		guestColumn.add("命中率");
		guestColumn.add("命中");
		guestColumn.add("出手");
		guestColumn.add("三分%");
		guestColumn.add("三分命中");
		guestColumn.add("三分出手");
		guestColumn.add("罚球%");
		guestColumn.add("罚球命中");
		guestColumn.add("罚球出手");
		guestColumn.add("进攻");
		guestColumn.add("防守");
		guestColumn.add("篮板");
		guestColumn.add("助攻");
		guestColumn.add("犯规");
		guestColumn.add("抢断");
		guestColumn.add("失误");
		guestColumn.add("盖帽");
		guestColumn.add("得分");
		if (guestPlayerDataList != null) {
			for (int i = 0; i < guestPlayerDataList.size(); i++) {
				// -------------------steven Jin!!!------------------
				Vector<String> a = new Vector<String>();
				a.add("");
				a.add(guestPlayerDataList.get(i).getName());
				a.add(guestPlayerDataList.get(i).getPosition());
				a.add("");
				a.add("");
				a.add("");
				a.add("");
				a.add("");
				a.add("");
				a.add("");
				a.add("");
				a.add("");
				a.add("");
				a.add("");
				a.add("");
				a.add("");
				a.add("");
				a.add("");
				a.add("");
				a.add("");
				a.add("");
				a.add("");
				// ------------------------------------------------
				/*
				 * a.add(String.valueOf(guestPlayerDataList.get(i).getName()));
				 * a.add("#" +
				 * String.valueOf(guestPlayerDataList.get(i).getNumber()));
				 * a.add(String.valueOf(guestPlayerDataList.get(i).getTime()));
				 * a.add(String.valueOf(guestPlayerDataList.get(i).getScore()));
				 * a
				 * .add(String.valueOf(guestPlayerDataList.get(i).getAssistance(
				 * )));
				 * a.add(String.valueOf(guestPlayerDataList.get(i).getFoul()));
				 */
				guestRowData.add(a);
			}
		} else {
			Vector<String> a = new Vector<String>();
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			guestRowData.add(a);

		}

		if (guestTable != null) {
			guestTable.setVisible(false);
		}

		if (guestScrollPane != null) {
			guestScrollPane.setVisible(false);
		}

		guestTable = new JTable(guestRowData, guestColumn);
		JTableHeader header = guestTable.getTableHeader();
		header.setDefaultRenderer(new DefaultTableCellRenderer() {

			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				Component cell = super.getTableCellRendererComponent(table,
						value, isSelected, hasFocus, row, column);
				cell.setBackground(Color.DARK_GRAY);
				return cell;
			}

		});
		((DefaultTableCellRenderer) header.getDefaultRenderer())
				.setPreferredSize(new Dimension(550, 20));
		((DefaultTableCellRenderer) header.getDefaultRenderer())
				.setHorizontalAlignment(JLabel.CENTER);
		MyTableRenderer r2 = new MyTableRenderer();
		r2.setHorizontalAlignment(JLabel.CENTER);
		guestTable.setDefaultRenderer(Object.class, r2);

		guestTable.setForeground(Color.WHITE);

		guestTable.setIntercellSpacing(new Dimension(0, 0));
		guestTable.getColumnModel().getColumn(0).setPreferredWidth(65);
		guestTable.getColumnModel().getColumn(1).setPreferredWidth(120);
		guestTable.setRowHeight(48);
		guestTable.setVisible(true);
		guestTable.setCellSelectionEnabled(true);
		guestTable.setOpaque(false);
		guestTable.getColumnModel().getColumn(0)
				.setCellRenderer(new PlayerRenderer("guest"));

		guestScrollPane = new JScrollPane(guestTable);
		guestScrollPane.getVerticalScrollBar().setUI(
				new MyScrollBarUI(Color.LIGHT_GRAY, Color.GRAY));
		guestScrollPane.setBounds(81, 20, 1200, 260);
		guestScrollPane.setVisible(true);
		guestScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		guestScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		guestScrollPane.getViewport().setOpaque(false);
		guestScrollPane.setOpaque(false);

		datalbl.add(guestScrollPane);

		/*
		 * ArrayList<PlayerPerformanceInSingleGame> hostPlayerVos = new
		 * ArrayList<PlayerPerformanceInSingleGame>();
		 * TeamPerformanceInSingleGame hostTeamPerformanceInSingleGame = gamePO
		 * .getHomeTP(); hostPlayerVos =
		 * hostTeamPerformanceInSingleGame.getPlayerList();
		 */
		if (hostRowData == null) {
			hostRowData = new Vector<Vector<String>>();
		} else {
			hostRowData.clear();
		}
		hostColumn = new Vector<String>();
		hostColumn.add("球员");
		hostColumn.add("姓名");
		hostColumn.add("位置");
		hostColumn.add("分钟");
		hostColumn.add("命中率");
		hostColumn.add("命中");
		hostColumn.add("出手");
		hostColumn.add("三分%");
		hostColumn.add("三分命中");
		hostColumn.add("三分出手");
		hostColumn.add("罚球%");
		hostColumn.add("罚球命中");
		hostColumn.add("罚球出手");
		hostColumn.add("进攻");
		hostColumn.add("防守");
		hostColumn.add("篮板");
		hostColumn.add("助攻");
		hostColumn.add("犯规");
		hostColumn.add("抢断");
		hostColumn.add("失误");
		hostColumn.add("盖帽");
		hostColumn.add("得分");
		if (hostPlayerDataList != null) {
			for (int i = 0; i < hostPlayerDataList.size(); i++) {
				Vector<String> a = new Vector<String>();
				// -------------------steven Jin!!!------------------
				a.add("");
				a.add(hostPlayerDataList.get(i).getName());
				a.add(hostPlayerDataList.get(i).getPosition());
				a.add(hostPlayerDataList.get(i).get);
				a.add(hostPlayerDataList.get(i).get);
				a.add("");
				a.add("");
				a.add("");
				a.add("");
				a.add("");
				a.add("");
				a.add("");
				a.add("");
				a.add("");
				a.add("");
				a.add("");
				a.add("");
				a.add("");
				a.add("");
				a.add("");
				a.add("");
				a.add("");
				// -----------------------------------------------
				/*
				 * a.add(String.valueOf(hostPlayerVos.get(i).getName()));
				 * a.add("#" +
				 * String.valueOf(hostPlayerVos.get(i).getNumber()));
				 * a.add(String.valueOf(hostPlayerVos.get(i).getTime()));
				 * a.add(String.valueOf(hostPlayerVos.get(i).getScore()));
				 * a.add(String.valueOf(hostPlayerVos.get(i).getAssistance()));
				 * a.add(String.valueOf(hostPlayerVos.get(i).getFoul()));
				 */
				hostRowData.add(a);
			}
		} else {
			Vector<String> a = new Vector<String>();
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			a.add("");
			hostRowData.add(a);
		}

		if (hostTable != null) {
			hostTable.setVisible(false);
		}

		if (hostScrollPane != null) {
			hostScrollPane.setVisible(false);
		}

		hostTable = new JTable(hostRowData, hostColumn);
		JTableHeader header1 = hostTable.getTableHeader();
		header1.setDefaultRenderer(new DefaultTableCellRenderer() {

			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				Component cell = super.getTableCellRendererComponent(table,
						value, isSelected, hasFocus, row, column);
				cell.setBackground(Color.DARK_GRAY);
				return cell;
			}

		});
		((DefaultTableCellRenderer) header1.getDefaultRenderer())
				.setPreferredSize(new Dimension(550, 20));
		((DefaultTableCellRenderer) header1.getDefaultRenderer())
				.setHorizontalAlignment(JLabel.CENTER);
		MyTableRenderer r3 = new MyTableRenderer();
		r3.setHorizontalAlignment(JLabel.CENTER);
		hostTable.setDefaultRenderer(Object.class, r3);

		hostTable.setForeground(Color.WHITE);
		hostTable.setIntercellSpacing(new Dimension(0, 0));
		hostTable.getColumnModel().getColumn(0).setPreferredWidth(65);
		hostTable.getColumnModel().getColumn(1).setPreferredWidth(120);
		hostTable.setRowHeight(48);
		hostTable.setVisible(true);
		hostTable.setCellSelectionEnabled(true);
		hostTable.setOpaque(false);
		hostTable.getColumnModel().getColumn(0)
				.setCellRenderer(new PlayerRenderer("host"));

		hostScrollPane = new JScrollPane(hostTable);
		hostScrollPane.getVerticalScrollBar().setUI(
				new MyScrollBarUI(Color.LIGHT_GRAY, Color.GRAY));
		hostScrollPane.setBounds(81, 300, 1200, 260);
		hostScrollPane.setVisible(true);
		hostScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		hostScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		hostScrollPane.getViewport().setOpaque(false);
		hostScrollPane.setOpaque(false);

		datalbl.add(hostScrollPane);

		bgLabel.add(datalbl);

		VisionController(currentState);

	}

	public void VisionController(String label) {
		if (label.equals("live")) {
			
			if (courtlbl != null) {
				courtlbl.setVisible(true);
			}
			if (liveTextlbl != null) {
				liveTextlbl.setVisible(true);
			}
			if (liveScrollPane != null) {
				liveScrollPane.setVisible(true);
			}
			if (summarylbl != null) {
				summarylbl.setVisible(false);
			}
			if (datalbl != null) {
				datalbl.setVisible(false);
			}
		} else if (label.equals("playerDataLive")) {
			
			if (courtlbl != null) {
				courtlbl.setVisible(false);
			}
			if (liveTextlbl != null) {
				liveTextlbl.setVisible(false);
			}
			if (liveScrollPane != null) {
				liveScrollPane.setVisible(false);
			}
			if (summarylbl != null) {
				summarylbl.setVisible(false);
			}
			if (datalbl != null) {
				datalbl.setVisible(true);
			}

		} else if (label.equals("teamDataLive")) {

			if (courtlbl != null) {
				courtlbl.setVisible(false);
			}
			if (liveTextlbl != null) {
				liveTextlbl.setVisible(false);
			}
			if (liveScrollPane != null) {
				liveScrollPane.setVisible(false);
			}
			if (summarylbl != null) {
				summarylbl.setVisible(true);
			}
			if (datalbl != null) {
				datalbl.setVisible(false);
			} else if (label.equals("playerDataLive")) {
				currentState = "playerDataLive";

				if (courtlbl != null) {
					courtlbl.setVisible(false);
				}
				if (liveTextlbl != null) {
					liveTextlbl.setVisible(false);
				}
				if (liveScrollPane != null) {
					liveScrollPane.setVisible(false);
				}
				if (summarylbl != null) {
					summarylbl.setVisible(false);
				}
				if (datalbl != null) {
					datalbl.setVisible(true);
				}
			}
		}

	}

	class PlayerRenderer extends JButton implements TableCellRenderer {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		String name;
		boolean isGuest;

		public PlayerRenderer(String s) {
			if (s.endsWith("guest")) {
				isGuest = true;
			} else {
				isGuest = false;
			}
		}

		@Override
		public Component getTableCellRendererComponent(JTable arg0,
				Object arg1, boolean arg2, boolean isSelected, int row,
				int column) {
			// TODO Auto-generated method stub
			if (isGuest) {
				this.name = (String) guestTable.getValueAt(row, 1);
			} else {
				this.name = (String) hostTable.getValueAt(row, 1);
			}

			ImageIcon buttonIcon = new ImageIcon(new ImageIcon(
					"CSEdata/players/portrait/" + this.name + ".png")
					.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));

			this.setIcon(buttonIcon);
			this.setOpaque(false);
			this.setContentAreaFilled(false);
			this.setBorderPainted(false);
			if (isSelected) {
				// previousPanel.setVisible(false);
				/*
				 * PlayerInfoPanel playerInfoPanel = new PlayerInfoPanel(
				 * this.name, mainFrame, getSelf(), previouspanel);
				 * MainFrame.currentPanel = "PlayerInfoPanel"; selfClose();
				 */
			}
			return this;
		}

	}

	public class MyTableRenderer extends DefaultTableCellRenderer {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean cellHasFocus,
				int row, int col) {

			if ((row % 2) == 1)
				setOpaque(false);
			else
				setOpaque(false);

			setText((value == null) ? "" : value.toString());

			return this;
		}
	}

	class MyButton extends JButton {

		private static final long serialVersionUID = 1L;

		public MyButton() {
			super();
			this.setHorizontalTextPosition(SwingConstants.CENTER);
			this.setFont(new Font("微软雅黑", 1, 15));
			this.setOpaque(false);
			this.setContentAreaFilled(false);
			this.setBorderPainted(false);
		}

		public MyButton(String s) {
			super();
			this.setText(s);
			this.setHorizontalTextPosition(SwingConstants.CENTER);
			this.setFont(new Font("微软雅黑", 1, 15));
			this.setOpaque(false);
			this.setContentAreaFilled(false);
			this.setBorderPainted(false);
		}

		protected void paintComponent(Graphics g) {
			Image oval = new ImageIcon("Image/Oval.png").getImage();
			oval.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
			g.drawImage(oval, 0, 0, 80, 80, null);
			super.paintComponent(g);
		}
	}

	class ChooseButton extends JButton {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ChooseButton(String s) {
			super();
			this.setText(s);
			this.setHorizontalTextPosition(SwingConstants.CENTER);
			this.setForeground(Color.WHITE);
			this.setFont(new Font("微软雅黑", 1, 15));
			this.setSize(155, 30);
			ImageIcon buttonIcon = new ImageIcon(new ImageIcon(
					"Image/mainButton.png").getImage().getScaledInstance(
					X * 155 / 1366, Y * 30 / 768, Image.SCALE_SMOOTH));

			this.setIcon(buttonIcon);
			this.setOpaque(false);
			this.setContentAreaFilled(false);
			this.setBorderPainted(false);
		}

	}

	class MyTextField extends JTextField {
		/**
			 * 
			 */
		private static final long serialVersionUID = 1L;

		public MyTextField() {
			super();
			this.setOpaque(false);
			this.setForeground(Color.WHITE);
			this.setFont(new Font("黑体", 1, 15));
			this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		}

		public MyTextField(Color textColor) {
			super();
			this.setOpaque(false);
			this.setForeground(textColor);
			this.setFont(new Font("黑体", 1, 15));
			this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		}

	}

	class MyLabel extends JLabel {
		/**
			 * 
			 */
		private static final long serialVersionUID = 1L;

		public MyLabel() {
			super();
			this.setOpaque(false);
			this.setForeground(Color.WHITE);
			this.setFont(new Font("黑体", 1, 13));

		}

		public MyLabel(Color textColor) {
			super();
			this.setOpaque(false);
			this.setForeground(textColor);
			this.setFont(new Font("黑体", 1, 13));

		}

		public MyLabel(String text) {
			super();
			this.setOpaque(false);
			this.setForeground(Color.WHITE);
			this.setText(text);
			this.setFont(new Font("黑体", 1, 13));

		}

		public MyLabel(Color textColor, String text) {
			super();
			this.setOpaque(false);
			this.setForeground(textColor);
			this.setText(text);
			this.setFont(new Font("黑体", 1, 13));

		}
	}

	public class GameDataObserver implements Observer {

		@Override
		public void update(Observable o, Object arg) {
			// TODO Auto-generated method stub
			gamePO = (GamePO) arg;
			gameDataLive(gamePO);
		}

	}

	public class CourtLiveObserver implements Observer{

		@Override
		public void update(Observable o, Object arg) {
			// TODO Auto-generated method stub
			gamePO = (GamePO) arg;
			courtLive(gamePO);
		}
	
    }
      
	
	public class LiveTextObserver implements Observer {

		@Override
		public void update(Observable o, Object arg) {
			// TODO Auto-generated method stub
			liveTextList = (ArrayList<LiveTextPO>) arg;
			live(liveTextList);

		}

	}

	public class TeamDataObserver implements Observer {

		@Override
		public void update(Observable o, Object arg) {
			// TODO Auto-generated method stub
			guestTeamPo = ((ArrayList<TeamPO>) arg).get(0);
			hostTeamPo = ((ArrayList<TeamPO>) arg).get(1);
			teamDataLive(guestTeamPo, hostTeamPo);

		}

	}

	public class PlayerDataObserver implements Observer {

		@Override
		public void update(Observable o, Object arg) {
			// TODO Auto-generated method stub
			guestPlayerDataList = (ArrayList<PlayerPO>) arg;
			hostPlayerDataList = (ArrayList<PlayerPO>) arg;
			playerDataLive(guestPlayerDataList, hostPlayerDataList);
		}

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
