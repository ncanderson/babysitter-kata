package nate.anderson.babysitter_kata.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
 
public class SittingShift { 
	
	private LocalTime shiftStartTime;
	private LocalTime shiftEndTime;
	private LocalTime bedtime;
	
	public List<LocalTime> getAllTimes() {
		List<LocalTime> allTimes = new ArrayList<LocalTime>();
		allTimes.add(shiftStartTime);
		allTimes.add(bedtime);
		allTimes.add(shiftEndTime);
		return allTimes;
	}
	
	public void setAllTimes(LocalTime shiftStartTime, LocalTime bedtime, LocalTime shiftEndTime) {
		this.shiftStartTime = shiftStartTime;
		this.bedtime = bedtime;
		this.shiftEndTime = shiftEndTime;
	}
	
	public LocalTime getShiftStartTime() {
		return shiftStartTime; 
	}
	 
	public void setShiftStartTime(LocalTime shiftStartTime) {
		this.shiftStartTime = shiftStartTime;
	}
	
	public LocalTime getShiftEndTime() {
		return shiftEndTime;
	}
	
	public void setShiftEndTime(LocalTime shiftEndTime) {
		this.shiftEndTime = shiftEndTime;
	}
	
	public LocalTime getBedtime() {
		return bedtime;
	}
	
	public void setBedtime(LocalTime bedtime) {
		this.bedtime = bedtime;
	}
}
