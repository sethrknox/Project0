package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.User;
import com.revature.utils.JDBCConnection;

public class UserRepoImpl implements UserRepo {

	private Connection conn = JDBCConnection.getConnection();
	
	@Override
	public void addUser(User u) {
		// TODO Auto-generated method stub
		String sql = "insert into project0.users values (default, ?, ?, ?, ?, ?) returning *;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getFirst());
			ps.setString(4, u.getLast());
			ps.setString(5, u.getType());
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				u.setId(rs.getInt("id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public User getUser(String username, String password) {
		// TODO Auto-generated method stub
		String sql = "select * from project0.users where username = ? and password = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setUsername(username);
				u.setPassword(password);
				u.setFirst(rs.getString("first"));
				u.setLast(rs.getString("last"));
				u.setType(rs.getString("type"));
				return u;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public boolean exists(String username) {
		// TODO Auto-generated method stub
		String sql = "select * from project0.users where username = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	

}
