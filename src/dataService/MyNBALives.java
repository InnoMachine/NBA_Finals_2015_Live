package dataService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import po.GameDate;
import po.GamePO;
import po.LiveTextPO;
import po.LiveTexts;
import po.PlayerPO;
import po.Players;
import po.Scoreboard;
import po.TeamPO;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class MyNBALives {
	
	public static void main(String args[]){
		init();
		while(true) {
			live();
		}
	}
	
	public static void init() {
		Singleton singleton = Singleton.getInstance();
		GamePO game = new GamePO();
		game.setBegin(true);
		game.setCurrentPeriod("1");
		game.setEnd(false);
		game.setExtratime(null);
		game.setGameDate(new GameDate("2015-6-15"));
		game.setGameLabel("14-15_2015-6-15_CLE-GSW");
		//game.setGuestOnCourtPlayerLsit(guestOnCourtPlayerLsit);
		game.setGuestTeam("CLE");
		//game.setHomeOnCourtPlayerLsit(homeOnCourtPlayerLsit);
		game.setHomeTeam("GSW");
		game.setRemainingTime("12:00");
		game.setScore1st(Scoreboard.makeSB("0-0"));
		game.setScore2nd(Scoreboard.makeSB("0-0"));
		game.setScore3rd(Scoreboard.makeSB("0-0"));
		game.setScore4th(Scoreboard.makeSB("0-0"));
		game.setScoreOverall(Scoreboard.makeSB("0-0"));
		game.setSeasonId("14-15");
		game.setVersus("CLE-GSW");
		
		TeamPO hostTeam = new TeamPO();
		hostTeam.setAbbreviation("GSW");
		//hostTeam.setAllPlayersNameList(allPlayersNameList);
		hostTeam.setAsist("0");
		hostTeam.setFreeThrowRatio("0");
		//hostTeam.setOnCourtPlayerList(onCourtPlayerList);
		hostTeam.setRebound("0");
		hostTeam.setShootRatio("0");
		hostTeam.setTeamName("Golden State Worriors");
		hostTeam.setThreePointShootRatio("0");
		
		TeamPO guestTeam = new TeamPO();
		guestTeam.setAbbreviation("GSW");
		//guestTeam.setAllPlayersNameList(allPlayersNameList);
		guestTeam.setAsist("0");
		guestTeam.setFreeThrowRatio("0");
		//guestTeam.setOnCourtPlayerList(onCourtPlayerList);
		guestTeam.setRebound("0");
		guestTeam.setShootRatio("0");
		guestTeam.setTeamName("Golden State Worriors");
		guestTeam.setThreePointShootRatio("0");
		
		singleton.setGame(game);
		singleton.setHostTeam(hostTeam);
		singleton.setGuestTeam(guestTeam);
//		singleton.setGuestPlayers(guestPlayers);
//		singleton.setHostPlayers(hostPlayers);
//		singleton.setLiveText(liveText);
	}
	
	public static void live() {
		
		Singleton.getInstance().setLiveText1(new LiveTexts(getLiveTextsFromJsonArray(getPeriodLiveTextJsonArray(1))));
		Singleton.getInstance().setLiveText1(new LiveTexts(getLiveTextsFromJsonArray(getPeriodLiveTextJsonArray(2))));
		Singleton.getInstance().setLiveText1(new LiveTexts(getLiveTextsFromJsonArray(getPeriodLiveTextJsonArray(3))));
		Singleton.getInstance().setLiveText1(new LiveTexts(getLiveTextsFromJsonArray(getPeriodLiveTextJsonArray(4))));
		
		String gameJsonString = getJsonContent("http://china.nba.com/wap/static/data/game/snapshotlive_0041400404.json");
		JSONObject gameJsonObject = JSON.parseObject(gameJsonString, JSONObject.class);
		JSONObject gamePayload = gameJsonObject.getJSONObject("payload");
		JSONObject awayteam = gamePayload.getJSONObject("awayTeam");
		JSONObject hometeam = gamePayload.getJSONObject("homeTeam");
		JSONArray awayteamplayersArray = awayteam.getJSONArray("gamePlayers");
		JSONArray hometeamplayersArray = hometeam.getJSONArray("gamePlayers");
		
		Singleton.getInstance().setGuestPlayers(new Players(getAwayPlayers(awayteamplayersArray)));
		Singleton.getInstance().setHostPlayers(new Players(getHomePlayers(hometeamplayersArray)));
		Singleton.getInstance().setGame(getGame(gamePayload));
		Singleton.getInstance().setGuestTeam(getAwayTeam(awayteam));
		Singleton.getInstance().setHostTeam(getHomeTeam(hometeam));
	}
	
	public static JSONArray getPeriodLiveTextJsonArray(int period) {
		String url = "http://china.nba.com/wap/static/data/game/playbyplay_0041400404_" + period + ".json";
		JSONArray eventsJsonArray = JSON.parseObject(getJsonContent(url), JSONObject.class).getJSONObject("payload").getJSONArray("playByPlays").getJSONObject(0).getJSONArray("events");;
		return eventsJsonArray;
	}
	
	public static ArrayList<LiveTextPO> getLiveTextsFromJsonArray(JSONArray jsonArray) {
		
		ArrayList<LiveTextPO> result = new ArrayList<LiveTextPO>();
		
		for(int i = 0; i < jsonArray.size(); i ++) {
			result.add(getLiveTextFromJsonObj(jsonArray.getJSONObject(i)));
		}
		
		return result;
	}
	
	public static LiveTextPO getLiveTextFromJsonObj(JSONObject jsonObject) {
		
		LiveTextPO livetext = new LiveTextPO();
		livetext.setPlayerName(getPlayerNameEnFromId(jsonObject.getString("playerId")));
		livetext.setRemainingTime(jsonObject.getString("gameClock"));
		livetext.setTeamAbbr(getAbbrFromId(jsonObject.getString("teamId")));
		livetext.setText(jsonObject.getString("description"));
		livetext.setScore(jsonObject.getString("awayScore") + "-" + jsonObject.getString("homeScore"));
		
		System.out.print(jsonObject.getString("gameClock"));
		System.out.print("\t");
		System.out.print(jsonObject.getString("awayScore") + "-" + jsonObject.getString("homeScore"));
		System.out.print("\t");
		System.out.println(jsonObject.getString("description"));
		System.out.print(getPlayerNameEnFromId(jsonObject.getString("playerId")));
		System.out.print("\t");
		System.out.println(getAbbrFromId(jsonObject.getString("teamId")));
		System.out.println("---------------------------------------------------------------");
		return livetext;
	}
	
	public static PlayerPO getPlayer(JSONObject playerObj) {
		PlayerPO player = new PlayerPO();
		JSONObject stats = playerObj.getJSONObject("statTotal");
		player.setCnName(playerObj.getJSONObject("profile").getString("displayName"));
		player.setEnName(playerObj.getJSONObject("profile").getString("displayNameEn"));
		player.setAsist(stats.getString("assists"));
		player.setBackRebound(stats.getString("defRebs"));
		player.setBlock(stats.getString("blocks"));
		player.setForwardRebound(stats.getString("offRebs"));
		player.setFoul(stats.getString("fouls"));
		player.setFreeThrowInNum(stats.getString("ftm"));
		player.setFreethrowInRatio(stats.getString("ftpct"));
		player.setFreeThrowTotalNum(stats.getString("fta"));
		player.setPosition(playerObj.getJSONObject("boxscore").getString("position"));
		player.setScore(stats.getString("points"));
		player.setShootInNum(stats.getString("fgm"));
		player.setShootInRatio(stats.getString("fgpct"));
		player.setShootThreeInNum(stats.getString("tpm"));
		player.setShootThreeInRatio(stats.getString("tppct"));
		player.setShootThreeTotalNum(stats.getString("tpa"));
		player.setShootTotalNum(stats.getString("fga"));
		player.setSteal(stats.getString("steals"));
		player.setTimeOnCourt(stats.getString("mins")+":"+stats.getString("secs"));
		player.setTotalRebound(stats.getString("rebs"));
		player.setTurnover(stats.getString("turnovers"));
		player.setOnCourt(playerObj.getJSONObject("boxscore").getString("onCourt"));
		return player;
	}
	
	public static ArrayList<PlayerPO> getAwayPlayers(JSONArray awayPlayerArray) {
		
		ArrayList<PlayerPO> result = new ArrayList<PlayerPO>();
		
		for(int i = 0; i < awayPlayerArray.size(); i ++) {
			result.add(getPlayer(awayPlayerArray.getJSONObject(i)));
		}
		
		return result;
	}
	
	public static ArrayList<PlayerPO> getHomePlayers(JSONArray homePlayerArray) {
		ArrayList<PlayerPO> result = new ArrayList<PlayerPO>();
		
		for(int i = 0; i < homePlayerArray.size(); i ++) {
			result.add(getPlayer(homePlayerArray.getJSONObject(i)));
		}
		
		return result;
	}
	
	public static TeamPO getAwayTeam(JSONObject awayteam) {
		
		TeamPO team = new TeamPO();
		JSONObject awayteamScore = awayteam.getJSONObject("score");
		team.setAbbreviation(awayteam.getJSONObject("profile").getString("abbr"));
		ArrayList<String> allPlayersNameList = new ArrayList<String>();
		for(PlayerPO player: Singleton.getInstance().getGuestPlayers().getPlayers()) {
				allPlayersNameList.add(player.getEnName());
		}
		team.setAllPlayersNameList(allPlayersNameList);
		team.setAsist(awayteamScore.getString("assists"));
		team.setFreeThrowRatio(awayteamScore.getString("ftpct"));
		team.setRebound(awayteamScore.getString("rebs"));
		team.setShootRatio(awayteamScore.getString("fgpct"));
		team.setTeamName(awayteam.getJSONObject("profile").getString("nameEn"));
		team.setThreePointShootRatio(awayteamScore.getString("tpppct"));
		return team;
	}
	
	public static TeamPO getHomeTeam(JSONObject hometeam) {
		
		TeamPO team = new TeamPO();
		JSONObject hometeamScore = hometeam.getJSONObject("score");
		team.setAbbreviation(hometeam.getJSONObject("profile").getString("abbr"));
		ArrayList<String> allPlayersNameList = new ArrayList<String>();
		for(PlayerPO player: Singleton.getInstance().getHostPlayers().getPlayers()) {
				allPlayersNameList.add(player.getEnName());
		}
		team.setAllPlayersNameList(allPlayersNameList);
		team.setAsist(hometeamScore.getString("assists"));
		team.setFreeThrowRatio(hometeamScore.getString("ftpct"));
		team.setRebound(hometeamScore.getString("rebs"));
		team.setShootRatio(hometeamScore.getString("fgpct"));
		team.setTeamName(hometeam.getJSONObject("profile").getString("nameEn"));
		team.setThreePointShootRatio(hometeamScore.getString("tpppct"));
		return team;
	}
	
	public static GamePO getGame(JSONObject gamePayload) {
		
		JSONObject boxscore = gamePayload.getJSONObject("boxscore");
		JSONObject hometeam = gamePayload.getJSONObject("homeTeam");
		JSONObject awayteam = gamePayload.getJSONObject("awayTeam");
		JSONObject awayteamProfileJsonObject = awayteam.getJSONObject("profile");
		JSONObject awayteamScoreJsonObject = awayteam.getJSONObject("score");
		JSONObject hometeamProfileJsonObject = hometeam.getJSONObject("profile");
		JSONObject hometeamScoreJsonObject = hometeam.getJSONObject("score");
		GamePO game = new GamePO();
		game.setBegin(true);
		game.setCurrentPeriod(boxscore.getString("period"));
		game.setEnd(false);
		game.setExtratime(null);
		game.setGameDate(new GameDate("2015-6-15"));
		game.setGameLabel("14-15_2015-6-15_CLE-GSW");
		ArrayList<String> guestOnCourtPlayerLsit = new ArrayList<String>();
		for(PlayerPO player: Singleton.getInstance().getGuestPlayers().getPlayers()) {
			if(player.getOnCourt().equals("true")) {
				guestOnCourtPlayerLsit.add(player.getEnName());
			}
		}
		game.setGuestOnCourtPlayerLsit(guestOnCourtPlayerLsit);
		game.setGuestTeam("CLE");
		ArrayList<String> homeOnCourtPlayerLsit = new ArrayList<String>();
		for(PlayerPO player: Singleton.getInstance().getHostPlayers().getPlayers()) {
			if(player.getOnCourt().equals("true")) {
				homeOnCourtPlayerLsit.add(player.getEnName());
			}
		}
		game.setHomeOnCourtPlayerLsit(homeOnCourtPlayerLsit);
		game.setHomeTeam("GSW");
		game.setRemainingTime("12:00");
		game.setScore1st(Scoreboard.makeSB(awayteamScoreJsonObject.getString("q1Score") + "-" + hometeamScoreJsonObject.getString("q1Score")));
		game.setScore2nd(Scoreboard.makeSB(awayteamScoreJsonObject.getString("q2Score") + "-" + hometeamScoreJsonObject.getString("q2Score")));
		game.setScore3rd(Scoreboard.makeSB(awayteamScoreJsonObject.getString("q3Score") + "-" + hometeamScoreJsonObject.getString("q3Score")));
		game.setScore4th(Scoreboard.makeSB(awayteamScoreJsonObject.getString("q4Score") + "-" + hometeamScoreJsonObject.getString("q4Score")));
		game.setScoreOverall(Scoreboard.makeSB(boxscore.getString("awayScore") + "-" + boxscore.getString("homeScore")));
		game.setSeasonId("14-15");
		game.setVersus(awayteamProfileJsonObject.getString("abbr") + "-" + hometeamProfileJsonObject.getString("abbr"));
		return game;
		
	}
	
	public static String getJsonContent(String urlStr) {
		
		try {// 获取HttpURLConnection连接对象
            URL url = new URL(urlStr);
            HttpURLConnection httpConn = (HttpURLConnection) url
                    .openConnection();
            // 设置连接属性
            httpConn.setConnectTimeout(3000);
            httpConn.setDoInput(true);
            httpConn.setRequestMethod("GET");
            // 获取相应码
            int respCode = httpConn.getResponseCode();
            if (respCode == 200) {
                return ConvertStream2Json(httpConn.getInputStream());
            }
        }
        catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
		return "";
	}
	
	public static String ConvertStream2Json(InputStream inputStream) {
		
        String jsonStr = "";
        // ByteArrayOutputStream相当于内存输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        
        // 将输入流转移到内存输出流中
        try {
            while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
                out.write(buffer, 0, len);
            }
            // 将内存流转换为字符串
            jsonStr = new String(out.toByteArray());
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return jsonStr;
    }

	public static String getPlayerNameEnFromId(String id) {
		switch(id) {
		case "203084":
			return "Harrison Barnes";
		case "2738":
			return "Andre Iguodala";
		case "203110":
			return "Draymond Green";
		case "202691":
			return "Klay Thompson";
		case "201939":
			return "Stephen Curry";
		case "101135":
			return "David Lee";
		case "2733":
			return "Shaun Livingston";
		case "2571":
			return "Andrew Bogut";
		case "101106":
			return "Andrew Bogut";
		case "201578":
			return "Marreese Speights";
		case "203200":
			return "Justin Holiday";
		case "203949":
			return "James Michael McAdoo";
		case "203105":
			return "Festus Ezeli";
		case "2544":
			return "LeBron James";
		case "202684":
			return "Tristan Thompson";
		case "202389":
			return "Timofey Mozgov";
		case "202697":
			return "Iman Shumpert";
		case "203521":
			return "Matthew Dellavedova";
		case "2747":
			return "J.R. Smith";
		case "2592":
			return "James Jones";
		case "2570":
			return "Kendrick Perkins";
		case "203925":
			return "Joe Harris";
		case "2034":
			return "Mike Miller";
		case "2217":
			return "Brendan Haywood";
		case "202681":
			return "Kyrie Irving";
		case "1890":
			return "Shawn Marion";
			
		
			
		}
		return null;
	}
	
	public static String getAbbrFromId(String id) {
		if(id.equals("1610612744")) {
			return "GSW";
		}else if(id.equals("1610612739")) {
			return "CLE";
		}else {
			return null;
		}
	}
}
