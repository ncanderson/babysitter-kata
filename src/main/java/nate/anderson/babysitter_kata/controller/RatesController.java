package nate.anderson.babysitter_kata.controller;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import nate.anderson.babysitter_kata.model.Rates;
import nate.anderson.babysitter_kata.model.SittingShift;

public class RatesController {
	
	// Takes a sitting shift and current rates to calculate cost
	private SittingShift sittingShift;
	private Rates rates;
	TemporalUnit seconds = ChronoUnit.SECONDS;
	
	// Rates and times are calculated from today with midnight falling on tomorrow
	LocalDateTime midnight = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(0, 0));
	
	/**
	 * Takes a shift and rates
	 * @param sittingShift times will have been set with iocontroller
	 * @param rates are brought in with default values
	 */
	public RatesController(SittingShift sittingShift, Rates rates) {
		this.sittingShift = sittingShift;
		this.rates = rates;
	}

	/**
	 * Getters and setters 
	 */
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
	
	/**
	 * Calculates cost of the night from the times held in the sitting shift 
	 * 
	 * @return totalCost for shift
	 */
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
	
	/** 
	 * Calculates the hours between each of the three (or four for midnight) important times in the sitting shift
	 * Does not round up here, leave that to calculate cost method
	 * 
	 * @return a list of hours
	 */
	public List<Double> calculateBillableTimes() {
		List<Double> sittingTimesInHours = new ArrayList<Double>();
		
		LocalDateTime start = sittingShift.getShiftStartTime();
		LocalDateTime bed = sittingShift.getBedtime();
		LocalDateTime end = sittingShift.getShiftEndTime();
		
		Double startToBed = toHours(start.until(bed, seconds));
		Double startToMidnight = toHours(start.until(midnight, seconds));
		Double startToEnd = toHours(start.until(end, seconds));
		Double bedToMidnight = toHours(bed.until(midnight, seconds));
		Double bedToEnd = toHours(bed.until(end, seconds));
		Double afterMidnight = toHours(midnight.until(end, seconds));
		Double noTime = 0.0;
		
		if (end.isBefore(midnight) || end.equals(midnight)) {
			if (bed.isAfter(end)) {
				sittingTimesInHours.add(startToEnd);
			}
			else {
				sittingTimesInHours.add(startToBed);				
			}
			
			if (start.isAfter(bed)) {
				sittingTimesInHours.add(startToEnd);
			}
			else {
				sittingTimesInHours.add(bedToEnd);				
			}
			sittingTimesInHours.add(noTime);
		}
		else {
			if (bed.isBefore(midnight)) {
				if (bed.isBefore(start)) {
					sittingTimesInHours.add(noTime);
					sittingTimesInHours.add(startToMidnight);
				}
				else {
					sittingTimesInHours.add(startToBed);
					sittingTimesInHours.add(bedToMidnight);					
				}
			}
			else {
				sittingTimesInHours.add(startToMidnight);
				sittingTimesInHours.add(noTime);
			}
			 
			if (start.isAfter(midnight)) {
				sittingTimesInHours.add(startToEnd);
			}
			else {
				sittingTimesInHours.add(afterMidnight);
			}
		}
		
		return sittingTimesInHours;
	}
	
	/**
	 * Takes seconds between two shift times and converts to hours
	 * Maintains partial hours, which are rounded (if necessary) in calculateCost()
	 *  
	 * @param seconds between shift times
	 * @return those seconds as an
	 */
	public Double toHours(long seconds) {
		double hours = Double.valueOf(seconds / 3600.0); 
		return hours;
	}
}
