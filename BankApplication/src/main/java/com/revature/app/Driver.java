package com.revature.app;

import java.util.Scanner;

import com.revature.logging.AppLogger;
import com.revature.models.User;
import com.revature.services.BankAccountService;
import com.revature.services.BankAccountServiceImpl;
import com.revature.services.TransferService;
import com.revature.services.TransferServiceImpl;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;

public class Driver {
	
	private static Scanner s = new Scanner(System.in);
	private static User u;
	private static UserService us = new UserServiceImpl();
	private static BankAccountService bas = new BankAccountServiceImpl();
	private static TransferService ts = new TransferServiceImpl();
	
	public static void main(String[] args) {
		//AppLogger.logger.warn("Warn using AppLogger class");
		
		boolean clickedQuit = false;
		do {
			System.out.println("Choose a command below:");
			System.out.println("1. Login");
			System.out.println("2. Create new account");
			System.out.println("0. Quit");
			int input = s.nextInt();
			// call nextLine() to get rid of \n after the integer scan
			s.nextLine();
			switch (input) {
			case 1: { // Login
				login();
				break;
			}
			case 2: { // Create Account
				register();
				break;
			}
			case 0: {
				clickedQuit = true;
				break;
			}
			default: {
				System.out.println("Choice not recognized.");
				break;
			}
			}
			
		} while (!clickedQuit);
		
		s.close();
	}
	
	public static void login() {
		u = us.login(s);
		if (u == null) {
			System.out.println("Invalid login credentials.");
		} else {
			System.out.println("Successfully logged in.");
			switch(u.getType().toLowerCase()) {
			case "customer": {
				customer();
				break;
			}
			case "employee": {
				employee();
				break;
			}
			default: {
				break;
			}
			}
		}
		
	}
	
	public static void register() {
		us.register(s);
		
	}
	
	public static void customer() {
		do {
			System.out.println("Choose a command below:");
			System.out.println("1. Apply for new bank account");
			System.out.println("2. View account");
			System.out.println("3. Withdraw");
			System.out.println("4. Deposit");
			System.out.println("5. Transfers");
			System.out.println("0. Logout");
			int input = s.nextInt();
			// clear \n from input
			s.nextLine();
			switch (input) {
			case 1: { // Apply for account
				bas.apply(u, s);
				break;
			}
			case 2: { // View balance of account
				bas.viewAccount(u, s);
				break;
			}
			case 3: { // Withdraw from an account
				bas.withdraw(u, s);
				break;
			}
			case 4: { // Deposit into an account
				bas.deposit(u, s);
				break;
			}
			case 5: { // Transfer between accounts
				ts.transfer(u, s);
				break;
			}
			case 0: {
				System.out.println("Logged out");
				u = null;
				break;
			}
			default: {
				System.out.println("Choice not recognized.");
				break;
			}
			}
		} while (u != null);
	}
	
	public static void employee() {
		do {
			System.out.println("Choose a command below: ");
			System.out.println("1. View all pending accounts");
			System.out.println("2. View a customer's accounts");
			System.out.println("3. View all transaction logs");
			System.out.println("0. Logout");
			int input = s.nextInt();
			// clear \n from input
			s.nextLine();
			switch (input) {
			case 1: { // View all accounts
				bas.viewPendingAccounts(u, s);
				break;
			}
			case 2: { // View a customer's accounts
				bas.viewCustomersAccounts(u, s);
				break;
			}
			case 3: { // View transaction logs
				ts.viewAllTransactions(u, s);
				break;
			}
			case 0: {
				System.out.println("Logged out");
				u = null;
				break;
			}
			default: {
				System.out.println("Choice not recognized.");
				break;
			}
			}
		} while (u != null);
	}
	
}
