package nate.anderson.babysitter_kata.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
 
public class SittingShift { 
	
	private LocalDateTime shiftStartTime;
	private LocalDateTime shiftEndTime;
	private LocalDateTime bedtime;
	 
	public List<LocalDateTime> getAllTimes() {
		List<LocalDateTime> allTimes = new ArrayList<LocalDateTime>();
		allTimes.add(shiftStartTime);
		allTimes.add(bedtime);
		allTimes.add(shiftEndTime);
		return allTimes;
	}
	
	public void setAllTimes(LocalDateTime shiftStartTime, LocalDateTime bedtime, LocalDateTime shiftEndTime) {
		this.shiftStartTime = shiftStartTime;
		this.bedtime = bedtime;
		this.shiftEndTime = shiftEndTime;
	}
	
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
