package com.social.twitter.controllers;

import com.social.twitter.model.Follow;
import com.social.twitter.model.Tweet;
import com.social.twitter.model.User;
import com.social.twitter.persistence.FollowService;
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

	@Autowired
	FollowService followService;

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

	@RequestMapping(value = "/{login}/{followLogin}", method = RequestMethod.GET)
	public ResponseEntity<?> registerFollow(@PathVariable("login") String login, @PathVariable("followLogin") String followLogin) {
		User user = userService.findByLogin(login);
		User followUser = userService.findByLogin(followLogin);
		if (user == null || followUser == null) return new ResponseEntity<Object>(String.format("%s or %s does not exist", login, followLogin), HttpStatus.NOT_FOUND);
		Follow follow = new Follow();
		follow.setUser(user);
		follow.setFollowing(followUser);
		// check if not following already
		boolean isAlreadyFollowed = followService.getAll().stream().anyMatch(f -> f.getUser().getLogin().equals(login) && f.getFollowing().getLogin().equals(followLogin));
		if (!isAlreadyFollowed) {
			followService.create(follow);
			return new ResponseEntity<>(String.format("User %s is now following %s", login, followLogin), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(String.format("User %s is already followed by %s", followLogin, login), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/{login}/timeline", method = RequestMethod.GET)
	public ResponseEntity<?> getUserTimeline(@PathVariable("login") String login) {
		User user = userService.findByLogin(login);
		List<User> followedUsers = followService.getAll().stream()
				.filter(follow -> follow.getUser().getLogin().equals(login))
				.map(u -> u.getFollowing())
				.collect(Collectors.toList());
		List<Tweet> timelineTweets = new ArrayList<>();
		for (Tweet tweet : tweetingService.getAll()) {
			if (followedUsers.contains(tweet.getTweetUser())) {
				timelineTweets.add(tweet);
			}
		}
		return new ResponseEntity<>(timelineTweets, HttpStatus.OK);
	}
}