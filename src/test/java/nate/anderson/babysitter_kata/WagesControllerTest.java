package nate.anderson.babysitter_kata;

import org.junit.Assert;
import org.junit.Test;

import nate.anderson.babysitter_kata.controller.WagesController;
import nate.anderson.babysitter_kata.model.SittingShift;

public class WagesControllerTest {

	@Test
	public void wagesControllerTakesAShittingShift() {
		SittingShift testShift = new SittingShift();
		WagesController testWagesController = new WagesController(testShift);
		Assert.assertEquals(testShift, testWagesController.getSittingShift());
	}
	
}
