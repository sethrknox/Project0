package com.revature.models;

import java.util.ArrayList;
import java.util.List;

public class User {

	private Integer id;
	private String username;
	private String password;
	private String first;
	private String last;
	private String type;
	
	private List<BankAccount> accounts = new ArrayList<BankAccount>();
	private List<Transfer> outgoingTransfers = new ArrayList<Transfer>();
	private List<Transfer> incomingTransfers = new ArrayList<Transfer>();
	
	public User() {}

	// Employees will be hard-coded in the DB script
	public User(String username, String password, String first, String last) {
		super();
		this.username = username;
		this.password = password;
		this.first = first;
		this.last = last;
		this.type = "customer";
	}

	public User(Integer id, String username, String password, String first, String last) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.first = first;
		this.last = last;
		this.type = "customer";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

	public List<BankAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<BankAccount> accounts) {
		this.accounts = accounts;
	}

	public List<Transfer> getOutgoingTransfers() {
		return outgoingTransfers;
	}

	public void setOutgoingTransfers(List<Transfer> transfers) {
		this.outgoingTransfers = transfers;
	}
	
	public List<Transfer> getIncomingTransfers() {
		return incomingTransfers;
	}

	public void setIncomingTransfers(List<Transfer> transfers) {
		this.incomingTransfers = transfers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accounts == null) ? 0 : accounts.hashCode());
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((incomingTransfers == null) ? 0 : incomingTransfers.hashCode());
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		result = prime * result + ((outgoingTransfers == null) ? 0 : outgoingTransfers.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (accounts == null) {
			if (other.accounts != null)
				return false;
		} else if (!accounts.equals(other.accounts))
			return false;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (incomingTransfers == null) {
			if (other.incomingTransfers != null)
				return false;
		} else if (!incomingTransfers.equals(other.incomingTransfers))
			return false;
		if (last == null) {
			if (other.last != null)
				return false;
		} else if (!last.equals(other.last))
			return false;
		if (outgoingTransfers == null) {
			if (other.outgoingTransfers != null)
				return false;
		} else if (!outgoingTransfers.equals(other.outgoingTransfers))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", first=" + first + ", last="
				+ last + ", type=" + type + ", accounts=" + accounts + ", outgoingTransfers=" + outgoingTransfers
				+ ", incomingTransfers=" + incomingTransfers + "]";
	}

	
	
}
