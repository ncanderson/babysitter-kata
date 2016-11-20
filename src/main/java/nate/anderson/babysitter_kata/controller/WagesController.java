package nate.anderson.babysitter_kata.controller;

import nate.anderson.babysitter_kata.model.SittingShift;

public class WagesController {

	private SittingShift sittingShift;
	
	public WagesController(SittingShift sittingShift) {
		this.sittingShift = sittingShift;
	}
	
	public void setSittingShift(SittingShift sittingShift) {
		this.sittingShift = sittingShift;
	}
	
	public SittingShift getSittingShift() {
		return sittingShift;
	}
	
}
