package com.revature.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.models.BankAccount;
import com.revature.models.Transaction;
import com.revature.models.Transfer;
import com.revature.models.User;
import com.revature.repositories.TransferRepo;
import com.revature.repositories.TransferRepoImpl;

public class TransferServiceImpl implements TransferService {

	private TransferRepo tr = new TransferRepoImpl();
	@Override
	public void transfer(User u, Scanner s) {
		// TODO Auto-generated method stub
		System.out.println("Choose an option below:\n1. Create a new transfer request\n2. View your pending transfers\n3. Accept/Reject incoming transfers\n0. Go back");
		int input = s.nextInt();
		// clear \n from input
		s.nextLine();
		switch(input) {
		case 1: { // Create a transfer
			createTransfer(u, s);
			break;
		}
		case 2: { // View pending transfers
			viewOutgoingTransfers(u, s);
			break;
		}
		case 3: { // Accept/Reject incoming transfers
			viewIncomingTransfers(u, s);
			break;
		}
		case 0: {}
		default: {
			break;
		}
		}		
		
	}
	
	@Override
	public void createTransfer(User u, Scanner s) {
		tr.getAllAccounts(u);
		System.out.println("Select the account id you want to transfer from: ");
		printAccounts(u);
		int input = s.nextInt();
		s.nextLine();
		//find account from input id
		for (BankAccount ba : u.getAccounts()) {
			if (ba.getId() == input) {
				if ("open".equalsIgnoreCase(ba.getStatus())) {
					System.out.println(ba);
					System.out.println("Enter the amount you want to transfer: ");
					double amount = s.nextDouble();
					System.out.println("Enter the account id you want to transfer the funds to: ");
					input = s.nextInt();
					s.nextLine();
					// TODO add check if id of receiving account exists
					// maybe a tr.checkAccount(input);
					if (!tr.checkAccountExists(input)) {
						System.out.println("That account id is inactive.");
					} else if (amount < 0) {
						System.out.println("Error: Cannot transfer a negative amount.");
					} else if (amount > ba.getBalance()) {
						System.out.println("Error: Not enough funds to transfer.");
					} else {
						Transfer t = new Transfer(ba.getId(), amount, input);
						tr.addTransfer(t);
						System.out.println("The transfer has been initiated. The recipient must accept/reject the transfer request.");
					}
				} else {
					System.out.println("Cannot transfer from inactive accounts.");
				}
				return;
			}
		}
		System.out.println("Account id not found.");
	}
	
	@Override
	public void viewOutgoingTransfers(User u, Scanner s) {
		tr.getAllAccounts(u);
		u.getOutgoingTransfers().clear();
		for (BankAccount ba : u.getAccounts()) {
			tr.viewOutgoingTransfers(u, ba);
		}
		if (u.getOutgoingTransfers().isEmpty()) {
			System.out.println("You do not have any outgoing transfers at this time.");
		} else {
			System.out.println("These are your outgoing transfer requests: ");
			for (Transfer t : u.getOutgoingTransfers()) {
				System.out.println(t);
			}
		}
		
	}
	
	@Override
	public void viewIncomingTransfers(User u, Scanner s) {
		tr.getAllAccounts(u);
		u.getIncomingTransfers().clear();
		for (BankAccount ba : u.getAccounts()) {
			tr.viewIncomingTransfers(u, ba);
		}
		if (u.getIncomingTransfers().isEmpty()) {
			System.out.println("You have no incoming requests at this time.");
		} else {
			System.out.println("Select a transfer id to accept/reject: ");
			for (Transfer t : u.getIncomingTransfers())	{
				System.out.println(t);
			}
			int input = s.nextInt();
			s.nextLine();
			for (Transfer t : u.getIncomingTransfers()) {
				if (input == t.getTransfer_id()) {
					System.out.println("Would you like to accept or reject this transfer? (a/r)");
					String answer = s.nextLine();
					if ("a".equalsIgnoreCase(answer)) {
						tr.acceptTransfer(t);
						System.out.println("Transfer accepted.");
					} else if ("r".equalsIgnoreCase(answer)) {
						tr.rejectTransfer(t);
						System.out.println("Transfer rejected.");
					} else {
						System.out.println("Invalid command.");
					}
				}
				return;
			}
			System.out.println("Transfer id not found.");
		}
		
	}
	
	@Override
	public void viewAllTransactions(User u, Scanner s) {
		// TODO Auto-generated method stub
		System.out.println("List of all transactions: ");
		List<Transaction> tlist = new ArrayList<Transaction>();
		tlist = tr.getAllTransactions();
		for (Transaction t : tlist) {
			System.out.println(t);
		}
	}
	
	//helper method to print a user's accounts
	public void printAccounts(User u) {
		for (BankAccount ba : u.getAccounts()) {
			System.out.println("Name: " + ba.getName() + ", id: " + ba.getId() + ", status: " + ba.getStatus());
		}
	}

}
