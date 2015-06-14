/**
 * puppy
 * 2015年3月8日 下午2:00:04
 * TODO
 */
package po;

import java.util.ArrayList;
import java.util.Observable;

public class TeamPO extends Observable{
	
	private ArrayList<String> allPlayersNameList;
	private String teamName;
	private String abbreviation;
	private String shootRatio;
	private String threePointShootRatio;
	private String freeThrowRatio;
	private String rebound;
	private String asist;
	public ArrayList<String> getAllPlayersNameList() {
		return allPlayersNameList;
	}
	public void setAllPlayersNameList(ArrayList<String> allPlayersNameList) {
		this.allPlayersNameList = allPlayersNameList;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getShootRatio() {
		return shootRatio;
	}
	public void setShootRatio(String shootRatio) {
		this.shootRatio = shootRatio;
	}
	public String getThreePointShootRatio() {
		return threePointShootRatio;
	}
	public void setThreePointShootRatio(String threePointShootRatio) {
		this.threePointShootRatio = threePointShootRatio;
	}
	public String getFreeThrowRatio() {
		return freeThrowRatio;
	}
	public void setFreeThrowRatio(String freeThrowRatio) {
		this.freeThrowRatio = freeThrowRatio;
	}
	public String getRebound() {
		return rebound;
	}
	public void setRebound(String rebound) {
		this.rebound = rebound;
	}
	public String getAsist() {
		return asist;
	}
	public void setAsist(String asist) {
		this.asist = asist;
	}

	
}
