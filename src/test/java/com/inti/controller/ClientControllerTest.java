package com.inti.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.inti.model.Client;
import com.inti.repository.IClientRepository;

@WebMvcTest(controllers = ClientController.class)
public class ClientControllerTest {
	
	@Autowired
	private MockMvc mock;
	
//	@Autowired
//	private IClientRepository icr1;
	
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
	
//	@Test
//	public void modifierClient() throws Exception
//	{
////		System.out.println("client : " + icr1.save(new Client(1, "test", "test", "test@test.fr")));
//		
//		mock.perform(get("/modifierClient/1"))
//		.andExpect(status().isOk())
//		.andDo(print());
//	}	
	
	@Test
	public void listeClient() throws Exception
	{
		mock.perform(get("/listeClient"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void deleteClient() throws Exception
	{
		mock.perform(get("/deleteClient/1"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listeClient"))
		.andDo(print());
	}
	
	@Test
	public void saveClientPost() throws Exception
	{
		mock.perform(post("/creerClient").sessionAttr("client", new Client("dupont", "louis", "test@test.fr")))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listeClient"))
		.andDo(print());
	}
	
	@Test
	public void updateClientPost() throws Exception
	{
		mock.perform(post("/modifierClient/1").sessionAttr("client", new Client("dupont", "louis", "test@test.fr")))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listeClient"))
		.andDo(print());
	}

}
