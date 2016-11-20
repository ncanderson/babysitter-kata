package nate.anderson.babysitter_kata;

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
	
	@Before
	public void setUp() {
		testShift = new SittingShift();
		ioController = new IOController();
	}
	
	@Test(expected=DateTimeParseException.class)
	public void ioControllerHandlesImproperlyFormattedStrings() {
		String userInput = "050:00 PM";
		testShift.setShiftStartTime(ioController.handleTime(userInput));
	}
	
	
	@Test
	public void ioControllerTakesAStringAndFormatsForShiftObject() {
		String userInput = "05:00 PM";
		testShift.setShiftStartTime(ioController.handleTime(userInput));
		Assert.assertEquals(LocalTime.of(17, 0), testShift.getShiftStartTime());
	}
	
	@Test
	public void ioControllerHandlesLowerCaseTimeMarker() {
		String userInput = "05:00 pm";
		testShift.setShiftStartTime(ioController.handleTime(userInput));
		Assert.assertEquals(LocalTime.of(17, 0), testShift.getShiftStartTime());
	}
	
	@Test
	public void handleTimeChecksThatEarlierTimeIsValid() {
		String userInput = "03:00 PM";
		testShift.setShiftStartTime(ioController.handleTime(userInput));
		Assert.assertNull(testShift.getShiftStartTime());
	}
	
	@Test
	public void handleTimeChecksThatLaterTimeIsValid() {
		String userInput = "05:00 AM";
		testShift.setShiftEndTime(ioController.handleTime(userInput));
		Assert.assertNull(testShift.getShiftStartTime());
	}
	
	@Test
	public void midnightIsAValidValueForStartAndFinish() {
		String userInput = "12:00 AM";
		testShift.setShiftStartTime(ioController.handleTime(userInput));
		testShift.setShiftEndTime(ioController.handleTime(userInput));
		Assert.assertEquals(LocalTime.of(0, 0), testShift.getShiftStartTime());
		Assert.assertEquals(LocalTime.of(0, 0), testShift.getShiftEndTime());
	}
	
	@Test 
	public void validTimesBetweenFiveAndMidnight() {
		testShift.setShiftStartTime(ioController.handleTime("10:00 PM"));
		Assert.assertEquals(LocalTime.of(22, 0), testShift.getShiftStartTime());
	}
	
	@Test
	public void validTimesBetweenMidnightAndFour() {
		testShift.setShiftEndTime(ioController.handleTime("02:00 AM"));
		Assert.assertEquals(LocalTime.of(2, 0), testShift.getShiftEndTime());
	}
	
	@Test
	public void fivePMIsAValidTime() {
		testShift.setShiftStartTime(ioController.handleTime("05:00 PM"));
		Assert.assertEquals(LocalTime.of(17, 0), testShift.getShiftStartTime());
	}
	
	@Test
	public void fourAMIsAValidTime() {
		testShift.setShiftEndTime(ioController.handleTime("04:00 AM"));
		Assert.assertEquals(LocalTime.of(4, 0), testShift.getShiftEndTime());
	}
	
	@Test
	public void noonIsInvalidForAnything() {
		String userInput = "12:00 PM";
		testShift.setShiftStartTime(ioController.handleTime(userInput));
		testShift.setShiftEndTime(ioController.handleTime(userInput));
		Assert.assertNull(testShift.getShiftStartTime());
		Assert.assertNull(testShift.getShiftEndTime());
	}

}
