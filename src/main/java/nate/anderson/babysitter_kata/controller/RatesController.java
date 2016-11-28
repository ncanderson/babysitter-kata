package nate.anderson.babysitter_kata.controller;

import java.util.ArrayList;
import java.util.List;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import nate.anderson.babysitter_kata.model.Rates;
import nate.anderson.babysitter_kata.model.SittingShift;

public class RatesController {

	private SittingShift sittingShift;
	private Rates rates;
	
	LocalDateTime midnight = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(0, 0));
	
	public RatesController(SittingShift sittingShift, Rates rates) {
		this.sittingShift = sittingShift;
		this.rates = rates;
	}

	public void setSittingShift(SittingShift sittingShift) {
		this.sittingShift = sittingShift;
	}
	
	public SittingShift getSittingShift() {
		return sittingShift;
	}
	
	public void setRates(Rates rates) {
		this.rates = rates;
	}
	
	public Rates getRates() {
		return rates;
	}
	
	public List<Double> calculateTimes() {
		List<Double> sittingTimes = new ArrayList<Double>();
		
		sittingTimes.add(toHours(sittingShift.getShiftStartTime().until(sittingShift.getBedtime(), ChronoUnit.SECONDS)));
		sittingTimes.add(toHours(sittingShift.getBedtime().until(midnight, ChronoUnit.SECONDS)));
		sittingTimes.add(toHours(midnight.until(sittingShift.getShiftEndTime(), ChronoUnit.SECONDS)));
		
		
		
//		List<LocalDateTime> shiftTimes = sittingShift.getAllTimes();
//		List<Duration> durations = new ArrayList<Duration>();
//		
//		durations.add(0, Duration.between(sittingShift.getShiftStartTime(), sittingShift.getBedtime()));
//		durations.add(1, Duration.between(sittingShift.getBedtime(), LocalTime.MAX));
//		durations.add(2, Duration.between(LocalTime.MIN, sittingShift.getShiftEndTime()));
//		
//		for (Duration duration: durations) {
//			sittingTimes.add(toHours(duration.getSeconds()));
//		}
//		
//		if (sittingShift.getBedtime().isAfter(sittingShift.getShiftStartTime())) {
//			durations.add(0, (Duration.between(sittingShift.getShiftStartTime(), sittingShift.getBedtime())));
//		}
//		else {
//			durations.add(1, (Duration.between(sittingShift.getShiftStartTime(), sittingShift.getShiftEndTime())));
//		}
//		sittingTimes.add(5);
//		sittingTimes.add(2);
//		sittingTimes.add(4);
//		
		return sittingTimes;
	}
	
	public int calculate() {
		List<Integer> shiftRates = rates.getAllRates();
		int totalCost = 0;
		
		List<Duration> durations = new ArrayList<Duration>();
		
		if (sittingShift.getBedtime().isAfter(sittingShift.getShiftStartTime())) {
			durations.add(0, (Duration.between(sittingShift.getShiftStartTime(), sittingShift.getBedtime())));
		}
		else {
			durations.add(1, (Duration.between(sittingShift.getShiftStartTime(), sittingShift.getShiftEndTime())));
		}
		
		for (int i = 0; i < durations.size(); i++) {
			totalCost += ((durations.get(i)).toHours() * shiftRates.get(i)); 
		}
		
		return totalCost;
	}
	
	public Double toHours(long seconds) {
		return Double.valueOf(seconds / 3600);
	}
}
