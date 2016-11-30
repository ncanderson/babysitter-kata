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
		
		System.out.println();
		System.out.println("Babysitting shift calculator");
		System.out.println("============================");
		System.out.println();
		
		while (true) {
			
			LocalTime formattedTime = null;
			String input = null;
			ioController.getListOfTimes().clear();
			
			while (true) {
				System.out.println("Please enter shift start time: ");
				input = scanner.nextLine();
				try {
					formattedTime = ioController.formatTime(input);
					ioController.addTimeToList(formattedTime);
					break;
				}
				catch (DateTimeParseException e) {
					System.out.println("Please enter a time in the format 'HH:mm am/pm'");
				}
				catch (IllegalArgumentException e) {
					System.out.println("Time must be between 5:00 PM and 4:00 AM");
				}
			}
			
			while (true) {
				System.out.println("Please enter bedtime: ");
				input = scanner.nextLine();
				try {
					formattedTime = ioController.formatTime(input);
					ioController.addTimeToList(formattedTime);
					break;
				}
				catch (DateTimeParseException e) {
					System.out.println("Please enter a time in the format 'HH:mm am/pm'");
				}
				catch (IllegalArgumentException e) {
					System.out.println("Time must be between 5:00 PM and 4:00 AM");
				}
			}
			
			while (true) {
				System.out.println("Please enter shift end time: ");
				input = scanner.nextLine();
				try {
					formattedTime = ioController.formatTime(input);
					ioController.addTimeToList(formattedTime);
					ioController.assignTimes(sittingShift, ioController.getListOfTimes());
					break;
				}
				catch (DateTimeParseException e) {
					System.out.println("Please enter a time in the format 'HH:mm am/pm'");
				}
				catch (IllegalArgumentException e) {
					System.out.println("Time must be between 5:00 PM and 4:00 AM");
				}
				catch (InvalidAttributesException e) {
					System.out.println("End time cannot be before start time, please try again");
					ioController.clearAtIndex(2);
				}
			}
			break;
		}
		
		ratesController = new RatesController(sittingShift, rates);
		
		System.out.println();
		System.out.println("=======================");
		System.out.println("Total for the night is:");
		System.out.println("$" + ratesController.calculateCost());
		System.out.println();
		
	}

}
