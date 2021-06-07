package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.BankAccount;
import com.revature.models.User;
import com.revature.utils.JDBCConnection;

public class BankAccountRepoImpl implements BankAccountRepo {

	private Connection conn = JDBCConnection.getConnection();
	
	@Override
	public void addAccount(User u, BankAccount ba) {
		// TODO Auto-generated method stub
		String sql = "insert into project0.accounts values (default, ?, ?, ?, 'pending') returning *";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,  ba.getName());
			ps.setDouble(2, ba.getBalance());
			ps.setInt(3, u.getId());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				ba.setId(rs.getInt("id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void getAllAccounts(User u) {
		// TODO Auto-generated method stub
		
		// Clear previous account info from user object
		u.getAccounts().clear();
		
		String sql = "select a.id, a.name, a.balance, a.status from project0.accounts as a left join project0.users as u on a.uid = u.id where u.id = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, u.getId());
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				BankAccount ba = new BankAccount();
				ba.setId(rs.getInt("id"));
				ba.setName(rs.getString("name"));
				ba.setBalance(rs.getDouble("balance"));
				ba.setStatus(rs.getString("status"));
				if (!u.getAccounts().contains(ba)) {
					u.getAccounts().add(ba);
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void withdraw(BankAccount ba, double amount) {
		// TODO Auto-generated method stub
		String sql = "update project0.accounts set balance = balance - ? where id = ? returning *";
		String sql2 = "insert into project0.transactions values (default, 'withdrawal', ?, ?, null) returning *;";
		
		try {
			// Perform withdrawal
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, amount);
			ps.setInt(2, ba.getId());
			ResultSet rs = ps.executeQuery();
			
			// Log withdrawal in transactions table
			if (rs.next()) {
				ps = conn.prepareStatement(sql2);
				ps.setInt(1, ba.getId());
				ps.setDouble(2, amount);
				rs = ps.executeQuery();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void deposit(BankAccount ba, double amount) {
		// TODO Auto-generated method stub
		String sql = "update project0.accounts set balance = balance + ? where id = ? returning *";
		String sql2 = "insert into project0.transactions values (default, 'deposit', ?, ?, null) returning *;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, amount);
			ps.setInt(2, ba.getId());
			ResultSet rs = ps.executeQuery();
			
			// Log deposit in transactions table
			if (rs.next()) {
				ps = conn.prepareStatement(sql2);
				ps.setInt(1, ba.getId());
				ps.setDouble(2, amount);
				rs = ps.executeQuery();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void getAllPendingAccounts(User u) {
		// TODO Auto-generated method stub
		String sql = "select * from project0.accounts where status = 'pending';";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				BankAccount ba = new BankAccount();
				ba.setId(rs.getInt("id"));
				ba.setName(rs.getString("name"));
				ba.setBalance(rs.getDouble("balance"));
				ba.setStatus(rs.getString("status"));
				if (!u.getAccounts().contains(ba)) {
					u.getAccounts().add(ba);
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateAccountStatus(BankAccount ba, String status) {
		// TODO Auto-generated method stub
		String sql = "update project0.accounts set status = ? where id = ? returning *";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, ba.getId());
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public User getCustomer(BankAccount ba) {
		// TODO Auto-generated method stub
		String sql = "select * from project0.users u left join project0.accounts a on u.id = a.uid where a.id = ?;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ba.getId());
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				User u = new User();
				u.setFirst(rs.getString("first"));
				u.setLast(rs.getString("last"));
				u.setId(rs.getInt("id"));
				return u;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
