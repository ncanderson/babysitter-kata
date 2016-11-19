package nate.anderson.babysitter_kata;

import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
		testShift.setShiftStartTime("05:00 PM");
		Assert.assertEquals(LocalTime.of(17, 0), testShift.getShiftStartTime());
	}
	
	@Test 
	public void sittingShiftStoresEndTime() {
		testShift.setShiftEndTime("03:00 AM");
		testEndTime = LocalTime.of(3, 0);
		Assert.assertEquals(testShift.getShiftEndTime(), testEndTime);
	}
	
	@Test 
	public void settingDifferentTimesForShiftStart() {
		testShift.setShiftStartTime("10:00 PM");
		testStartTime = LocalTime.of(22, 0);
		Assert.assertEquals(testShift.getShiftStartTime(), testStartTime);
	}

	@Test 
	public void settingDifferentTimesForShiftEnd() {
		testShift.setShiftEndTime("03:00 AM");
		Assert.assertEquals(LocalTime.of(3, 0), testShift.getShiftEndTime());
	}
	
	@Test
	public void timeInputIsStoredIn24HourTime() {
		testShift.setShiftStartTime("05:00 PM");
		Assert.assertEquals(LocalTime.of(17, 0), testShift.getShiftStartTime());
	}
	
	@Test
	public void invalidShiftStartTimeDoesNotSetShiftStartAttribute() {
		testShift.setShiftStartTime("03:00 PM");
		Assert.assertNull(testShift.getShiftStartTime());
	}
}
