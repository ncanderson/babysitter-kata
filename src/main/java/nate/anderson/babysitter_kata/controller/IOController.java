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
		
		for (LocalTime shiftTime : shiftTimes) {
			if (shiftTime == null) {
				throw new InvalidAttributesException();
			}
		}
		
		if (shiftTimes.get(0).isBefore(END_TIME)) {
			sittingShift.setShiftStartTime(LocalDateTime.of(TOMORROW, shiftTimes.get(0)));
		}
		else {
			sittingShift.setShiftStartTime(LocalDateTime.of(TODAY, shiftTimes.get(0)));			
		}
		
		if (shiftTimes.get(1).isAfter(LocalTime.NOON)) {
			sittingShift.setBedtime(LocalDateTime.of(TODAY, shiftTimes.get(1)));
		}
		else {
			sittingShift.setBedtime(LocalDateTime.of(TOMORROW, shiftTimes.get(1)));
		}
		
		if (shiftTimes.get(2).isAfter(LocalTime.NOON)) {
			sittingShift.setShiftEndTime(LocalDateTime.of(TODAY, shiftTimes.get(2)));
		}
		else if (shiftTimes.get(2).isBefore(LocalTime.NOON)) {
			sittingShift.setShiftEndTime(LocalDateTime.of(TOMORROW, shiftTimes.get(2)));
		}
		else {
			throw new InvalidAttributesException();
		}
		
			
		if (sittingShift.getShiftEndTime().isBefore(sittingShift.getShiftStartTime())) {
			throw new InvalidAttributesException();
		}
		
		if (sittingShift.getShiftStartTime().equals(sittingShift.getShiftEndTime())) {
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
		LocalTime formattedTime = twentyFourHourConverter(userInput.toUpperCase());
		
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