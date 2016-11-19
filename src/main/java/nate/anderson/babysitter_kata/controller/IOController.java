package nate.anderson.babysitter_kata.controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class IOController {
	
	private final LocalTime EARLIEST_START = LocalTime.of(17, 0);
	private final LocalTime LATEST_FINISH = LocalTime.of(4, 0);
	
	public LocalTime handleStartTime(String userInput) {
		LocalTime formattedTime = twentyFourHourConverter(userInput);
		if (startTimeIsValid(formattedTime)) {
			return formattedTime;			
		}
		else {
			return null;
		}
	}
		
	private LocalTime twentyFourHourConverter(String shiftTime) {
		DateTimeFormatter shiftTime12HourFormat = DateTimeFormatter.ofPattern("hh:mm a");
		DateTimeFormatter shiftTime24HourFormat = DateTimeFormatter.ofPattern("HH:mm");
		
		LocalTime twelveHourTime = LocalTime.parse(shiftTime, shiftTime12HourFormat);
		LocalTime formattedTime = LocalTime.parse(shiftTime24HourFormat.format(twelveHourTime));
		return formattedTime;
	}
	
	private boolean startTimeIsValid(LocalTime shiftStartTime) {
		if (shiftStartTime.equals(EARLIEST_START) || shiftStartTime.isAfter(EARLIEST_START)) {
			return true;			
		}
		else {
			return false;
		}
	}
}
//&& (shiftTime.isBefore(LATEST_FINISH) || shiftTime.equals(LATEST_FINISH))) {