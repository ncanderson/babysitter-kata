package nate.anderson.babysitter_kata;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import nate.anderson.babysitter_kata.model.Rates;

public class RatesTest {

	Rates rates;
	
	
	@Before
	public void setUp() {
		rates = new Rates();
	}
	
	@Test
	public void ratesObjectHoldsAListOfThreeRates() {
		Assert.assertEquals(3, (rates.getAllRates()).size());
	}
	
	@Test 
	public void startToBedTimeDefaultsTo12() {
		Assert.assertEquals(12, rates.getStartToBedtime());
	}
	
	@Test
	public void bedtimeToMidnightDefaultsTo8() {
		Assert.assertEquals(8, rates.getBedtimeToMidnight());
	}
	
	@Test 
	public void midnightToFinishDefaultsTo16() {
		Assert.assertEquals(16, rates.getMidnightToFinish());
	}
	
	@Test
	public void setAllRatesCanResetAllRatesAtOnce() {
		rates.setAllRates(10, 10, 10);
		Assert.assertEquals(10, rates.getStartToBedtime());
		Assert.assertEquals(10, rates.getBedtimeToMidnight());
		Assert.assertEquals(10, rates.getMidnightToFinish());
	}
	
	
	
}