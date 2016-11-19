package nate.anderson.babysitter_kata.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SittingShift {
	
	private final LocalTime EARLIEST_START = LocalTime.of(17, 0);
	private final LocalTime LATEST_FINISH = LocalTime.of(4, 0); 
	
	private LocalTime shiftStartTime;
	private LocalTime shiftEndTime;
	
	public LocalTime getShiftStartTime() {
		return shiftStartTime; 
	}
	
	public void setShiftStartTime(LocalTime shiftStartTime) {
		this.shiftStartTime = shiftStartTime;
//		LocalTime startTime = twentyFourHourConverter(shiftStartTime);
//		if (startTime.equals(EARLIEST_START) || startTime.isAfter(EARLIEST_START)) {
//			this.shiftStartTime = startTime;			
//		}
	}
	
	public LocalTime getShiftEndTime() {
		return shiftEndTime;
	}
	
	public void setShiftEndTime(LocalTime shiftEndTime) {
		this.shiftEndTime = shiftEndTime;
//		LocalTime endTime = twentyFourHourConverter(shiftEndTime);
//		if (endTime.equals(LATEST_FINISH) || endTime.isBefore(LATEST_FINISH)) {
//			this.shiftEndTime = endTime;			
//		}
	}
	


}
