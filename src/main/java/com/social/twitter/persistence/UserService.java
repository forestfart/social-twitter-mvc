package com.social.twitter.persistence;

import java.util.Collection;
import java.util.List;

import com.social.twitter.model.User;
import org.hibernate.Query;
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

	public Collection<User> getAllByHql() {
		String hql = "FROM User";
		Query query = connector.getSession().createQuery(hql);
		return query.list();
	}

	public User findById(String id) {
		return (User)connector.getSession().get(User.class, id);
	}

	public User findByLogin(String searchLogin) {
		String hql = "FROM User s WHERE s.id = " + searchLogin;
		Query query = connector.getSession().createQuery(hql);
		return (User)query.uniqueResult();
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
