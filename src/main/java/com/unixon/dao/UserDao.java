package com.unixon.dao;

import java.util.List;

import com.unixon.model.User;

public interface UserDao {
	void save(User user);
	List<User> getList();
	User getUserById(Long id);
	void update(Long id,User user);
	void delete(Long id);
	
}
