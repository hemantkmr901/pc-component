package com.unixon.service;

import java.util.List;

import com.unixon.model.User;

public interface UserService {

	void saveService(User user);
	List<User> getListService();
	void updateService(Long id, User user);
	User getUserByIdService(Long id);
	void deleteService(Long id);
	
	//for holding User object
	User getUser();
	void setUser(User user);
}
