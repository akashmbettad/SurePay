package com.java.surepay;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.surepay.Entity.Record;
import com.java.surepay.controller.RecordController;


/*
 * In progress - Integration Testing
 */

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecordControllerIntegrationTest {


	@Autowired
	RecordController Controller;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;


	@Test
	public void whenPostRequestToUsersAndValidRecord_thenCorrectResponse() throws Exception {

		Record record=new Record(170148L,"NL43AEGO0773393871","Flowers Morley Koos",16.52,43.09,59.61);
		ResultActions response=mockMvc.perform(MockMvcRequestBuilders.post("/createTransaction")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(record)));

				response.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	public void whenPostRequestToUsersAndInValidUser_thenCorrectResponse() throws Exception {
		Record record=new Record(170148L,"NL43AEGO0773393871","Flowers Morley Koos",16.52,43.09,59.61);
		
		ResultActions response=mockMvc.perform(MockMvcRequestBuilders.post("/createTransaction")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(record)));
		
		response.andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
		.andExpect(MockMvcResultMatchers.jsonPath("$.message", Is.is("Error with transaction")));

	}


}
