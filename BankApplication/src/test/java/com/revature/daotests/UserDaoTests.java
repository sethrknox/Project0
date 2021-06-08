package com.revature.daotests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.models.User;
import com.revature.repositories.UserRepo;
import com.revature.repositories.UserRepoImpl;

import junit.framework.Assert;

public class UserDaoTests {

	private UserRepo ur = new UserRepoImpl();
	
	@Test
	public void registerAccountTest() {
		User u = new User("seth", "password", "Seth", "Knox");
		u.setId(1);
		
		assertEquals(u, ur.getUser("seth", "password"));
		//fail("Not yet implemented");
	}

}
