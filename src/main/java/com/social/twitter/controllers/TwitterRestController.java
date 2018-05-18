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
		Collection<Tweet> tweets = tweetingService.getAllByHql();
		String responseGson = tweets.stream().map(tweet -> String.format("%s %s: %s <BR>\n", tweet.getDate(), tweet.getTweetUser().getLogin(), tweet.getContent())).collect(Collectors.joining());
		return new ResponseEntity<>(responseGson, HttpStatus.OK);
	}

	@RequestMapping(value = "/{login}", method = RequestMethod.GET)
	public ResponseEntity<?> getUserTweets(@PathVariable("login") String login) {
		Collection<Tweet> tweets = tweetingService.getAllByHql();
		String responseGson = tweets.stream()
				.filter(tweet -> tweet.getTweetUser().getLogin().equals(login))
				.map(tweet -> String.format("%s %s: %s <BR>\n", tweet.getDate(), tweet.getTweetUser().getLogin(), tweet.getContent())).collect(Collectors.joining());
		return new ResponseEntity<>(responseGson, HttpStatus.OK);
	}


	/*@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("id") String id) {
		User user = userService.findById(id);
		if (user == null)
	         return new ResponseEntity(HttpStatus.NOT_FOUND);
	    return new ResponseEntity<User>(user, HttpStatus.OK);
	}*/
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user) {

		userService.create(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
		User user = userService.findById(id);
		if (user == null) {
	         return new ResponseEntity(HttpStatus.NOT_FOUND);
	    }
		userService.delete(user);
	    return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/{login}", method = RequestMethod.POST)
	public ResponseEntity<?> registerTweet(@PathVariable("login") String login, @RequestBody Tweet tweet) {
		//User user = userService.getAllByHql().stream().filter(u -> u.getLogin().equals(login)).findFirst().orElse(null);
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