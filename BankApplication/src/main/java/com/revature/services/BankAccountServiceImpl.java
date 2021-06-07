package com.revature.services;

import java.util.Scanner;

import com.revature.models.BankAccount;
import com.revature.models.User;
import com.revature.repositories.BankAccountRepo;
import com.revature.repositories.BankAccountRepoImpl;

public class BankAccountServiceImpl implements BankAccountService {

	private BankAccountRepo bar = new BankAccountRepoImpl();
	@Override
	public void apply(User u, Scanner s) {
		// TODO Auto-generated method stub
		System.out.println("Enter a name for your new bank account: ");
		String name = s.nextLine();
		System.out.println("Enter your starting balance");
		double balance = s.nextDouble();
		// clear \n
		s.nextLine();
		BankAccount ba = new BankAccount();
		ba.setName(name);
		ba.setBalance(balance);
		bar.addAccount(u, ba);
	}

	@Override
	public void viewAccount(User u, Scanner s) {
		// TODO Auto-generated method stub
		bar.getAllAccounts(u);
		System.out.println("Select the account id you want to view: ");
		printAccounts(u);
		int input = s.nextInt();
		// clear new line from scanner
		s.nextLine();
		for (BankAccount ba : u.getAccounts()) {
			if (ba.getId() == input) {
				if ("open".equalsIgnoreCase(ba.getStatus())) {
					System.out.println("Balance of account " + input + ": " + ba.getBalance());
				} else if (!"open".equalsIgnoreCase(ba.getStatus())) {
					System.out.println("That account is not open");
				} else {
					System.out.println("Account id not found");
				}
			}
		}
	}

	@Override
	public void withdraw(User u, Scanner s) {
		// TODO Auto-generated method stub
		bar.getAllAccounts(u);
		System.out.println("Select an account you want to withdraw from: ");
		printAccounts(u);
		int input = s.nextInt();
		// clear new line from scanner
		s.nextLine();
		//find account from input id
		for (BankAccount ba : u.getAccounts()) {
			if (ba.getId() == input) {
				if ("open".equalsIgnoreCase(ba.getStatus())) {
					//ask how much to withdraw and check that it does not overdraft
					System.out.println("Balance of account " + input + ": " + ba.getBalance());
					System.out.println("Enter the amount you want to withdraw: ");
					double amount = s.nextDouble();
					s.nextLine();
					if (amount > ba.getBalance()) {
						System.out.println("Error: Cannot overdraft with this account.");
					} else if (amount < 0) {
						System.out.println("Error: Cannot withdraw a negative amount.");
					} else {
						bar.withdraw(ba, amount);
						System.out.println("Successfully withdrawn: " + amount);
					}
				} else {
					System.out.println("Cannot withdraw from inactive accounts.");
				}
			}
		}
		
		
	}

	@Override
	public void deposit(User u, Scanner s) {
		// TODO Auto-generated method stub
		bar.getAllAccounts(u);
		System.out.println("Select an account you want to deposit into: ");
		printAccounts(u);
		int input = s.nextInt();
		// clear \n
		s.nextLine();
		//find account from input id
		for (BankAccount ba : u.getAccounts()) {
			if (ba.getId() == input) {
				if ("open".equalsIgnoreCase(ba.getStatus())) {
					//ask how much to deposit and check that it is not negative
					System.out.println("Balance of account " + input + ": " + ba.getBalance());
					System.out.println("Enter the amount you want to deposit: ");
					double amount = s.nextDouble();
					s.nextLine();
					if (amount < 0) {
						System.out.println("Error: Cannot deposit a negative amount.");
					} else {
						bar.deposit(ba, amount);
						System.out.println("Successfully deposited: " + amount);
					}
				} else {
					System.out.println("Cannot deposit into inactive accounts.");
				}
			} 
		}
		
	}

	@Override
	public void viewPendingAccounts(User u, Scanner s) {
		// TODO Auto-generated method stub
		u.getAccounts().clear();
		bar.getAllPendingAccounts(u);
		System.out.println("Select an account id you want to manage: ");
		printAccounts(u);
		int input = s.nextInt();
		s.nextLine();
		
		for (BankAccount ba : u.getAccounts()) {
			if (ba.getId() == input) {
				User customer = bar.getCustomer(ba);
				System.out.println("Customer id: " + customer.getId() + ", Customer: " + customer.getFirst() + " " + customer.getLast() + ", Bank account id: " + ba.getId() + ", Initial balance: " + ba.getBalance());
				System.out.println("Would you like to approve or deny this bank account? (a/d)");
				String choice = s.nextLine();
				if ("a".equalsIgnoreCase(choice)) {
					bar.updateAccountStatus(ba, "open");
					System.out.println("Successfully approved account appliction.");
				} else if ("d".equalsIgnoreCase(choice)) {
					bar.updateAccountStatus(ba, "denied");
					System.out.println("Successfully denied account application.");
				} else {
					System.out.println("Choice not recognized.");
				}
				return;
			}
		}
		System.out.println("Account id not found;");
	}

	@Override
	public void viewCustomersAccounts(User u, Scanner s) {
		// TODO Auto-generated method stub
		u.getAccounts().clear();
		System.out.println("Enter the id of the customer's accounts you want to view: ");
		int input = s.nextInt();
		s.nextLine();
		User customer = new User();
		customer.setId(input);
		viewAccount(customer, s);
	}
	
	//helper method to print a user's accounts
	public void printAccounts(User u) {
		for (BankAccount ba : u.getAccounts()) {
			System.out.println("Name: " + ba.getName() + ", id: " + ba.getId() + ", status: " + ba.getStatus());
		}
	}

}
