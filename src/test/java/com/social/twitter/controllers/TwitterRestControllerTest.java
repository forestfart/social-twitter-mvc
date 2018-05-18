package com.social.twitter.controllers;

import com.social.twitter.persistence.TweetingService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.social.twitter.persistence.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(TwitterRestController.class)
public class TwitterRestControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private TweetingService tweetingService;

	@MockBean
	private UserService userService;

}