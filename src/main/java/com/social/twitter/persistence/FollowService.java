package com.social.twitter.persistence;

import com.social.twitter.model.Follow;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("followService")
public class FollowService {

    DatabaseConnector connector;

    public FollowService() {
        connector = DatabaseConnector.getInstance();
    }

    public void create(Follow follow) {
        Transaction transaction = connector.getSession().beginTransaction();
        connector.getSession().save(follow);
        transaction.commit();
    }

    public Collection<Follow> getAll() {
        return connector.getSession().createCriteria(Follow.class).list();
    }

    public void merge(Follow follow) {
        Transaction transaction = connector.getSession().beginTransaction();
        connector.getSession().merge(follow);
        transaction.commit();
    }
}
