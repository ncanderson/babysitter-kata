package nate.anderson.babysitter_kata;

import java.time.LocalTime;
import java.util.InputMismatchException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import nate.anderson.babysitter_kata.controller.RatesController;
import nate.anderson.babysitter_kata.model.Rates;
import nate.anderson.babysitter_kata.model.SittingShift;

public class RatesControllerTest {

	SittingShift testShift;
	Rates testRates;
	RatesController testRatesController;
	
	@Before
	public void setUp() {
		testShift = new SittingShift();
		testRatesController = new RatesController(testShift, testRates);
	}
	
	@Test
	public void wagesControllerTakesAShittingShift() {
		Assert.assertEquals(testShift, testRatesController.getSittingShift());
	} 
	
	@Test
	public void testRatesControllerSetterMethod() {
		SittingShift secondTestShift = new SittingShift();
		testRatesController.setSittingShift(secondTestShift);
		Assert.assertEquals(secondTestShift, testRatesController.getSittingShift());
	}
	
	@Test
	public void oneHourPreBedtimeCosts12() {
		SittingShift twelveDollarShift = new SittingShift();
		twelveDollarShift.setShiftStartTime(LocalTime.of(17, 0));
		twelveDollarShift.setBedtime(LocalTime.of(20, 0));
		testRatesController.setSittingShift(twelveDollarShift);
		Assert.assertEquals(12, testRatesController.calculate());
	}
	
}

