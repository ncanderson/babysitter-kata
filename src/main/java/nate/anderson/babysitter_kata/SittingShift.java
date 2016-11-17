package nate.anderson.babysitter_kata;

import java.time.LocalTime;

public class SittingShift {
	
	private LocalTime shiftStartTime;
	
	public LocalTime getShiftStartTime() {
		return shiftStartTime;
	}
	
	public void setShiftStartTime(String shiftStartTime) {
		this.shiftStartTime = LocalTime.of(5, 0);
	}
	
}
