package nate.anderson.babysitter_kata;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import nate.anderson.babysitter_kata.controller.WagesController;
import nate.anderson.babysitter_kata.model.SittingShift;

public class WagesControllerTest {

	SittingShift testShift;
	WagesController testWagesController;
	
	@Before
	public void setUp() {
		testShift = new SittingShift();
		testWagesController = new WagesController(testShift);
	}
	
	@Test
	public void wagesControllerTakesAShittingShift() {
		Assert.assertEquals(testShift, testWagesController.getSittingShift());
	}
	
	@Test
	public void testWageControllerSetterMethod() {
		SittingShift secondTestShift = new SittingShift();
		testWagesController.setSittingShift(secondTestShift);
		Assert.assertEquals(secondTestShift, testWagesController.getSittingShift());
	}
	
	@Test
	public void wagesControllerHasAListOfThreeWageLevels() {
		Assert.assertEquals(3, (testWagesController.getWageList()).size());
	}
}

