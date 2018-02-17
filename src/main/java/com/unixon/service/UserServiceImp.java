package com.unixon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unixon.dao.UserDao;
import com.unixon.model.User;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserDao userDao;
	
	private User user;

	@Transactional
	public void saveService(User user) {
		userDao.save(user);
	}

	@Transactional(readOnly = true)
	public List<User> getListService() {
		return userDao.getList();
	}

	@Transactional
	public void updateService(Long id, User user) {
		System.out.println("user");
		userDao.update(id,user);
	}
	
	@Transactional(readOnly=true)
	public User getUserByIdService(Long id) {
		return userDao.getUserById(id);
	}

	@Transactional
	public void deleteService(Long id) {
		userDao.delete(id);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
