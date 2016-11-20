package nate.anderson.babysitter_kata.controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class IOController {
	
	private final LocalTime EARLIEST_START = LocalTime.of(17, 0);
	private final LocalTime LATEST_FINISH = LocalTime.of(4, 0);
	
	/**
	 * Take String input from the user, format to LocalTime, check that time is valid
	 * A null LocalTime will be handled further up the call stack,
	 * as will an improperly formatted input string
	 * 
	 * @param userInput
	 * @return LocalTime, formatted for the model
	 */
	public LocalTime handleTime(String userInput) throws DateTimeParseException {
		LocalTime formattedTime = twentyFourHourConverter(userInput.toUpperCase());
		if (timeIsValid(formattedTime)) {
			return formattedTime;			
		}
		else {
			return null;
		}
	}
	
	/**
	 * Take a string and return a LocalTime object formatted to 24-hour time
	 * 
	 * @param shiftTime
	 * @return shiftTime string formatted to 24-hour LocalTime object
	 */
	private LocalTime twentyFourHourConverter(String shiftTime) {
		DateTimeFormatter shiftTime12HourFormat = DateTimeFormatter.ofPattern("hh:mm a");
		DateTimeFormatter shiftTime24HourFormat = DateTimeFormatter.ofPattern("HH:mm");
		
		LocalTime twelveHourTime = LocalTime.parse(shiftTime, shiftTime12HourFormat);
		LocalTime formattedTime = LocalTime.parse(shiftTime24HourFormat.format(twelveHourTime));
		return formattedTime;
	}
	
	/**
	 * Check that time falls within allowable range
	 * 
	 * @param shiftTime
	 * @return true if time is within range, false if not
	 */
	private boolean timeIsValid(LocalTime shiftTime) {
		if (shiftTime.equals(EARLIEST_START) || shiftTime.equals(LATEST_FINISH) || shiftTime.equals(LocalTime.MIDNIGHT)) {
			return true;
		}
		else if (shiftTime.isBefore(LocalTime.MAX) && shiftTime.isAfter(EARLIEST_START)) {
			return true;
		}
		else if (shiftTime.isAfter(LocalTime.MIDNIGHT) && shiftTime.isBefore(LATEST_FINISH)) {
			return true;
		}
		else {
			return false;
		}
	}
}