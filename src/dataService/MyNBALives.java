package dataService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import po.GamePO;
import po.LiveTextPO;
import po.PlayerPO;
import po.TeamPO;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class MyNBALives {
	
	public static void main(String args[]){
		
	}
	
	public static void live() {
		String playbyplayJsonString = getJsonContent("http://china.nba.com/wap/static/data/game/playbyplay_0041400404_4.json");
		String gameJsonString = getJsonContent("http://china.nba.com/wap/static/data/game/snapshotlive_0041400404.json");
		
		JSONObject playbyplayJsonObject =  JSON.parseObject(playbyplayJsonString, JSONObject.class);
		JSONObject gameJsonObject = JSON.parseObject(gameJsonString, JSONObject.class);
		
		JSONArray eventsJsonArray = playbyplayJsonObject.getJSONObject("payload").getJSONArray("playByPlays").getJSONObject(0).getJSONArray("events");
		JSONObject gamePayload = gameJsonObject.getJSONObject("payload");
		JSONObject awayteam = gamePayload.getJSONObject("awayTeam");
		JSONObject hometeam = gamePayload.getJSONObject("homeTeam");
		
		JSONArray awayteamplayersArray = awayteam.getJSONArray("gamePlayers");
		JSONArray hometeamplayersArray = hometeam.getJSONArray("gamePlayers");
		
		getLiveTextsFromJsonArray(eventsJsonArray);
		getGame(gamePayload);
		getAwayPlayers(awayteamplayersArray);
		getHomePlayers(hometeamplayersArray);
		getAwayTeam(awayteam);
		getHomeTeam(hometeam);
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
		System.out.print(jsonObject.getString("gameClock"));
		System.out.print("\t");
		System.out.print(jsonObject.getString("awayScore") + "-" + jsonObject.getString("homeScore"));
		System.out.print("\t");
		System.out.println(jsonObject.getString("description"));
		System.out.print(getPlayerNameEnFromId(jsonObject.getString("playerId")));
		System.out.print("\t");
		System.out.println(getAbbrFromId(jsonObject.getString("teamId")));
		return livetext;
	}
	
	public static PlayerPO getPlayer(JSONObject playerObj) {
		System.out.print(playerObj.getJSONObject("profile").getString("displayNameEn")+"\n");
		System.out.print(playerObj.getJSONObject("boxscore").getString("isStarter")+"\t");//
		System.out.print(playerObj.getJSONObject("boxscore").getString("onCourt")+"\t");//
		System.out.print(playerObj.getJSONObject("boxscore").getString("position")+"\t");
		JSONObject stats = playerObj.getJSONObject("statTotal");
		System.out.println(stats.getString("assists"));
		return null;
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
		awayteam.getJSONObject("profile").getString("abbr");
		JSONObject awayteamScore = awayteam.getJSONObject("score");
		System.out.println("rebs:" + awayteamScore.getString("rebs"));
		return null;
	}
	
	public static TeamPO getHomeTeam(JSONObject hometeam) {
		hometeam.getJSONObject("profile").getString("abbr");
		JSONObject hometeamScore = hometeam.getJSONObject("score");
		System.out.println("rebs:" + hometeamScore.getString("rebs"));
		return null;
	}
	
	public static GamePO getGame(JSONObject gamePayload) {
		
		JSONObject boxscore = gamePayload.getJSONObject("boxscore");
		
		JSONObject hometeam = gamePayload.getJSONObject("homeTeam");
		JSONObject awayteam = gamePayload.getJSONObject("awayTeam");
		System.out.println(boxscore.getString("awayScore") + " - " + boxscore.getString("homeScore"));
		System.out.println("current period:" + boxscore.getString("period"));
		System.out.println("attendance:" + boxscore.getString("attendance"));
		System.out.println("arena:" + gamePayload.getJSONObject("gameProfile").getString("arenaName"));
		System.out.println("status:" + boxscore.getString("statusDesc"));
		
		
		JSONObject awayteamProfileJsonObject = awayteam.getJSONObject("profile");
		JSONObject awayteamScoreJsonObject = awayteam.getJSONObject("score");
		
		
		JSONObject hometeamProfileJsonObject = hometeam.getJSONObject("profile");
		JSONObject hometeamScoreJsonObject = hometeam.getJSONObject("score");
		
		System.out.println(awayteamProfileJsonObject.getString("abbr") + " - " + hometeamProfileJsonObject.getString("abbr"));
		System.out.println(awayteamScoreJsonObject.getString("q1Score") + " - " + hometeamScoreJsonObject.getString("q1Score"));
		System.out.println(awayteamScoreJsonObject.getString("q2Score") + " - " + hometeamScoreJsonObject.getString("q2Score"));
		System.out.println(awayteamScoreJsonObject.getString("q3Score") + " - " + hometeamScoreJsonObject.getString("q3Score"));
		System.out.println(awayteamScoreJsonObject.getString("q4Score") + " - " + hometeamScoreJsonObject.getString("q4Score"));
		
		return null;
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
