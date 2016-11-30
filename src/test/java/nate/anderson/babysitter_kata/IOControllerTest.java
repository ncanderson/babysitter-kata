package nate.anderson.babysitter_kata;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.InvalidAttributesException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import nate.anderson.babysitter_kata.controller.IOController;
import nate.anderson.babysitter_kata.model.SittingShift;

public class IOControllerTest {
	
	SittingShift testShift;
	IOController ioController;
	LocalDate today;
	LocalDate tomorrow;
	String userInput;
	List<LocalTime> shiftTimesFromUsers;
	
	@Before
	public void setUp() {
		testShift = new SittingShift();
		ioController = new IOController();
		today = LocalDate.now();
		tomorrow = LocalDate.now().plusDays(1);
		shiftTimesFromUsers = new ArrayList<LocalTime>();
		shiftTimesFromUsers.add(LocalTime.of(17, 0));
		shiftTimesFromUsers.add(LocalTime.of(22, 0));
		shiftTimesFromUsers.add(LocalTime.of(3, 0));
	}
	
	@Test(expected=DateTimeParseException.class)
	public void ioControllerHandlesImproperlyFormattedStrings() {
		userInput = "Not even a time";
		LocalTime testTime = ioController.formatTime(userInput);
	}
	
	@Test
	public void ioControllerTakesAStringAndFormatsForShiftObject() {
		userInput = "05:00 PM";
		LocalTime testTime = ioController.formatTime(userInput);
		Assert.assertEquals(LocalTime.of(17, 0), testTime);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void formatTimeThrowsExceptionIfTimeIsTooEarly() {
		userInput = "03:00 PM";
		LocalTime testTime = ioController.formatTime(userInput);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void formatTimeThrowsExceptionIfTimeIsTooLate() {
		userInput = "06:00 AM";
		LocalTime testTime = ioController.formatTime(userInput);
	}
	
	@Test
	public void ioControllerHandlesLowerCaseTimeMarker() {
		userInput = "05:00 pm";
		LocalTime testTime = ioController.formatTime(userInput);
		Assert.assertEquals(LocalTime.of(17, 0), testTime);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void formatTimeThrowsExceptionEarlierTimeIsValid() {
		userInput = "03:00 PM";
		LocalTime testTime = ioController.formatTime(userInput);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void formatTimeChecksThatLaterTimeIsValid() {
		userInput = "05:00 AM";
		LocalTime testTime = ioController.formatTime(userInput);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void formatTimeChecksThatBedtimeIsWithinValidRange() {
		userInput = "05:00 AM";
		LocalTime testBedtime = ioController.formatTime(userInput);
	}
	
	@Test 
	public void midnightIsAValidValueForStartAndFinish() {
		userInput = "12:00 AM";
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
	public void formatTimeAddsZeroForSingleDigitHour() {
		LocalTime testTime = ioController.formatTime("5:00 PM");
		Assert.assertEquals(LocalTime.of(17, 0), testTime);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void noonIsInvalidForAnything() {
		String userInput = "12:00 PM";
		LocalTime testTime = ioController.formatTime(userInput);
	}
	
	@Test
	public void ioControllerHasAListForHoldingStartAndEndTimes() {
		Assert.assertNotNull(ioController.getListOfTimes());
	}
	
	@Test
	public void assignTimesChecksIncomingTimesAndHandlesSettingStart() throws InvalidAttributesException {
		ioController.assignTimes(testShift, shiftTimesFromUsers);
		Assert.assertEquals(LocalDateTime.of(today, LocalTime.of(17, 0)), testShift.getShiftStartTime());
	}
	
	@Test
	public void assingTimesSetsLocalTimeShiftStartToToday() throws InvalidAttributesException {
		ioController.assignTimes(testShift, shiftTimesFromUsers);
		Assert.assertEquals(LocalDateTime.of(today, shiftTimesFromUsers.get(0)), testShift.getShiftStartTime());
	}
	
	@Test 
	public void assignTimesChecksIncomingTimesAndHandlesSettingBedtime() throws InvalidAttributesException {
		ioController.assignTimes(testShift, shiftTimesFromUsers);
		Assert.assertEquals(LocalDateTime.of(today, LocalTime.of(22, 0)), testShift.getBedtime());
	}
	
	@Test
	public void assignTimesChecksIncomingTimesAndHandlesSettingEnd() throws InvalidAttributesException {
		ioController.assignTimes(testShift, shiftTimesFromUsers);
		Assert.assertEquals(LocalDateTime.of(tomorrow, LocalTime.of(3, 0)), testShift.getShiftEndTime());
	}
	
	@Test
	public void assignTimesChecksDifferentStartTimes() throws InvalidAttributesException {
		shiftTimesFromUsers.set(0, LocalTime.of(22, 0));
		ioController.assignTimes(testShift, shiftTimesFromUsers);
		Assert.assertEquals(LocalDateTime.of(today, LocalTime.of(22, 0)), testShift.getShiftStartTime());
	}
	
	@Test
	public void checkingAnotherStartTime() throws InvalidAttributesException {
		shiftTimesFromUsers.set(0, LocalTime.of(2, 0));
		ioController.assignTimes(testShift, shiftTimesFromUsers);
		Assert.assertEquals(LocalDateTime.of(tomorrow, LocalTime.of(2, 0)), testShift.getShiftStartTime());
	}
	
	@Test
	public void testAnotherBedtime() throws InvalidAttributesException { 
		shiftTimesFromUsers.set(1, LocalTime.of(17, 0));
		ioController.assignTimes(testShift, shiftTimesFromUsers);
		Assert.assertEquals(LocalDateTime.of(today, LocalTime.of(17, 0)), testShift.getBedtime());
	}
	
	@Test
	public void oneMoreBedtime() throws InvalidAttributesException {
		shiftTimesFromUsers.set(1, LocalTime.of(4, 0));
		ioController.assignTimes(testShift, shiftTimesFromUsers);
		Assert.assertEquals(LocalDateTime.of(tomorrow, LocalTime.of(4, 0)), testShift.getBedtime());
	}
	
	@Test
	public void testingAnotherEndTime() throws InvalidAttributesException { 
		shiftTimesFromUsers.set(2, LocalTime.of(4, 0));
		ioController.assignTimes(testShift, shiftTimesFromUsers);
		Assert.assertEquals(LocalDateTime.of(tomorrow, LocalTime.of(4, 0)), testShift.getShiftEndTime());
	}
	
	@Test
	public void testingAFinalEndTime() throws InvalidAttributesException {
		shiftTimesFromUsers.set(2, LocalTime.of(0, 0));
		ioController.assignTimes(testShift, shiftTimesFromUsers);
		Assert.assertEquals(LocalDateTime.of(tomorrow, LocalTime.of(0, 0)), testShift.getShiftEndTime());
	}
	
	@Test
	public void endTimeAfterMidnightAfterStartTimeIsTomorrow() throws InvalidAttributesException {
		ioController.assignTimes(testShift, shiftTimesFromUsers);
		Assert.assertEquals(LocalDateTime.of(tomorrow, LocalTime.of(3, 0)), testShift.getShiftEndTime());
	}
	
	@Test(expected=InvalidAttributesException.class) 
	public void endTimeCannotBeBeforeStartTime() throws InvalidAttributesException {
		shiftTimesFromUsers.set(0, LocalTime.of(3, 0));
		shiftTimesFromUsers.set(1, LocalTime.of(22, 0));
		shiftTimesFromUsers.set(2, LocalTime.of(17, 0));
		ioController.assignTimes(testShift, shiftTimesFromUsers);
	}
	 
	@Test(expected=InvalidAttributesException.class)
	public void startAndEndTimeBeingTheSameThrowsException() throws InvalidAttributesException {
		shiftTimesFromUsers.set(0, LocalTime.of(17, 0));
		shiftTimesFromUsers.set(1, LocalTime.of(22, 0));
		shiftTimesFromUsers.set(2, LocalTime.of(17, 0));
		ioController.assignTimes(testShift, shiftTimesFromUsers);
	}
	
	@Test 
	public void clearAtIndexSetsIndexZeroToNull() {
		ioController.addTimeToList(LocalTime.of(17, 0));
		ioController.addTimeToList(LocalTime.of(18, 0));
		ioController.addTimeToList(LocalTime.of(19, 0));
		ioController.clearAtIndex(0);
		LocalTime shouldBeNull = ioController.getListOfTimes().get(0);
		Assert.assertNull(shouldBeNull);
	}
	
	@Test 
	public void clearAtIndexSetsIndexOneToNull() {
		ioController.addTimeToList(LocalTime.of(17, 0));
		ioController.addTimeToList(LocalTime.of(18, 0));
		ioController.addTimeToList(LocalTime.of(19, 0));
		ioController.clearAtIndex(1);
		LocalTime shouldBeNull = ioController.getListOfTimes().get(1);
		Assert.assertNull(shouldBeNull);
	}
	
	@Test 
	public void clearAtIndexSetsIndexTwoToNull() {
		ioController.addTimeToList(LocalTime.of(17, 0));
		ioController.addTimeToList(LocalTime.of(18, 0));
		ioController.addTimeToList(LocalTime.of(19, 0));
		ioController.clearAtIndex(2);
		LocalTime shouldBeNull = ioController.getListOfTimes().get(2);
		Assert.assertNull(shouldBeNull);
	}
}
