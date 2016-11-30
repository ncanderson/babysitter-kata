package nate.anderson.babysitter_kata.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.directory.InvalidAttributesException;

import nate.anderson.babysitter_kata.model.SittingShift;


public class IOController {
	
	/**
	 * Final variables to check user inputs against
	 */
	private final LocalDate TODAY = LocalDate.now();
	private final LocalDate TOMORROW = TODAY.plusDays(1);
	
	private final LocalTime START_TIME = LocalTime.of(17, 0);
	private final LocalTime NOON = LocalTime.NOON;
	private final LocalTime END_TIME = LocalTime.of(4, 0);
	
	private final LocalDateTime EARLIEST_START = LocalDateTime.of(TODAY, START_TIME);
	private final LocalDateTime LATEST_FINISH = LocalDateTime.of(TOMORROW, END_TIME);
	
	private List<LocalTime> listOfTimes;
	 
	/**
	 * IOController holds all times, checking that the selected times make logical sense before 
	 * handing off to the sitting shift
	 */
	public IOController() {
		listOfTimes = new ArrayList<LocalTime>();
	}
	
	/** 
	 * @return the instance variable that holds all the times
	 */
	public List<LocalTime> getListOfTimes() {
		return listOfTimes;
	}
	
	/**
	 * Clears from temporary list in case of bad input
	 */
	public void clearAtIndex(int index) {
		listOfTimes.remove(index);
	}
	
	/**
	 * This method will take times from userinput and hold them before analyzing for validity
	 * 
	 * @param shiftTime
	 */
	public void addTimeToList(LocalTime shiftTime) {
		listOfTimes.add(shiftTime);
	}
	
	/**
	 * Handles the setting of shift times, analyzing to make sure they are valid and end is after start 
	 * 
	 * @param sittingShift
	 * @param shiftTimes
	 */
	public void assignTimes(SittingShift sittingShift, List<LocalTime> shiftTimes) throws InvalidAttributesException {

		sittingShift.setShiftStartTime(turnLocalTimeIntoLocalDateTime(shiftTimes.get(0)));
		sittingShift.setBedtime(turnLocalTimeIntoLocalDateTime(shiftTimes.get(1)));
		sittingShift.setShiftEndTime(turnLocalTimeIntoLocalDateTime(shiftTimes.get(2)));

		checkEndTimeIsAfterStart(sittingShift);
	}
	
	/**
	 * Check LocalTime in relation to NOON, which determines which day the time in question will fall ong
	 * 
	 * @param shiftTime to add Date to 
	 * @return the appropriate LocalDateTime
	 */
	private LocalDateTime turnLocalTimeIntoLocalDateTime(LocalTime shiftTime) {
		
		if (shiftTime.isAfter(NOON)) {
			return (LocalDateTime.of(TODAY, shiftTime));			
		}
		else {
			return(LocalDateTime.of(TOMORROW, shiftTime));
		}
	}
	
	/**
	 * Check times assigned to the Sitting Shift to ensure end is after start
	 * 
	 * @param sittingShift to check
	 * @throws InvalidAttributesException if end is before or equal to start
	 */
	private void checkEndTimeIsAfterStart(SittingShift sittingShift) throws InvalidAttributesException {
		
		LocalDateTime start = sittingShift.getShiftStartTime();
		LocalDateTime end = sittingShift.getShiftEndTime();
		
		if (start.isAfter(end) || start.equals(end)) {
			throw new InvalidAttributesException();
		}
	}
	
	/**
	 * Take String input from the user, format to LocalTime, check that time is valid
	 * A null LocalTime will be handled further up the call stack,
	 * as will an improperly formatted input string
	 * 
	 * @param userInput
	 * @return LocalTime, formatted for the model
	 */
	public LocalTime formatTime(String userInput) throws DateTimeParseException, IllegalArgumentException {
		
		userInput = addMissingZeroAtIndexZero(userInput);
		
		LocalTime formattedTime = twentyFourHourConverter(userInput.toUpperCase());
		
		return checkTimeIsWithinLimits(formattedTime);
	} 
	
	/**
	 * Goes between timeIsValid and format time, passing the IllegalArgumentException up the stack
	 * 
	 * @param formattedTime to check
	 * @return the same if valid
	 * @throws IllegalArgumentException if not valid
	 */
	private LocalTime checkTimeIsWithinLimits(LocalTime formattedTime) throws IllegalArgumentException {
		
		try {
			if (timeIsValid(formattedTime)) {
				return formattedTime;
			}
		}
		catch (IllegalArgumentException e) {
			throw new IllegalArgumentException();
		}
		return null;
	}
	
	/**
	 * Checks user input length and adds a zero if missing - required for the DateTimeFormatter 
	 * 
	 * @param userInput
	 * @return the input string with a leading zero added, if required
	 */
	private String addMissingZeroAtIndexZero(String userInput) {
		
		if (userInput.length() == 7) {
			userInput = "0" + userInput;
		}
		return userInput;
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
		
		return LocalTime.parse(shiftTime24HourFormat.format(twelveHourTime));
	}
	
	/**
	 * Check that time falls within allowable range: 5pm and 4am
	 * 
	 * @param shiftTime
	 * @return true if time is within range, false if not
	 */
	private boolean timeIsValid(LocalTime shiftTime) {
		
		if (shiftTime.equals(EARLIEST_START.toLocalTime()) || shiftTime.equals(LATEST_FINISH.toLocalTime()) || shiftTime.equals(LocalTime.MIDNIGHT)) {
			return true;
		}
		else if (shiftTime.isBefore(LocalTime.MAX) && (shiftTime.isAfter(EARLIEST_START.toLocalTime()))) {
			return true;
		}
		else if (shiftTime.isAfter(LocalTime.MIDNIGHT) && shiftTime.isBefore(LATEST_FINISH.toLocalTime())) {
			return true;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
}