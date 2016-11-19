package nate.anderson.babysitter_kata;

import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import nate.anderson.babysitter_kata.model.SittingShift;

public class SittingShiftTest {
	
	SittingShift testShift;
	LocalTime testStartTime;
	LocalTime testEndTime;
	
	@Before
	public void setUp() {
		testShift = new SittingShift();
	}
	
	@Test
	public void sittingShiftStoresStartTime() {
		testShift.setShiftStartTime(LocalTime.of(17, 0));
		Assert.assertEquals(LocalTime.of(17, 0), testShift.getShiftStartTime());
	}
	
	@Test 
	public void sittingShiftStoresEndTime() {
		testShift.setShiftEndTime(LocalTime.of(3, 0));
		testEndTime = LocalTime.of(3, 0);
		Assert.assertEquals(testShift.getShiftEndTime(), testEndTime);
	}
	
	@Test 
	public void settingDifferentTimesForShiftStart() {
		testShift.setShiftStartTime(LocalTime.of(22, 0));
		testStartTime = LocalTime.of(22, 0);
		Assert.assertEquals(testShift.getShiftStartTime(), testStartTime);
	}

	@Test  
	public void settingDifferentTimesForShiftEnd() {
		testShift.setShiftEndTime(LocalTime.of(3, 0));
		Assert.assertEquals(LocalTime.of(3, 0), testShift.getShiftEndTime());
	}
	
	@Test
	public void timeInputIsStoredIn24HourTime() {
		testShift.setShiftStartTime(LocalTime.of(17, 0));
		Assert.assertEquals(LocalTime.of(17, 0), testShift.getShiftStartTime());
	}
}
