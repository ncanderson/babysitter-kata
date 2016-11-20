package nate.anderson.babysitter_kata.model;

import java.util.ArrayList;
import java.util.List;

public class Rates {

	private List<Integer> allRates;
	private int startToBedtime;
	private int bedtimeToMidnight;
	private int midnightToFinish;
	
	public Rates() { 
		allRates = new ArrayList<Integer>(3);
		startToBedtime = 12;
		bedtimeToMidnight = 8;
		midnightToFinish = 16;
		allRates.add(0, startToBedtime);
		allRates.add(1, bedtimeToMidnight);
		allRates.add(2, midnightToFinish);
	}
	
	public List<Integer> getAllRates() {
		return allRates;
	}
	
	public void setAllRates(int startToBedTime, int bedtimeToMidnight, int midnightToFinish) {
		allRates.set(0, startToBedTime);
		allRates.set(1, bedtimeToMidnight);
		allRates.set(2, midnightToFinish);
	}
	
	public int getStartToBedtime() {
		return allRates.get(0);
	}
	
	public void setStartToBedtime(int startToBedtime) {
		allRates.set(0, startToBedtime);
	}
	
	public int getBedtimeToMidnight() {
		return allRates.get(1);
	}
	
	public void setBedtimeToMidnight(int bedtimeToMidnight) {
		allRates.set(1, bedtimeToMidnight);
	}
	
	public int getMidnightToFinish() {
		return allRates.get(2);
	}
	
	public void setMidnightToFinish(int midnightToFinish) {
		allRates.set(2, midnightToFinish);
	}
}
