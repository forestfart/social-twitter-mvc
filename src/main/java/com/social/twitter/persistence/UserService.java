package com.social.twitter.persistence;

import java.util.Collection;

import com.social.twitter.model.User;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;


@Component("userService")
public class UserService {

	DatabaseConnector connector;

	public UserService() {
		connector = DatabaseConnector.getInstance();
	}

	public Collection<User> getAll() {
		return connector.getSession().createCriteria(User.class).list();
	}
	
	public User findByLogin(String login) {
		return (User) connector.getSession().get(User.class, login);
	}

	public void create(User user) {
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().save(user);
		transaction.commit();
	}

	public void delete(User user) {
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().delete(user);
		transaction.commit();
	}

	public void merge(User user) {
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().merge(user);
		transaction.commit();
	}
}
