package com.social.twitter.controllers;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.social.twitter.model.User;
import com.social.twitter.persistence.TweetingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.social.twitter.persistence.UserService;

import java.util.Collection;

@RunWith(SpringRunner.class)
@WebMvcTest(TwitterRestController.class)
public class TwitterRestControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private TweetingService tweetingService;

	@MockBean
	private UserService userService;

	/*@Test
	public void testGetUsers() throws Exception {
		// Given
		User user = new User();
		user.setLogin("testLogin");

		Collection<User> allUsers = singletonList(user);

		// When
		given(userService.getAll()).willReturn(allUsers);

		// Then
		mvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].login", is(user.getLogin())));
	}
*/
	/*@Test
	public void testGetParticipant() throws Exception {
		// Given
		Participant participant = new Participant();
		participant.setLogin("testLogin");
		participant.setPassword("testPassword");

		// When
		given(participantService.findByLogin(participant.getLogin())).willReturn(participant);

		// Then
		mvc.perform(get("/participants/testLogin").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.login", is(participant.getLogin()))).andExpect(jsonPath("$.password", is(participant.getPassword())));
	}

	@Test
	public void testRegisterParticipant() throws Exception {
		// Given
		Participant participant = new Participant();
		participant.setLogin("testLogin");
		participant.setPassword("testPassword");

		Gson gson = new Gson();
		String jsonParticipant = gson.toJson(participant);

		// When
		given(participantService.findByLogin(participant.getLogin())).willReturn(null);

		// Then
		mvc.perform(post("/participants").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(jsonParticipant)).andExpect(status().isCreated());
	}

	@Test
	public void testDeleteParticipant() throws Exception {
		// Given
		Participant participant = new Participant();
		participant.setLogin("testLogin");
		participant.setPassword("testPassword");

		// When
		given(participantService.findByLogin(participant.getLogin())).willReturn(participant);

		// Then
		mvc.perform(delete("/participants/testLogin").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testUpdateParticipant() throws Exception {
		// Given
		Participant participant = new Participant();
		participant.setLogin("testLogin");
		participant.setPassword("testPassword");

		Participant participantUpdate = new Participant();
		participantUpdate.setLogin(participant.getLogin());
		participantUpdate.setPassword("newPassword");

		Gson gson = new Gson();
		String jsonParticipantUpdate = gson.toJson(participantUpdate);

		// When
		given(participantService.findByLogin(participant.getLogin())).willReturn(participant);

		// Then
		mvc.perform(put("/participants").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(jsonParticipantUpdate))
				.andExpect(jsonPath("$.login", is(participant.getLogin()))).andExpect(jsonPath("$.password", is(participantUpdate.getPassword()))).andExpect(status().isAccepted());

	}*/
}