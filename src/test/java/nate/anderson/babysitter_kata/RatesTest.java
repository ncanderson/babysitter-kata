package nate.anderson.babysitter_kata;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import nate.anderson.babysitter_kata.model.Rates;

public class RatesTest {

	Rates rates;
	int rateResetTestValue = 10;
	
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
		Assert.assertEquals(12, rates.getStartToBedtimeRate());
	}
	
	@Test
	public void bedtimeToMidnightDefaultsTo8() {
		Assert.assertEquals(8, rates.getBedtimeToMidnightRate
				());
	}
	
	@Test 
	public void midnightToFinishDefaultsTo16() {
		Assert.assertEquals(16, rates.getMidnightToFinishRate());
	}
	
	@Test
	public void setAllRatesCanResetAllRatesAtOnce() {
		rates.setAllRates(rateResetTestValue, rateResetTestValue, rateResetTestValue);
		Assert.assertEquals(10, rates.getStartToBedtimeRate());
		Assert.assertEquals(10, rates.getBedtimeToMidnightRate());
		Assert.assertEquals(10, rates.getMidnightToFinishRate());
	}
	
	@Test
	public void resetStartToBedtime() {
		rates.setStartToBedtimeRate(rateResetTestValue);
		Assert.assertEquals(10, rates.getStartToBedtimeRate());
	}
	
	@Test
	public void resetBedtimeToMidnight() {
		rates.setBedtimeToMidnightRate(rateResetTestValue);
		Assert.assertEquals(10, rates.getBedtimeToMidnightRate());
	}
	
	@Test
	public void resetMidnightToFinish() {
		rates.setMidnightToFinishRate(rateResetTestValue);
		Assert.assertEquals(10, rates.getMidnightToFinishRate());
	}
	
}