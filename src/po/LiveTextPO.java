package po;

public class LiveTextPO {
	String text;
	int quarterID;
	TeamAbbr teamAbbr;
	String playerName;
	String remainingTime;
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
	public TeamAbbr getTeamAbbr() {
		return teamAbbr;
	}
	public void setTeamAbbr(TeamAbbr teamAbbr) {
		this.teamAbbr = teamAbbr;
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
