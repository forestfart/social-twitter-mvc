package com.social.twitter.persistence;

import com.social.twitter.model.Tweet;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("tweetingService")
public class TweetingService {

	DatabaseConnector connector;

	public TweetingService() {
		connector = DatabaseConnector.getInstance();
	}

	public void create(Tweet tweet) {
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().save(tweet);
		transaction.commit();
	}

	public Collection<Tweet> getAll() {
		return connector.getSession().createCriteria(Tweet.class).list();
	}

	public Collection<Tweet> getAllByHql() {
		String hql = "FROM Tweet";
		Query query = connector.getSession().createQuery(hql);
		return query.list();
	}

	public void merge(Tweet tweet) {
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().merge(tweet);
		transaction.commit();
	}
}