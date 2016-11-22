package nate.anderson.babysitter_kata;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import nate.anderson.babysitter_kata.model.SittingShift;

public class SittingShiftTest {
	
	SittingShift testShift;
	LocalDate today = LocalDate.now();
	LocalDateTime testTime = LocalDateTime.of(today, LocalTime.of(2, 0));
	
	@Before
	public void setUp() {
		testShift = new SittingShift();
	}
	
	@Test
	public void sittingShiftStoresStartTime() {
		LocalTime testStart = LocalTime.of(17, 0);
		testShift.setShiftStartTime(LocalDateTime.of(today, testStart));
		Assert.assertEquals(LocalDateTime.of(today, testStart), testShift.getShiftStartTime());
	}
	
	@Test 
	public void sittingShiftStoresEndTime() {
		LocalTime testEnd = LocalTime.of(3, 0);
		testShift.setShiftEndTime(LocalDateTime.of(today, testEnd));
		Assert.assertEquals(LocalDateTime.of(today, testEnd), testShift.getShiftEndTime());
	}
	
	@Test 
	public void sittingShiftStoresBedTime() {
		LocalTime testBedtime = LocalTime.of(22, 0);
		testShift.setBedtime(LocalDateTime.of(today, testBedtime));
		Assert.assertEquals(LocalDateTime.of(today, testBedtime), testShift.getBedtime());
	}
	
	@Test  
	public void sittingShiftStoresAnotherBedTime() {
		LocalTime testBedtime = LocalTime.of(3, 0);
		testShift.setBedtime(LocalDateTime.of(today, testBedtime));
		Assert.assertEquals(LocalDateTime.of(today, testBedtime), testShift.getBedtime());
	}
	
	@Test 
	public void settingDifferentTimesForShiftStart() {
		LocalTime testStart = LocalTime.of(22, 0);
		testShift.setShiftStartTime(LocalDateTime.of(today, testStart));
		Assert.assertEquals(LocalDateTime.of(today, testStart), testShift.getShiftStartTime());
	}

	@Test 
	public void sittingShiftStoresDifferentEndTime() {
		LocalTime testEnd = LocalTime.of(1, 0);
		testShift.setShiftEndTime(LocalDateTime.of(today, testEnd));
		Assert.assertEquals(LocalDateTime.of(today, testEnd), testShift.getShiftEndTime());
	}
	
	@Test
	public void timeInputIsStoredIn24HourTime() {
		testShift.setShiftStartTime(LocalDateTime.of(today, LocalTime.of(17, 0)));
		Assert.assertEquals(LocalTime.of(17, 0), testShift.getShiftStartTime().toLocalTime());
	}
	
	@Test
	public void sittingShiftPackagesAllTimes() {
		Assert.assertEquals(3, testShift.getAllTimes().size());
	}
	
	@Test 
	public void allTimesCanBeSetAtOnce() {
		testShift.setAllTimes(testTime, testTime, testTime);
		Assert.assertEquals(testTime, testShift.getShiftStartTime());
		Assert.assertEquals(testTime, testShift.getBedtime());
		Assert.assertEquals(testTime, testShift.getShiftEndTime());
	}

}
