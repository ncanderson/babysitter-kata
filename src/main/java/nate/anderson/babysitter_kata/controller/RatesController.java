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
		List<Double> sittingTimesInHours = new ArrayList<Double>();
		
		sittingTimesInHours.add(toHours(sittingShift.getShiftStartTime().until(sittingShift.getBedtime(), ChronoUnit.SECONDS)));
		if (sittingShift.getShiftEndTime().isBefore(midnight)) {
			sittingTimesInHours.add(toHours(sittingShift.getBedtime().until(sittingShift.getShiftEndTime(), ChronoUnit.SECONDS)));
		}
		else {
			sittingTimesInHours.add(toHours(sittingShift.getBedtime().until(midnight, ChronoUnit.SECONDS)));			
		}
		sittingTimesInHours.add(toHours(midnight.until(sittingShift.getShiftEndTime(), ChronoUnit.SECONDS)));
		
		return sittingTimesInHours;
	}
	
	public int calculateCost() {
		List<Integer> shiftRates = rates.getAllRates();
		List<Double> durations = calculateTimes();
		int totalCost = 0;
		
		for (int i = 0; i < durations.size(); i++) {
			if (durations.get(i) > 0) {
				totalCost += ((durations.get(i)) * shiftRates.get(i));
			}
		}

		return totalCost;
	}
	
	public Double toHours(long seconds) {
		return Double.valueOf(seconds / 3600);
	}
}
