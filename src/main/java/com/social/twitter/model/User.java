package com.social.twitter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

	@Id
	private String login;

	@JsonIgnore
	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "tweets_id")
	Set<User> tweets = new HashSet<>();

	public void setLogin(String login) {
		this.login = login;
	}

	public void setTweets(Set<User> tweets) {
		this.tweets = tweets;
	}

	public String getLogin() {
		return login;
	}

	public Set<User> getTweets() {
		return tweets;
	}
}
