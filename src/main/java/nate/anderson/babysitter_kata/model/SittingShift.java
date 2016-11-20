package nate.anderson.babysitter_kata.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
 
public class SittingShift { 
	
	private LocalTime shiftStartTime;
	private LocalTime shiftEndTime;
	private LocalTime bedtime;
	
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
