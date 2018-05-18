package com.social.twitter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	@Column(name = "login")
	private String login;

	@JsonIgnore
	@OneToMany(
		targetEntity = Tweet.class,
		mappedBy = "tweetUser",
		cascade = CascadeType.ALL)
	private Set<Tweet> tweets;


	/*@ManyToMany(cascade = CascadeType.ALL, mappedBy = "subscribtions")
	public Set<User> users;*/

	public User(String login, Set<Tweet> tweets) {
		this.login = login;
		this.tweets = new HashSet<>();
	//	this.users = new HashSet<>();
	}

	public User() {
		this(null , null);
	}

	public String getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public Set<Tweet> getTweets() {
		return tweets;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setTweets(Set<Tweet> tweets) {
		this.tweets = tweets;
	}

	public void setId(String id) {
		this.id = id;
	}
}
