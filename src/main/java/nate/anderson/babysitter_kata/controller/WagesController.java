package nate.anderson.babysitter_kata.controller;

import java.util.ArrayList;
import java.util.List;

import nate.anderson.babysitter_kata.model.SittingShift;

public class WagesController {

	private SittingShift sittingShift;
	private List<Integer> wageList;
	
	
	public WagesController(SittingShift sittingShift) {
		this.sittingShift = sittingShift;
		this.wageList = new ArrayList<Integer>();
	}
	
	public void setWageList(List<Integer> wageList) {
		this.wageList = wageList;
	}
	
	public List<Integer> getWageList() {
		return wageList;
	}
	
	public void setSittingShift(SittingShift sittingShift) {
		this.sittingShift = sittingShift;
	}
	
	public SittingShift getSittingShift() {
		return sittingShift;
	}
	
}
