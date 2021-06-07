package com.revature.services;

import java.util.Scanner;

import com.revature.models.User;

public interface BankAccountService {
	// Customer methods
	public void apply(User u, Scanner s);
	public void viewAccount(User u, Scanner s);
	public void withdraw(User u, Scanner s);
	public void deposit(User u, Scanner s);
	
	// Employee methods
	public void viewPendingAccounts(User u, Scanner s);
	public void viewCustomersAccounts(User u, Scanner s);
}
