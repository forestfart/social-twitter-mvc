package com.social.twitter.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tweets")
public class Tweet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column
	private LocalDateTime date;

	@Column
	private String content;

	public Tweet() {
		this.date = LocalDateTime.now();
	}

	public long getId() {
		return id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public String getContent() {
		return content;
	}

	public void setId(long id) {

		this.id = id;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public void setContent(String content) {
		this.content = content;
	}
}