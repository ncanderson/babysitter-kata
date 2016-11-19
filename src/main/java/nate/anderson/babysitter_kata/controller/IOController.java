package nate.anderson.babysitter_kata.controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class IOController {
	
	public LocalTime handleStartTime(String userInput) {
		LocalTime formattedTime = twentyFourHourConverter(userInput);
		return formattedTime;
	}
		
	private LocalTime twentyFourHourConverter(String shiftTime) {
		DateTimeFormatter shiftTime12HourFormat = DateTimeFormatter.ofPattern("hh:mm a");
		DateTimeFormatter shiftTime24HourFormat = DateTimeFormatter.ofPattern("HH:mm");
		
		LocalTime twelveHourTime = LocalTime.parse(shiftTime, shiftTime12HourFormat);
		LocalTime formattedTime = LocalTime.parse(shiftTime24HourFormat.format(twelveHourTime));
		return formattedTime;
	}
}
