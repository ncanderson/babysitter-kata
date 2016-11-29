package nate.anderson.babysitter_kata.controller;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import nate.anderson.babysitter_kata.model.Rates;
import nate.anderson.babysitter_kata.model.SittingShift;

public class RatesController {

	private SittingShift sittingShift;
	private Rates rates;
	ChronoUnit seconds = ChronoUnit.SECONDS;
	
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
	
	public List<Double> calculateBillableTimes() {
		List<Double> sittingTimesInHours = new ArrayList<Double>();
		
		LocalDateTime start = sittingShift.getShiftStartTime();
		LocalDateTime bed = sittingShift.getBedtime();
		LocalDateTime end = sittingShift.getShiftEndTime();
		
		Double startToBed = toHours(start.until(bed, seconds));
		Double bedToMidnight = toHours(bed.until(midnight, seconds));
		Double afterMidnight = toHours(midnight.until(end, seconds));
		Double startToEnd = toHours(start.until(end, seconds));
		
		if (end.isBefore(midnight) || end.equals(midnight)) {
			if (bed.isAfter(end)) {
				sittingTimesInHours.add(startToEnd);
			}
			else {
				sittingTimesInHours.add(startToBed);				
			}
			
			if (start.isAfter(bed)) {
				sittingTimesInHours.add(toHours(start.until(end, seconds)));
			}
			else {
				sittingTimesInHours.add(toHours(bed.until(end, seconds)));				
			}
			sittingTimesInHours.add(0.0);
		}
		else {
			if (bed.isBefore(midnight)) {
				if (bed.isBefore(start)) {
					sittingTimesInHours.add(0.0);
					sittingTimesInHours.add(toHours(start.until(midnight, seconds)));
				}
				else {
					sittingTimesInHours.add(startToBed);
					sittingTimesInHours.add(bedToMidnight);					
				}
			}
			else {
				sittingTimesInHours.add(toHours(start.until(midnight, seconds)));
				sittingTimesInHours.add(0.0);
			}
			if (start.isAfter(midnight)) {
				sittingTimesInHours.add(toHours(start.until(end, seconds)));
			}
			else {
				sittingTimesInHours.add(afterMidnight);
			}
		}
		
		return sittingTimesInHours;
	}
	
	public int calculateCost() {
		List<Integer> shiftRates = rates.getAllRates();
		List<Double> durations = calculateBillableTimes();
		int totalCost = 0;
		
		for (int i = 0; i < durations.size(); i++) {
			if (durations.get(i) > 0) {
				Double modifiedHours = Math.ceil(durations.get(i));
				totalCost += (modifiedHours * shiftRates.get(i));
			}
		}

		return totalCost;
	}
	
	public Double toHours(long seconds) {
		double hours = Double.valueOf(seconds / 3600.0); 
		return hours;
	}
}
