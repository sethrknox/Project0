package com.revature.services;

import java.util.Scanner;

import com.revature.models.User;

public interface UserService {

	public User login(Scanner s);
	public void register(Scanner s);
}
