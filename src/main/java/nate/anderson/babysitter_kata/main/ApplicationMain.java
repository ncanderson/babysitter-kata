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
			
			while (true) {
				System.out.println("Please enter shift start time: ");
				try {
					input = scanner.nextLine();
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
				try {
					input = scanner.nextLine();
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
				try {
					input = scanner.nextLine();
					formattedTime = ioController.formatTime(input);
					ioController.addTimeToList(formattedTime);
					ioController.assignTimes(sittingShift, ioController.getListOfTimes());
					ratesController = new RatesController(sittingShift, rates);
					break;
				}
				catch (DateTimeParseException e) {
					System.out.println("Please enter a time in the format 'HH:mm am/pm'");
				}
				catch (IllegalArgumentException e) {
					System.out.println("Time must be between 5:00 PM and 4:00 AM");
				}
				catch (InvalidAttributesException e) {
					System.out.println("End time cannot be after start time, please try again");
				}
			}
			break;
		}
		
		System.out.println();
		System.out.println("=======================");
		System.out.println("Total for the night is:");
		System.out.println("$" + ratesController.calculateCost());
		
	}

}
