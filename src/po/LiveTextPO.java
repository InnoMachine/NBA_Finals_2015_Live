package po;

public class LiveTextPO {
	
	String text;
	int quarterID;
	String teamAbbr;
	String playerName;
	String remainingTime;
	String score;
	
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getQuarterID() {
		return quarterID;
	}
	public void setQuarterID(int quarterID) {
		this.quarterID = quarterID;
	}
	public String getTeamAbbr() {
		return teamAbbr;
	}
	public void setTeamAbbr(String string) {
		this.teamAbbr = string;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getRemainingTime() {
		return remainingTime;
	}
	public void setRemainingTime(String remainingTimeString) {
		this.remainingTime = remainingTimeString;
	}
	
	
	
}
