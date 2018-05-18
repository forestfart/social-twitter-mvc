package com.social.twitter.model;

import javax.persistence.*;

@Entity
@Table(name = "follow")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private User User;

    @ManyToOne
    private User following;

    public void setUser(User user) {
        User = user;
    }

    public void setFollowing(User following) {
        this.following = following;
    }

    public User getUser() {
        return User;
    }

    public User getFollowing() {
        return following;
    }
}
