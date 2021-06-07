package com.revature.services;

import java.util.Scanner;

import com.revature.models.User;

public interface TransferService {

	public void transfer(User u, Scanner s);
	public void createTransfer(User u, Scanner s);
	public void viewOutgoingTransfers(User u, Scanner s);
	public void viewIncomingTransfers(User u, Scanner s);
	
	public void viewAllTransactions(User u, Scanner s);
}
