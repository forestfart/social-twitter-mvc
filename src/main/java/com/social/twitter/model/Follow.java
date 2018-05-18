package com.social.twitter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "follow")
public class Follow {

    @Id
    private User User;

    @Column(name = "following")
    private User following;

}
