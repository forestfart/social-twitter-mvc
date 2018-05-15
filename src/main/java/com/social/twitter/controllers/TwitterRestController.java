package com.social.twitter.controllers;

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
		Collection<Tweet> tweets = tweetingService.getAll();
		return new ResponseEntity<Collection<Tweet>>(tweets, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("id") String login) {
		User user = userService.findByLogin(login);
		if (user == null) {
	         return new ResponseEntity(HttpStatus.NOT_FOUND);
	    }
	    return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		if (userService.findByLogin(user.getLogin()) != null) {
			return new ResponseEntity(
				"Unable to create. An user with login " + user.getLogin() + " already exists.",
				HttpStatus.CONFLICT);
			}
		userService.create(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") String login) {
		User user = userService.findByLogin(login);
		if (user == null) {
	         return new ResponseEntity(HttpStatus.NOT_FOUND);
	    }
		userService.delete(user);
	    return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/tweet", method = RequestMethod.POST)
	public ResponseEntity<?> registerTweet(@RequestBody Tweet tweet) {

		tweetingService.create(tweet);
		return new ResponseEntity<Tweet>(tweet, HttpStatus.CREATED);
	}
}
