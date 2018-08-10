package com.example.demo;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloSpringBootApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	MessageService msgSvc;
	
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testRestMessages() throws Exception {
		
		Message m1 = new Message("test msg 1", new Person(), new Person(), LocalDateTime.now());
		m1.id =1;
		
		Message m2 = new Message("test msg 2", new Person(), new Person(), LocalDateTime.now());
		m2.id =2;
		
		when(msgSvc.getMessagesInQueue()).thenReturn(new Message[] {m1,m2});
		
		mockMvc.perform(get("/hello").accept(MediaType.APPLICATION_JSON_UTF8))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.message", containsString("test msg")));
	}
	
}
