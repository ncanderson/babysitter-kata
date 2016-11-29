package nate.anderson.babysitter_kata.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
 
public class SittingShift { 
	
	/**
	 * Three variables for the times in the shift. The other, midnight, is implicit and 
	 * its presence handled by the iocontroller
	 */
	private LocalDateTime shiftStartTime;
	private LocalDateTime shiftEndTime;
	private LocalDateTime bedtime;
	 
	/**
	 * Get all instance variables in list format
	 * 
	 * @return all variables added in the appropriate order for the iocontroller
	 */
	public List<LocalDateTime> getAllTimes() {
		List<LocalDateTime> allTimes = new ArrayList<LocalDateTime>();
		allTimes.add(shiftStartTime);
		allTimes.add(bedtime);
		allTimes.add(shiftEndTime);
		return allTimes;
	}
	
	/** 
	 * Set all shift times at once
	 * 
	 * @param shiftStartTime
	 * @param bedtime
	 * @param shiftEndTime
	 */
	public void setAllTimes(LocalDateTime shiftStartTime, LocalDateTime bedtime, LocalDateTime shiftEndTime) {
		this.shiftStartTime = shiftStartTime;
		this.bedtime = bedtime;
		this.shiftEndTime = shiftEndTime;
	}
	
	/**
	 * Getters and setters for invididual times
	 */
	public LocalDateTime getShiftStartTime() {
		return shiftStartTime; 
	}
	 
	public void setShiftStartTime(LocalDateTime shiftStartTime) {
		this.shiftStartTime = shiftStartTime;
	}
	
	public LocalDateTime getShiftEndTime() {
		return shiftEndTime;
	}
	
	public void setShiftEndTime(LocalDateTime shiftEndTime) {
		this.shiftEndTime = shiftEndTime;
	}
	
	public LocalDateTime getBedtime() {
		return bedtime;
	}
	
	public void setBedtime(LocalDateTime bedtime) {
		this.bedtime = bedtime;
	}
}
