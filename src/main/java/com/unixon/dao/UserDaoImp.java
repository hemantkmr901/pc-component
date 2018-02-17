package com.unixon.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.unixon.model.User;

@Repository
public class UserDaoImp implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	public List<User> getList() {
		@SuppressWarnings("unchecked")
		TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from user");
		return query.getResultList();
	}

	public User getUserById(Long id) {
		User user = sessionFactory.getCurrentSession().get(User.class, id);
		return user;
	}

	public void update(Long id, User user) {
		Session session = sessionFactory.getCurrentSession();
		User user2 = session.byId(User.class).load(id);
		if (user.getEmail() == null) {
			user2.setName(user.getName());
			user2.setPhotoLink(user.getPhotoLink());
			session.flush();
		} else {
			user2.setName(user.getName());
			user2.setEmail(user.getEmail());
			user2.setPhotoLink(user.getPhotoLink());
			session.flush();
		}
	}

	public void delete(Long id) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.byId(User.class).load(id);
	      session.delete(user);
	}

}
