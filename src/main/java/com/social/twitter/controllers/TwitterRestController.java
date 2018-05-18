package com.social.twitter.controllers;

import com.google.gson.Gson;
import com.social.twitter.model.Tweet;
import com.social.twitter.model.User;
import com.social.twitter.persistence.TweetingService;
import com.social.twitter.persistence.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/twitter")
public class TwitterRestController {

	@Autowired
	UserService userService;

	@Autowired
	TweetingService tweetingService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> getAllTweets() {
		Collection<Tweet> tweets = tweetingService.getAll();
		return new ResponseEntity<>(tweets, HttpStatus.OK);
	}

	@RequestMapping(value = "/{login}", method = RequestMethod.GET)
	public ResponseEntity<?> getUserTweets(@PathVariable("login") String login) {
		if (userService.findByLogin(login) == null) return new ResponseEntity<Object>(String.format("User << %s >> does not exist", login), HttpStatus.NOT_FOUND);
		List<Tweet> filteredTweets = tweetingService.getAll().stream()
				.filter(tweet -> tweet.getTweetUser().getLogin().toString().equals(login))
				.collect(Collectors.toList());
		if (filteredTweets.size() == 0) return new ResponseEntity<Object>(String.format("User << %s >> does not have any tweets", login), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(filteredTweets, HttpStatus.OK);
	}

	@RequestMapping(value = "/{login}", method = RequestMethod.POST)
	public ResponseEntity<?> registerTweet(@PathVariable("login") String login, @RequestBody Tweet tweet) {
		User user = userService.findByLogin(login);
		if (user == null) {
			user = new User();
			user.setLogin(login);
			userService.create(user);
		}
		tweet.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		tweet.setTweetUser(user);
		tweetingService.create(tweet);
		return new ResponseEntity<>(tweet, HttpStatus.CREATED);
	}

}