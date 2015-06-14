package po;

import java.util.ArrayList;
import java.util.Observable;

public class LiveTexts extends Observable{

	private ArrayList<LiveTextPO> liveTexts;
	
	public LiveTexts() {
		this.setLiveTexts(null);
	}
	
	public LiveTexts(ArrayList<LiveTextPO> liveTexts) {
		this.setLiveTexts(liveTexts);
	}

	public ArrayList<LiveTextPO> getLiveTexts() {
		return liveTexts;
	}

	public void setLiveTexts(ArrayList<LiveTextPO> liveTexts) {
		this.liveTexts = liveTexts;
	}

}
