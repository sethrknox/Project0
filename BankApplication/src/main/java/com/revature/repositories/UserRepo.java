package com.revature.repositories;

import com.revature.models.User;

public interface UserRepo {

	public void addUser(User u);
	public User getUser(String username, String password);
}
