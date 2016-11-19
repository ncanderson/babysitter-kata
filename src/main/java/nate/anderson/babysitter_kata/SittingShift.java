package nate.anderson.babysitter_kata;

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
	
	public void setShiftStartTime(String shiftStartTime) {
		LocalTime startTime = twentyFourHourConverter(shiftStartTime);
		if (startTime.equals(EARLIEST_START) || startTime.isAfter(EARLIEST_START)) {
			this.shiftStartTime = startTime;			
		}
	}
	
	public LocalTime getShiftEndTime() {
		return shiftEndTime;
	}
	
	public void setShiftEndTime(String shiftEndTime) {
		LocalTime endTime = twentyFourHourConverter(shiftEndTime);
		if (endTime.equals(LATEST_FINISH) || endTime.isBefore(LATEST_FINISH)) {
			this.shiftEndTime = endTime;			
		}
	}
	
	private LocalTime twentyFourHourConverter(String shiftTime) {
		DateTimeFormatter shiftTime12HourFormat = DateTimeFormatter.ofPattern("hh:mm a");
		DateTimeFormatter shiftTime24HourFormat = DateTimeFormatter.ofPattern("HH:mm");
		
		LocalTime twelveHourTime = LocalTime.parse(shiftTime, shiftTime12HourFormat);
		LocalTime formattedTime = LocalTime.parse(shiftTime24HourFormat.format(twelveHourTime));
		return formattedTime;
	}

}
