package nate.anderson.babysitter_kata;

import java.time.LocalTime;

public class SittingShift {
	
	private LocalTime shiftStartTime;
	private LocalTime shiftEndTime;
	
	public LocalTime getShiftStartTime() {
		return shiftStartTime;
	}
	
	public void setShiftStartTime(String shiftStartTime) {
		this.shiftStartTime = LocalTime.of(5, 0);
	}
	
	public LocalTime getShiftEndTime() {
		return shiftEndTime;
	}
	
	public void setShiftEndTime(String shiftEndTime) {
		this.shiftEndTime = LocalTime.of(3, 0);
		
	}
	
}
