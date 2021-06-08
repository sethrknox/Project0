package com.revature.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.BankAccount;
import com.revature.models.Transaction;
import com.revature.models.Transfer;
import com.revature.models.User;
import com.revature.utils.JDBCConnection;

public class TransferRepoImpl implements TransferRepo {

	private Connection conn = JDBCConnection.getConnection();
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
	public void addTransfer(Transfer t) {
		// TODO Auto-generated method stub
		String sql = "insert into project0.transfers values (default, ?, ?, ?, 'pending') returning *;";
		String sql2 = "call project0.create_transaction(?, ?, ?, ?)";
		String sql3 = "update project0.accounts set balance = balance - ? where id = ? returning *;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, t.getSender_id());
			ps.setDouble(2, t.getAmount());
			ps.setInt(3, t.getReceiver_id());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				t.setTransfer_id(rs.getInt("id"));
				
				CallableStatement cs = conn.prepareCall(sql2);
				cs.setString(1, "transfer");
				cs.setInt(2, t.getSender_id());
				cs.setDouble(3, t.getAmount());
				cs.setInt(4, t.getTransfer_id());
				cs.execute();
				
				ps = conn.prepareStatement(sql3);
				ps.setDouble(1, t.getAmount());
				ps.setInt(2, t.getSender_id());
				rs = ps.executeQuery();
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void viewOutgoingTransfers(User u, BankAccount ba) {
		// TODO Auto-generated method stub
		String sql = "select * from project0.transfers where status = 'pending' and sender_id = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ba.getId());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Transfer t = new Transfer(rs.getInt("sender_id"), rs.getDouble("amount"), rs.getInt("receiver_id"));
				t.setTransfer_id(rs.getInt("id"));
				u.getOutgoingTransfers().add(t);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void viewIncomingTransfers(User u, BankAccount ba) {
		// TODO Auto-generated method stub
		String sql = "select * from project0.transfers where status = 'pending' and receiver_id = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ba.getId());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Transfer t = new Transfer(rs.getInt("sender_id"), rs.getDouble("amount"), rs.getInt("receiver_id"));
				t.setTransfer_id(rs.getInt("id"));
				u.getIncomingTransfers().add(t);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void acceptTransfer(Transfer t) {
		// TODO Auto-generated method stub
		String sql = "update project0.transfers set status = 'accepted' where id = ? returning *;";
		String sql2 = "update project0.accounts set balance = balance + ? where id = ? returning *;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, t.getTransfer_id());
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				ps = conn.prepareStatement(sql2);
				ps.setDouble(1, t.getAmount());
				ps.setInt(2, t.getReceiver_id());
				rs = ps.executeQuery();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void rejectTransfer(Transfer t) {
		// TODO Auto-generated method stub
		String sql = "update project0.transfers set status = 'rejected' where id = ? returning *;";
		String sql2 = "update project0.accounts set balance = balance + ? where id = ? returning *;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, t.getTransfer_id());
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				ps = conn.prepareStatement(sql2);
				ps.setDouble(1, t.getAmount());
				ps.setInt(2, t.getSender_id());
				rs = ps.executeQuery();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean checkAccountExists(Integer id) {
		String sql = "select * from project0.accounts where id = ? and status = 'open';";
		
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}

	@Override
	public List<Transaction> getAllTransactions() {
		// TODO Auto-generated method stub
		String sql = "select tn.id, tn.type, tn.ba_id, tn.amount, tf.receiver_id, tf.status from project0.transactions tn left join project0.transfers tf on tn.transfer_id = tf.id";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			List<Transaction> result = new ArrayList<Transaction>();
			while (rs.next()) {
				Transaction t = new Transaction();
				t.setId(rs.getInt("id"));
				t.setType(rs.getString("type"));
				t.setAmount(rs.getDouble("amount"));
				t.setBa_id(rs.getInt("ba_id"));
				t.setRecv_id(rs.getInt("receiver_id"));
				t.setStatus(rs.getString("status"));
				result.add(t);
			}
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
