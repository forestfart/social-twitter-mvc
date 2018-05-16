package com.social.twitter.model;

import javax.persistence.*;


@Entity
@Table(name = "tweets")
public class Tweet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "date")
	private String date;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tweetUser")
	private User tweetUser;

	@Column(name = "content")
	private String content;

	public Tweet(String date, User tweetUser, String content) {
		this.date = date;
		this.tweetUser = tweetUser;
		this.content = content;
	}

	public Tweet() {
	}

	public long getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public User getTweetUser() {
		return tweetUser;
	}

	public String getContent() {
		return content;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setTweetUser(User tweetUser) {
		this.tweetUser = tweetUser;
	}
}