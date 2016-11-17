package nate.anderson.babysitter_kata;

import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

public class SittingShiftTest {
	
	@Test
	public void sittingShiftStoresStartTime() {
		SittingShift testShift = new SittingShift();
		testShift.setShiftStartTime("5:00");
		LocalTime testStartTime = LocalTime.of(5, 0);
		Assert.assertEquals(testShift.getShiftStartTime(), testStartTime);
	}
	
	@Test 
	public void sittingShiftStoresEndTime() {
		SittingShift testShift = new SittingShift();
		testShift.setShiftEndTime("3:00");
		LocalTime testEndTime = LocalTime.of(3, 0);
		Assert.assertEquals(testShift.getShiftEndTime(), testEndTime);
	}
	
	@Test 
	public void settingDifferentTimesForShiftStart() {
		SittingShift testShift = new SittingShift();
		testShift.setShiftStartTime("10:00");
		LocalTime testStartTime = LocalTime.of(10, 0);
		Assert.assertEquals(testShift.getShiftStartTime(), testStartTime);
	}

	@Test 
	public void settingDifferentTimesForShiftEnd() {
		SittingShift testShift = new SittingShift();
		testShift.setShiftEndTime("7:00");
		LocalTime testEndTime = LocalTime.of(7, 0);
		Assert.assertEquals(testShift.getShiftEndTime(), testEndTime);
	}
}
