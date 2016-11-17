package nate.anderson.babysitter_kata;

import java.time.LocalTime;

public class SittingShift {
	
	private LocalTime shiftStartTime;
	private LocalTime shiftEndTime;
	
	public LocalTime getShiftStartTime() {
		return shiftStartTime;
	}
	
	public void setShiftStartTime(String shiftStartTime) {
		String[] startTimeConverter = shiftStartTime.split(":");
		this.shiftStartTime = LocalTime.of(Integer.parseInt(startTimeConverter[0]), Integer.parseInt(startTimeConverter[1]));
	}
	
	public LocalTime getShiftEndTime() {
		return shiftEndTime;
	}
	
	public void setShiftEndTime(String shiftEndTime) {
		String[] endTimeConverter = shiftEndTime.split(":");
		this.shiftEndTime = LocalTime.of(Integer.parseInt(endTimeConverter[0]), Integer.parseInt(endTimeConverter[1]));
	}
	
}
