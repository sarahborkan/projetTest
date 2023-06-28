package com.inti.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.inti.repository.IClientRepository;

@WebMvcTest(controllers = ClientController.class)
public class ClientControllerTest {
	
	@Autowired
	private MockMvc mock;
	
	@MockBean
	private IClientRepository icr;
	
	@Test
	public void saveClient() throws Exception
	{
		mock.perform(get("/creerClient"))
		.andExpect(status().isOk())
//		.andExpect((ResultMatcher) containsStringIgnoringCase("Inscription"))
		.andDo(print());
	}

}
