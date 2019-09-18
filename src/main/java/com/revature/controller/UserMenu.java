package com.revature.controller;

import java.text.NumberFormat;
import java.util.Scanner;
import org.apache.log4j.Logger;

import com.revature.exception.AccessDeniedUserNotAdminException;
import com.revature.exception.DuplicateUserInDatabaseException;
import com.revature.exception.NoTransactionsForUserInDatabaseException;
import com.revature.exception.NotEnoughMoneyInAccountException;
import com.revature.exception.TooManyLoginAttemptsProgramExitsException;
import com.revature.exception.UserEnteredInvalidPasswordException;
import com.revature.exception.UserEnteredInvalidUsernameException;
import com.revature.model.User;
import com.revature.service.Service;

public class UserMenu {
	
	private static int createAccountTryCounter = 0;
	private static int optionNotRecognizedForMenuMainCounter = 0;
	private static Scanner sc = new Scanner(System.in);
	private static Service service = new Service();
	private static NumberFormat toCurrencyFormat = NumberFormat.getCurrencyInstance();
	
	private static Logger logger = Logger.getLogger(UserMenu.class);
	private static int loginCounter;

	public UserMenu() {
		menuMain();
	}
	
	private static void menuMain() {
		logger.info("Menu started");
		System.out.println("Welcome to Unsecure Bank - a new way to do money!");
		System.out.println("Please make a selection from the options below: ");
		System.out.println("1 - Login to your account");
		System.out.println("2 - Create New Account");
		System.out.println("3 - Exit");
		
		String usersOption = sc.nextLine();
		logger.debug("received user input: " + usersOption);
		
		switch(usersOption) {
		case "1":
			optionNotRecognizedForMenuMainCounter = 0;
			try {
				logger.info("User login started");
				System.out.println("Enter username: ");
				String userEnteredUsername = sc.nextLine();
				logger.debug("Received user input: " + userEnteredUsername);
				
				while(service.verifyCorrectUserEnteredUsername(userEnteredUsername) == false) {
					System.out.println("Username not recognized, please try again: ");
					userEnteredUsername = sc.nextLine();
					logger.debug("Received user input: " + userEnteredUsername);
				}
				service.setSelectedUser(service.getUser(userEnteredUsername));
				
				System.out.println("Enter password: ");
				String userEnteredPassword = sc.nextLine();
				logger.debug("Received user input: " + userEnteredPassword);
				while(service.verifyCorrectUserEnteredPassword(userEnteredPassword) == false) {
					System.out.println("Password not recognized, please try again: ");
					userEnteredPassword = sc.nextLine();
					loginCounter += 1;
					logger.debug("Received user input: " + userEnteredPassword);
					if (loginCounter >= 3) {
						throw new TooManyLoginAttemptsProgramExitsException();
					}
				} 
				System.out.println("Glad to have you back, " + userEnteredUsername
							+ "!");
			} catch (TooManyLoginAttemptsProgramExitsException e) {
				System.out.println(e.getMessage());
				System.exit(0);
			}
			
			menuForUser();
			break;
		case "2":
			optionNotRecognizedForMenuMainCounter = 0;
			logger.info("Create new account option started");
			
			do {
				System.out.println("Please enter your desired username: ");
				String userEnteredUsername = sc.nextLine();
				logger.debug("Received user input: " + userEnteredUsername);
			
				System.out.println("Please enter your desired password: ");
				String userEnteredPassword = sc.nextLine();
				logger.debug("Received user input: " + userEnteredPassword);	
				try {
					service.createAccount(userEnteredUsername, userEnteredPassword);
					service.setSelectedUser(service.getUser(userEnteredUsername));
					System.out.println("Glad to have you as a new customer, " + 
							service.getSelectedUser().getUserName() + "!");
					System.out.println("Here is $50.00 to start out!");
					System.out.println("Thank you for choosing the most unsecure bank " +
							"in the Mid-Atlantic Region!");
					service.depositMoney(50);
					createAccountTryCounter = 0;
				} catch (UserEnteredInvalidUsernameException e) {
					System.out.println(e.getMessage());
					createAccountTryCounter++;
				} catch (UserEnteredInvalidPasswordException e) {
					System.out.println(e.getMessage());
					createAccountTryCounter++;
				} catch (DuplicateUserInDatabaseException e) {
					System.out.println(e.getMessage());
					createAccountTryCounter++;
				}
			} while (createAccountTryCounter >0);
			
			
			menuForUser();
			break;
		case "3":
			optionNotRecognizedForMenuMainCounter = 0;
			System.out.println("Your money has been compromised, goodbye!");
			System.exit(0);
		default:
			System.out.println("Option not recognized");
			optionNotRecognizedForMenuMainCounter++;
			logger.debug(usersOption + " was not recognized.");
			logger.debug("This has occurred " + optionNotRecognizedForMenuMainCounter + " times.");
			
			if (optionNotRecognizedForMenuMainCounter > 10) {
				logger.fatal("Failed to recognize option 10 times, exiting");
				System.exit(1);
			}
			
			menuMain();
			break;
		}
		
	}
	
	public static void menuForUser() {
		logger.info("Menu for user started");
		loginCounter = 0;
		
		System.out.println("Welcome to Unsecure Bank - a new way to do money!");
		System.out.println("How may I assist you today? "
				+ "Please make a selection from the options below: ");
		System.out.println("1 - Display My Balance");
		System.out.println("2 - Display All Transactions");
		System.out.println("3 - Withdrawal Money");
		System.out.println("4 - Deposit Money");
		System.out.println("5 - Modify Account Access");
		System.out.println("6 - Logout");
		System.out.println("7 - Exit");
		
		String usersOption = sc.nextLine();
		logger.debug("received user input: " + usersOption);
		
		switch(usersOption) {
		case "1":
			optionNotRecognizedForMenuMainCounter = 0;
			System.out.println("Even though your spending on lamp shades is out of hand, "
					+ "your balance is " + toCurrencyFormat.format(service.getBalance()));
			System.out.println("");
			menuForUser();
			break;
		case "2":
			optionNotRecognizedForMenuMainCounter = 0;
			try {
				System.out.println(service.getTransactions());
			} catch (NoTransactionsForUserInDatabaseException e) {
				System.out.println(e.getMessage());
			}
			menuForUser();
			break;
		case "3":
			optionNotRecognizedForMenuMainCounter = 0;
			System.out.println("How much would you like to withdrawal?");
			System.out.println("Please use the follow1ing format:  X.XX");
			usersOption = sc.nextLine();
			usersOption.trim();
			if (Service.formatCheckerForMoney(usersOption)) {
				double withdrawalAmount = Double.valueOf(usersOption);
				try {
					service.withdrawalMoney(withdrawalAmount);
					System.out.println("WATCH OUT! Here comes a bucket of money with "
							+ toCurrencyFormat.format(withdrawalAmount) + " in it!\n");
				} catch (NotEnoughMoneyInAccountException e) {
					System.out.println(e.getMessage());
					System.out.println("You do not have " + toCurrencyFormat.format(withdrawalAmount) + " in your account\n");
				}
			} else {
				System.out.println("Money format must only include 0123456789.");
				System.out.println("Please try again!\n");
			}
			
			menuForUser();
			break;
		case "4":
			optionNotRecognizedForMenuMainCounter = 0;
			System.out.println("How much would you like to deposit?");
			System.out.println("Please use the following format:  X.XX");

			usersOption = sc.nextLine();
			usersOption.trim();
			if (Service.formatCheckerForMoney(usersOption)) {
				double depositAmount = Double.valueOf(usersOption);
				service.depositMoney(depositAmount);
				System.out.println("Your account has been struggling, thank you for your deposit of "
						+ toCurrencyFormat.format(depositAmount) + "!");
				System.out.println("");
			} else {
				System.out.println("Money format must only include 0123456789.");
				System.out.println("Please try again!\n");
			}
			
			menuForUser();
			break;
		case "5":
			optionNotRecognizedForMenuMainCounter = 0;
			try {
				throw new AccessDeniedUserNotAdminException();
			} catch (AccessDeniedUserNotAdminException e) {
				System.out.println(e.getMessage() + "\n");
				
				menuForUser();
			}
			break;			
		case "6":
			optionNotRecognizedForMenuMainCounter = 0;
			loginCounter = 0;
			service.logoutOfAccount();
			System.out.println("You have been successfully logged out of your account");
			System.out.println("Hopefully your money is safe!\n");
			menuMain();
			break;			
		case "7":
			optionNotRecognizedForMenuMainCounter = 0;
			System.out.println("Your money has been compromised, goodbye!");
			System.exit(0);
		default:
			System.out.println("Option not recognized");
			optionNotRecognizedForMenuMainCounter++;
			logger.debug(usersOption + " was not recognized.");
			logger.debug("This has occurred " + optionNotRecognizedForMenuMainCounter + " times.");
			
			if (optionNotRecognizedForMenuMainCounter > 10) {
				logger.fatal("Failed to recognize option 10 times, exiting");
				System.exit(1);
			}
			
			menuForUser();
			break;
		}
	}
	
	
}
