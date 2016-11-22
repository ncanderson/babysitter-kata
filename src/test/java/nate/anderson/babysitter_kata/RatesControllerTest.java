package nate.anderson.babysitter_kata;

import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.List;

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
	
	LocalTime testStart = LocalTime.of(17, 0);
	LocalTime testBedtime = LocalTime.of(22, 0);
	LocalTime testEnd = LocalTime.of(4, 0);
	
	@Before
	public void setUp() {
		testShift = new SittingShift();
		testRates = new Rates();
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
	public void ratesControllerCalculatesTimeFromStartToBedtime() {
		testShift.setAllTimes(testStart, testBedtime, testEnd);
		List<Double> testTimes = testRatesController.calculateTimes();
		Assert.assertEquals(5.0, testTimes.get(0), .05);
	}
	
//	@Test 
//	public void ratesControllerCalculatesTimeFromBedtimeToMidnight() {
//		testShift.setAllTimes(testStart, testBedtime, testEnd);
//		List<Double> testTimes = testRatesController.calculateTimes();
//		Assert.assertEquals(2.0, testTimes.get(1), .05);
//	}
	
	@Test 
	public void ratesControllerCalculatesTimeFromMidnightToEnd() {
		testShift.setAllTimes(testStart, testBedtime, testEnd);
		List<Double> testTimes = testRatesController.calculateTimes();
		Assert.assertEquals(4.0, testTimes.get(2), .05);
	}
	
//	@Test
//	public void ratesControllerCanHandleDifferentTimes() {
//		testShift.setAllTimes(LocalTime.of(22, 0), LocalTime.of(23, 0), LocalTime.of(2, 0));
//		List<Double> testTimes = testRatesController.calculateTimes();
//		Assert.assertEquals(testTimes.get(0), 1.0, .05);
//		Assert.assertEquals(testTimes.get(1), 1.0, .05);
//		Assert.assertEquals(testTimes.get(2), 2.0, .05);
//	}
	
	@Test
	public void ratesControllerCanParseVariousSittingShiftTimesIntoAList() {
		
	}
	
	@Test
	public void oneHourPreBedtimeCosts12() {
		SittingShift twelveDollarShift = new SittingShift();
		twelveDollarShift.setShiftStartTime(LocalTime.of(17, 0));
		twelveDollarShift.setBedtime(LocalTime.of(18, 0));
		twelveDollarShift.setShiftEndTime(LocalTime.of(18, 0));
		testRatesController.setSittingShift(twelveDollarShift);
		Assert.assertEquals(12, testRatesController.calculate());
	}
	
//	@Test 
//	public void oneHourPostBedtimeCosts8() {
//		SittingShift eightDollarShift = new SittingShift();
//		eightDollarShift.setShiftStartTime(LocalTime.of(18, 0));
//		eightDollarShift.setBedtime(LocalTime.of(17, 0));
//		eightDollarShift.setShiftEndTime(LocalTime.of(19, 0));
//		testRatesController.setSittingShift(eightDollarShift);
//		Assert.assertEquals(8, testRatesController.calculate());
//	}
	 
//	@Test 
//	public void oneHourPostMidnightCost16() {
//		SittingShift sixteenDollarShift = new SittingShift();
//		sixteenDollarShift.setShiftStartTime(LocalTime.of(0, 0));
//		sixteenDollarShift.setBedtime(LocalTime.of(17, 0));
//		sixteenDollarShift.setShiftEndTime(LocalTime.of(1, 0));
//		testRatesController.setSittingShift(sixteenDollarShift);
//		Assert.assertEquals(16, testRatesController.calculate());
//	}
	
	@Test
	public void toHoursConvertsLongSecondsToIntegerHours() {
		Double hours = testRatesController.toHours(3600);
		Assert.assertEquals(1.0, hours, .05);
	}
	
}

