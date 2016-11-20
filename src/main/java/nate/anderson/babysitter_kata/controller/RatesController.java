package nate.anderson.babysitter_kata.controller;

import nate.anderson.babysitter_kata.model.Rates;
import nate.anderson.babysitter_kata.model.SittingShift;

public class RatesController {

	private SittingShift sittingShift;
	private Rates rates;
	
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
	
	public int calculate() {
		return 12;
	}
	
}
