package com.revature.repository;

import java.util.List;

import com.revature.model.User;

public interface UserDAO {
	User getUser(long id);
	User getUser(String username);
	List<User> getUsers();
	boolean createUser(User u);
	boolean updateUser(User u);
	boolean deleteUser(User u);
	
}
