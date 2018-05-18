package com.social.twitter.persistence;

import com.social.twitter.model.Follow;
import com.social.twitter.model.User;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

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

/*    public List<User> getFollowedUsers(String searchLogin) {
            Query query = connector.getSession().createQuery("FROM Follow f WHERE f.user.login = :searchLogin").setParameter("searchLogin", searchLogin);
        return query.list();
    }*/
}
