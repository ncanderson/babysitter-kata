package nate.anderson.babysitter_kata;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import nate.anderson.babysitter_kata.controller.RatesController;
import nate.anderson.babysitter_kata.model.Rates;
import nate.anderson.babysitter_kata.model.SittingShift;

public class RatesControllerTest {

	SittingShift testShift;
	Rates testRates;
	RatesController testWagesController;
	
	@Before
	public void setUp() {
		testShift = new SittingShift();
		testWagesController = new RatesController(testShift, testRates);
	}
	
	@Test
	public void wagesControllerTakesAShittingShift() {
		Assert.assertEquals(testShift, testWagesController.getSittingShift());
	} 
	
	@Test
	public void testRatesControllerSetterMethod() {
		SittingShift secondTestShift = new SittingShift();
		testWagesController.setSittingShift(secondTestShift);
		Assert.assertEquals(secondTestShift, testWagesController.getSittingShift());
	}
	
}

