package com.revature.repositories;

import java.util.List;

import com.revature.models.BankAccount;
import com.revature.models.Transaction;
import com.revature.models.Transfer;
import com.revature.models.User;

public interface TransferRepo {

	public void getAllAccounts(User u);
	public void addTransfer(Transfer t);
	public void viewOutgoingTransfers(User u, BankAccount ba);
	public void viewIncomingTransfers(User u, BankAccount ba);
	public void acceptTransfer(Transfer t);
	public void rejectTransfer(Transfer t);
	public boolean checkAccountExists(Integer id);
	public List<Transaction> getAllTransactions();
}
