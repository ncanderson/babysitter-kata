package nate.anderson.babysitter_kata.model;

import java.util.ArrayList;
import java.util.List;

public class Rates {

	/**
	 * List of all rates and each individual rate
	 */
	private List<Integer> allRates;
	private int startToBedtimeRate;
	private int bedtimeToMidnightRate;
	private int midnightToFinishRate;
	
	/**
	 * New Rates sets rates to default value and puts them in a list
	 */
	public Rates() { 
		allRates = new ArrayList<Integer>(3);
		startToBedtimeRate = 12;
		bedtimeToMidnightRate = 8;
		midnightToFinishRate = 16;
		allRates.add(0, startToBedtimeRate);
		allRates.add(1, bedtimeToMidnightRate);
		allRates.add(2, midnightToFinishRate);
	}
	
	/** 
	 * Get list of all rates in order
	 * 
	 * @return
	 */
	public List<Integer> getAllRates() {
		return allRates;
	}
	
	/** 
	 * Set all rates at once
	 * 
	 * @param startToBedtimeRate
	 * @param bedtimeToMidnightRate
	 * @param midnightToFinishRate
	 */
	public void setAllRates(int startToBedtimeRate, int bedtimeToMidnightRate, int midnightToFinishRate) {
		allRates.set(0, startToBedtimeRate);
		allRates.set(1, bedtimeToMidnightRate);
		allRates.set(2, midnightToFinishRate);
	}
	
	/** 
	 * Getters and setters for individual rates
	 */
	public int getStartToBedtimeRate() {
		return allRates.get(0);
	}
	
	public void setStartToBedtimeRate(int startToBedtimeRate) {
		allRates.set(0, startToBedtimeRate);
		this.startToBedtimeRate = startToBedtimeRate;
	}
	
	public int getBedtimeToMidnightRate() {
		return allRates.get(1);
	}
	
	public void setBedtimeToMidnightRate(int bedtimeToMidnightRate) {
		allRates.set(1, bedtimeToMidnightRate);
		this.bedtimeToMidnightRate = bedtimeToMidnightRate;
	}
	
	public int getMidnightToFinishRate() {
		return allRates.get(2);
	}
	
	public void setMidnightToFinishRate(int midnightToFinishRate) {
		allRates.set(2, midnightToFinishRate);
		this.midnightToFinishRate = midnightToFinishRate;
	}
}
