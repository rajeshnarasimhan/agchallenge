package com.autogeneral.agchallenge.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.autogeneral.agchallenge.AgchallengeApplication;
import com.autogeneral.agchallenge.rest.error.RestExceptionTranslator;

/**
 * Test class for TasksWS
 * @author rajesh
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AgchallengeApplication.class)
public class TaskWSIntTest {
	
	private MockMvc restMockMvc;
	
	@Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TasksWS taskWs = new TasksWS();
        this.restMockMvc = MockMvcBuilders.standaloneSetup(taskWs)
        		.setControllerAdvice(new RestExceptionTranslator())
        		.build();
    }
	
	@Test
    public void validateBrackets1() throws Exception {
		//initialize data
		String input = "{[()]}";
		
		restMockMvc.perform(get("/tasks/validateBrackets").param("input", input))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	        .andExpect(jsonPath("$.input").value(input))
	        .andExpect(jsonPath("$.isBalanced").value(true));
    }
	
	@Test
    public void validateBrackets2() throws Exception {
		//initialize data
		String input = "{[()}";
		
		restMockMvc.perform(get("/tasks/validateBrackets").param("input", input))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	        .andExpect(jsonPath("$.input").value(input))
	        .andExpect(jsonPath("$.isBalanced").value(false));
    }
	
	@Test
    public void validateBracketsFail() throws Exception {
		
		restMockMvc.perform(get("/tasks/validateBrackets"))
	        .andExpect(status().isNotFound())
	        .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON));
    }
}