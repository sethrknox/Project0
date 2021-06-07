package com.revature.repositories;

import com.revature.models.BankAccount;
import com.revature.models.User;

public interface BankAccountRepo {

	// Customer methods
	public void addAccount(User u, BankAccount ba);
	public void getAllAccounts(User u);
	public void withdraw(BankAccount ba, double amount);
	public void deposit(BankAccount ba, double amount);
	
	// Employee methods
	public void getAllPendingAccounts(User u);
	public void updateAccountStatus(BankAccount ba, String status);
	public User getCustomer(BankAccount ba);
}
