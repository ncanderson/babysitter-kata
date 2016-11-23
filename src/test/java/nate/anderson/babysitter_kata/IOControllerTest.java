package nate.anderson.babysitter_kata;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import nate.anderson.babysitter_kata.controller.IOController;
import nate.anderson.babysitter_kata.model.SittingShift;

public class IOControllerTest {
	
	SittingShift testShift;
	IOController ioController;
	LocalDateTime today;
	String userInput;
	
	@Before
	public void setUp() {
		testShift = new SittingShift();
		ioController = new IOController();
		today = LocalDateTime.now();
	}
	
	@Test(expected=DateTimeParseException.class)
	public void ioControllerHandlesImproperlyFormattedStrings() {
		userInput = "Not even a time";
		LocalTime testTime = ioController.formatTime(userInput);
	}
	
	@Test
	public void ioControllerTakesAStringAndFormatsForShiftObject() {
		String userInput = "05:00 PM";
		LocalTime testTime = ioController.formatTime(userInput);
		Assert.assertEquals(LocalTime.of(17, 0), testTime);
	}
	
	@Test
	public void formatTimeReturnsNullIfTimeIsTooEarly() {
		userInput = "03:00 PM";
		LocalTime testTime = ioController.formatTime(userInput);
		Assert.assertNull(testTime);
	}
	
	@Test
	public void formatTimeReturnsNullIfTimeIsTooLate() {
		userInput = "06:00 AM";
		LocalTime testTime = ioController.formatTime(userInput);
		Assert.assertNull(testTime);
	}
	
	@Test
	public void ioControllerHandlesLowerCaseTimeMarker() {
		String userInput = "05:00 pm";
		LocalTime testTime = ioController.formatTime(userInput);
		Assert.assertEquals(LocalTime.of(17, 0), testTime);
	}
	
	@Test
	public void handleTimeChecksThatEarlierTimeIsValid() {
		String userInput = "03:00 PM";
		LocalTime testTime = ioController.formatTime(userInput);
		Assert.assertNull(testTime);
	}
	
	@Test
	public void formatTimeChecksThatLaterTimeIsValid() {
		String userInput = "05:00 AM";
		LocalTime testTime = ioController.formatTime(userInput);
		Assert.assertNull(testTime);
	}
	
	@Test 
	public void formatTimeChecksThatBedtimeIsWithinValidRange() {
		String userInput = "05:00 AM";
		LocalTime testBedtime = ioController.formatTime(userInput);
		Assert.assertNull(testBedtime);
	}
	
	@Test 
	public void midnightIsAValidValueForStartAndFinish() {
		String userInput = "12:00 AM";
		LocalTime start = ioController.formatTime(userInput);
		LocalTime end = ioController.formatTime(userInput);
		Assert.assertEquals(LocalTime.MIDNIGHT, start);
		Assert.assertEquals(LocalTime.MIDNIGHT, end);
	}
	
	@Test 
	public void validTimesBetweenFiveAndMidnight() {
		LocalTime testStart = ioController.formatTime("10:00 PM");
		Assert.assertEquals(LocalTime.of(22, 0), testStart);
	}
	
	@Test
	public void validTimesBetweenMidnightAndFour() {
		LocalTime testTime = ioController.formatTime("02:00 AM");
		Assert.assertEquals(LocalTime.of(2, 0), testTime);
	}
	
	@Test
	public void fivePMIsAValidTime() {
		LocalTime testTime = ioController.formatTime("05:00 PM");
		Assert.assertEquals(LocalTime.of(17, 0), testTime);
	}
	
	@Test
	public void fourAMIsAValidTime() {
		LocalTime testTime = ioController.formatTime("04:00 AM");
		Assert.assertEquals(LocalTime.of(4, 0), testTime);
	}
	
	@Test
	public void noonIsInvalidForAnything() {
		String userInput = "12:00 PM";
		LocalTime testTime = ioController.formatTime(userInput);
		Assert.assertNull(testTime);
	}
	
	@Test
	public void ioControllerHasAListForHoldingStartAndEndTimes() {
		Assert.assertNotNull(ioController.getListOfTimes());
	}
	
//	@Test
//	public void ioControllerChecksValidityOfAssignedTimes() {
//		LocalTime startTime = LocalTime.of(22, 0);
//		LocalTime badEndTime = LocalTime.of(17, 0);
//		
//	}

}
