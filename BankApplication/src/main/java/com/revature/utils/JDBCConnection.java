package com.revature.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
	
public class JDBCConnection {
	private static Connection conn = null;
	
	// method to get connection
	public static Connection getConnection() {
		
		try {
			// Check if connection exists
			if (conn == null) {
				/*
				 * hot-fix to ensure that the driver loads correctly
				 * when our application starts
				 * */
				Class.forName("org.postgresql.Driver");
				
				// In order to establish a connection to our DB
				// we need our credentials : url, username, password
				
				Properties props = new Properties();
				InputStream input = JDBCConnection.class.getClassLoader().getResourceAsStream("connection.properties");
				props.load(input);
				String url = props.getProperty("url");
				String un = props.getProperty("username");
				String pw = props.getProperty("password");
				conn = DriverManager.getConnection(url, un, pw);
				return conn;
			} else {
				return conn;
			}
		} catch (SQLException | IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/*
	// Test if our connection works
	public static void main(String[] args) {
		Connection conn = JDBCConnection.getConnection();
		if (conn != null) {
			System.out.println("Connection successful");
		} else {
			System.out.println("Connection unsuccessful");
		}
	}
	*/
	
	
}
