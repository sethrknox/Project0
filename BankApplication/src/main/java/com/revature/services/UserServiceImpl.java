package com.revature.services;

import java.util.Scanner;

import com.revature.logging.AppLogger;
import com.revature.models.User;
import com.revature.repositories.UserRepo;
import com.revature.repositories.UserRepoImpl;

public class UserServiceImpl implements UserService{

	private UserRepo ur = new UserRepoImpl();
	@Override
	public User login(Scanner s) {
		// TODO Auto-generated method stub
		// collect user input and login
		System.out.println("Enter your username: ");
		String un = s.nextLine();
		System.out.println("Enter your password: ");
		String pw = s.nextLine();
		return ur.getUser(un, pw);
	}

	@Override
	public void register(Scanner s) {
		// TODO Auto-generated method stub
		// collect user input and create account
		User u = new User();
		System.out.println("Enter your first name: ");
		u.setFirst(s.nextLine());
		System.out.println("Enter your last name: ");
		u.setLast(s.nextLine());
		System.out.println("Enter a username: ");
		u.setUsername(s.nextLine());
		System.out.println("Enter a password: ");
		u.setPassword(s.nextLine());
		u.setType("customer");
		System.out.println("Here is the information you entered: ");
		System.out.println("Username: " + u.getUsername());
		System.out.println("Name: " + u.getFirst() + " " + u.getLast());
		System.out.println("Is this information correct? (y/n)");
		String input = s.nextLine();
		if("y".equalsIgnoreCase(input)) {
			ur.addUser(u);
			if (u.getId() != 0) {
				AppLogger.logger.info("Registered account number: " + u.getId());
				System.out.println("Account successfully created.");
			} else {
				System.out.println("Error creating account, please try again.");
			}
		} else {
			System.out.println("Account creation aborted.");
		}
	}

}
