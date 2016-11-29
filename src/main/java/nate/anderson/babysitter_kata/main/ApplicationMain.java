package nate.anderson.babysitter_kata.main;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import javax.naming.directory.InvalidAttributesException;

import nate.anderson.babysitter_kata.controller.IOController;
import nate.anderson.babysitter_kata.controller.RatesController;
import nate.anderson.babysitter_kata.model.Rates;
import nate.anderson.babysitter_kata.model.SittingShift;

public class ApplicationMain {
	
	Rates rates;
	SittingShift sittingShift;
	IOController ioController;
	RatesController ratesController;
	
	Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		ApplicationMain applicationMain = new ApplicationMain();
		applicationMain.run();
	}
	
	public void run() {
		rates = new Rates();
		sittingShift = new SittingShift();
		ioController = new IOController();
		
		while (true) {
			
			LocalTime formattedTime = null;
			String input = null;
			ioController.getListOfTimes().clear();
			
			System.out.println("Please enter shift start time: ");
			try {
				input = scanner.nextLine();
				formattedTime = ioController.formatTime(input);
				ioController.addTimeToList(formattedTime);
			}
			catch (DateTimeParseException e) {
				System.out.println("Please enter a time in the format 'HH:mm am/pm'");
			}
			
			System.out.println("Please enter bedtime: ");
			try {
				input = scanner.nextLine();
				formattedTime = ioController.formatTime(input);
				ioController.addTimeToList(formattedTime);
			}
			catch (DateTimeParseException e) {
				System.out.println("Please enter a time in the format 'HH:mm am/pm'");
			}
			
			System.out.println("Please enter shift end time: ");
			try {
				input = scanner.nextLine();
				formattedTime = ioController.formatTime(input);
				ioController.addTimeToList(formattedTime);
			}
			catch (DateTimeParseException e) {
				System.out.println("Please enter a time in the format 'HH:mm am/pm'");
			}
		
			try {
				ioController.assignTimes(sittingShift, ioController.getListOfTimes());
				ratesController = new RatesController(sittingShift, rates);
				break;
			} 
			catch (InvalidAttributesException e) {
				System.out.println("One or more times are invalid, please try again");
			}
			
		}
		
		System.out.println();
		System.out.println("=======================");
		System.out.println("Total for the night is:");
		System.out.println("$ " + ratesController.calculateCost());
		
	}

}
