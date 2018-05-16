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
import java.util.Collection;


@RestController
@RequestMapping("/twitter")
public class TwitterRestController {

	@Autowired
	UserService userService;

	@Autowired
	TweetingService tweetingService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> getTweets() {
		Collection<Tweet> tweets = tweetingService.getAllByHql();
		return new ResponseEntity<>(tweets, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("id") String id) {
		User user = userService.findById(id);
		if (user == null) {
	         return new ResponseEntity(HttpStatus.NOT_FOUND);
	    }
	    return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user) {

		userService.create(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
		User user = userService.findById(id);
		if (user == null) {
	         return new ResponseEntity(HttpStatus.NOT_FOUND);
	    }
		userService.delete(user);
	    return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/{login}", method = RequestMethod.POST)
	public ResponseEntity<?> registerTweet(@PathVariable("login") String login, @RequestBody Tweet jsonTweet) {
		User user = userService.getAllByHql().stream().filter(u -> u.getLogin().equals(login)).findFirst().orElse(null);
		if (user == null) {
			user = new User();
			user.setLogin(login);
			userService.create(user);
		}
		Tweet tweet = new Tweet(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), user, jsonTweet.getContent());
		tweetingService.create(tweet);
		return new ResponseEntity<>(String.format("tweet: %s %s created on %s", tweet.getTweetUser().getLogin(), tweet.getContent(), tweet.getDate()), HttpStatus.CREATED);
	}
}
