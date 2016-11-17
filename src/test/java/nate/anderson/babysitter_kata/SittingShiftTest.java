package nate.anderson.babysitter_kata;

import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

public class SittingShiftTest {

	@Test
	public void babySittingShiftStoresStartTime() {
		SittingShift testShift = new SittingShift();
		testShift.setShiftStartTime("5:00");
		LocalTime testStartTime = LocalTime.of(5, 0);
		Assert.assertEquals(testShift.getShiftStartTime(), testStartTime);
	}
	
}
