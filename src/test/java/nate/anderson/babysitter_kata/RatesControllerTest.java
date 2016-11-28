package nate.anderson.babysitter_kata;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
	
	LocalDate today = LocalDate.now();
	LocalDate tomorrow = today.plusDays(1);
	
	LocalDateTime testStart = LocalDateTime.of(today, LocalTime.of(17, 0));
	LocalDateTime testBedtime = LocalDateTime.of(today, LocalTime.of(22, 0));
	LocalDateTime testEnd = LocalDateTime.of(tomorrow, LocalTime.of(4, 0));
	
	
	
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
	
	@Test 
	public void ratesControllerCalculatesTimeFromBedtimeToMidnight() {
		testShift.setAllTimes(testStart, testBedtime, testEnd);
		List<Double> testTimes = testRatesController.calculateTimes();
		Assert.assertEquals(2.0, testTimes.get(1), .05);
	}
	
	@Test 
	public void ratesControllerCalculatesTimeFromMidnightToEnd() {
		testShift.setAllTimes(testStart, testBedtime, testEnd);
		List<Double> testTimes = testRatesController.calculateTimes();
		Assert.assertEquals(4.0, testTimes.get(2), .05);
	}
	
	@Test
	public void ratesControllerCanHandleDifferentTimes() {
		LocalDateTime newStart = LocalDateTime.of(today, LocalTime.of(22, 0));
		LocalDateTime newBedtime = LocalDateTime.of(today, LocalTime.of(23, 0));
		LocalDateTime newEnd = LocalDateTime.of(tomorrow, LocalTime.of(2, 0));
		testShift.setAllTimes(newStart, newBedtime, newEnd);
		List<Double> testTimes = testRatesController.calculateTimes();
		Assert.assertEquals(1.0, testTimes.get(0), .05);
		Assert.assertEquals(1.0, testTimes.get(1), .05);
		Assert.assertEquals(2.0, testTimes.get(2), .05);
	}
	
	@Test
	public void oneHourPreBedtimeCosts12() {
		SittingShift twelveDollarShift = new SittingShift();
		twelveDollarShift.setShiftStartTime(LocalDateTime.of(today, LocalTime.of(17, 0)));
		twelveDollarShift.setBedtime(LocalDateTime.of(today, LocalTime.of(18, 0)));
		twelveDollarShift.setShiftEndTime(LocalDateTime.of(today, LocalTime.of(18, 0)));
		testRatesController.setSittingShift(twelveDollarShift);
		Assert.assertEquals(12, testRatesController.calculateCost());
	}
	
	@Test 
	public void oneHourPostBedtimeCosts8() {
		SittingShift eightDollarShift = new SittingShift();
		eightDollarShift.setShiftStartTime(LocalDateTime.of(today, LocalTime.of(17, 0)));
		eightDollarShift.setBedtime(LocalDateTime.of(today, LocalTime.of(17, 0)));
		eightDollarShift.setShiftEndTime(LocalDateTime.of(today, LocalTime.of(18, 0)));
		testRatesController.setSittingShift(eightDollarShift);
		Assert.assertEquals(8, testRatesController.calculateCost());
	}
	 
//	@Test 
//	public void oneHourPostMidnightCost16() {
//		SittingShift sixteenDollarShift = new SittingShift();
//		sixteenDollarShift.setShiftStartTime(LocalTime.of(0, 0));
//		sixteenDollarShift.setBedtime(LocalTime.of(17, 0));
//		sixteenDollarShift.setShiftEndTime(LocalTime.of(1, 0));
//		testRatesController.setSittingShift(sixteenDollarShift);
//		Assert.assertEquals(16, testRatesController.calculate());
//	}
//	
//	@Test
//	public void toHoursConvertsLongSecondsToIntegerHours() {
//		Double hours = testRatesController.toHours(3600);
//		Assert.assertEquals(1.0, hours, .05);
//	}
//	
}

