package nate.anderson.babysitter_kata;

import java.time.LocalTime;

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
	
	@Test
	public void ioControllerTakesAStringAndFormatsForShiftObject() {
		String userInput = "05:00 PM";
		testShift.setShiftStartTime(ioController.handleStartTime(userInput));
		Assert.assertEquals(LocalTime.of(17, 0), testShift.getShiftStartTime());
	}
	
	@Test
	public void handleStartTimeChecksThatStartTimeIsValid() {
		String userInput = "03:00 PM";
		testShift.setShiftStartTime(ioController.handleStartTime(userInput));
		Assert.assertNull(testShift.getShiftStartTime());
	}

}
