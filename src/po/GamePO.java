/**
 * puppy
 * 2015年3月8日 下午3:06:02
 * TODO
 */
package po;

import java.util.ArrayList;
import java.util.Observable;

public class GamePO extends Observable{
	
	private String gameLabel;
	private String seasonId;//season
	private GameDate gameDate;//comparable
	private String versus;
	private String guestTeam;
	private String homeTeam;
	private Scoreboard scoreOverall;
	private Scoreboard score1st;
	private Scoreboard score2nd;
	private Scoreboard score3rd;
	private Scoreboard score4th;
	private ArrayList<Scoreboard> extratime;//加时赛
	
	private String currentPeriod;
	private String remainingTime;
	private ArrayList<String> guestOnCourtPlayerLsit;
	private ArrayList<String> homeOnCourtPlayerLsit;
	private boolean isBegin;
	private boolean isEnd;
	public String getGameLabel() {
		return gameLabel;
	}
	public void setGameLabel(String gameLabel) {
		this.gameLabel = gameLabel;
	}
	public String getSeasonId() {
		return seasonId;
	}
	public void setSeasonId(String seasonId) {
		this.seasonId = seasonId;
	}
	public GameDate getGameDate() {
		return gameDate;
	}
	public void setGameDate(GameDate gameDate) {
		this.gameDate = gameDate;
	}
	public String getVersus() {
		return versus;
	}
	public void setVersus(String versus) {
		this.versus = versus;
	}
	public String getGuestTeam() {
		return guestTeam;
	}
	public void setGuestTeam(String guestTeam) {
		this.guestTeam = guestTeam;
	}
	public String getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}
	public Scoreboard getScoreOverall() {
		return scoreOverall;
	}
	public void setScoreOverall(Scoreboard scoreOverall) {
		this.scoreOverall = scoreOverall;
	}
	public Scoreboard getScore1st() {
		return score1st;
	}
	public void setScore1st(Scoreboard score1st) {
		this.score1st = score1st;
	}
	public Scoreboard getScore2nd() {
		return score2nd;
	}
	public void setScore2nd(Scoreboard score2nd) {
		this.score2nd = score2nd;
	}
	public Scoreboard getScore3rd() {
		return score3rd;
	}
	public void setScore3rd(Scoreboard score3rd) {
		this.score3rd = score3rd;
	}
	public Scoreboard getScore4th() {
		return score4th;
	}
	public void setScore4th(Scoreboard score4th) {
		this.score4th = score4th;
	}
	public ArrayList<Scoreboard> getExtratime() {
		return extratime;
	}
	public void setExtratime(ArrayList<Scoreboard> extratime) {
		this.extratime = extratime;
	}
	public String getCurrentPeriod() {
		return currentPeriod;
	}
	public void setCurrentPeriod(String currentPeriod) {
		this.currentPeriod = currentPeriod;
	}
	public String getRemainingTime() {
		return remainingTime;
	}
	public void setRemainingTime(String remainingTime) {
		this.remainingTime = remainingTime;
	}
	public ArrayList<String> getGuestOnCourtPlayerLsit() {
		return guestOnCourtPlayerLsit;
	}
	public void setGuestOnCourtPlayerLsit(ArrayList<String> guestOnCourtPlayerLsit) {
		this.guestOnCourtPlayerLsit = guestOnCourtPlayerLsit;
	}
	public ArrayList<String> getHomeOnCourtPlayerLsit() {
		return homeOnCourtPlayerLsit;
	}
	public void setHomeOnCourtPlayerLsit(ArrayList<String> homeOnCourtPlayerLsit) {
		this.homeOnCourtPlayerLsit = homeOnCourtPlayerLsit;
	}
	public boolean isBegin() {
		return isBegin;
	}
	public void setBegin(boolean isBegin) {
		this.isBegin = isBegin;
	}
	public boolean isEnd() {
		return isEnd;
	}
	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}
	
}
