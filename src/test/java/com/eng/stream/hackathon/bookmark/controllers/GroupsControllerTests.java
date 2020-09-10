package com.eng.stream.hackathon.bookmark.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.eng.stream.hackathon.bookmark.models.Group;
import com.eng.stream.hackathon.bookmark.models.GroupBean;
import com.eng.stream.hackathon.bookmark.services.GroupService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class GroupsControllerTests {

	@MockBean
	private GroupService groupService;
	
	@Autowired
    private MockMvc mockMvc;
	
	 	@Test
	    @DisplayName("GET /groups success")
	    void testGetAllGroupsSuccess() throws Exception {
	        // Setup our mocked service
	 		Group snoGroup = new Group("FT", "SNO", "pmurugesan2012@gmail.com",new Date(System.currentTimeMillis()));
	 		Group enoGroup = new Group("FT", "ENO", "harish.mohan@gmail.com",new Date(System.currentTimeMillis()));
	 		List<Group> allGroups = new ArrayList<Group>();
	 		allGroups.add(snoGroup);
	 		allGroups.add(enoGroup);
	        doReturn(allGroups).when(groupService).findAllGroups();

	        // Execute the GET request
	        mockMvc.perform(get("/api/v1/groups"))
	                // Validate the response code and content type
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                // Validate headers
	                .andExpect(header().string(HttpHeaders.LOCATION, "/api/v1/groups"))

	                // Validate the returned fields
	                .andExpect(jsonPath("$", hasSize(2)))
	                .andExpect(jsonPath("$[0].groupType", is("FT")))
	                .andExpect(jsonPath("$[0].groupValue", is("SNO")))
	                .andExpect(jsonPath("$[0].creator", is("pmurugesan2012@gmail.com")))
	                .andExpect(jsonPath("$[1].groupType", is("FT")))
	                .andExpect(jsonPath("$[1].groupValue", is("ENO")))
	                .andExpect(jsonPath("$[1].creator", is("harish.mohan@gmail.com")));
	    }

	 	@Test
	    @DisplayName("POST /api/v1/groups/createGroup")
	    void testCreateGroup() throws Exception {
	        // Setup our mocked service
	 		GroupBean snoGroup = new GroupBean("FT", "SNO", "pmurugesan2012@gmail.com");
	        Group snoGroupToRetuns = new Group("FT", "SNO", "pmurugesan2012@gmail.com",new Date(System.currentTimeMillis()));
	        snoGroupToRetuns.setGroupId(10l);
	        doReturn(snoGroupToRetuns).when(groupService).createGroup(any());

	        // Execute the POST request
	        mockMvc.perform(post("/api/v1/groups/createGroup")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(snoGroup)))
	                // Validate the response code and content type
	                .andExpect(status().isCreated())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                // Validate the returned fields
	                .andExpect(jsonPath("$.groupId", is(10)))
	                . andExpect(jsonPath("$.groupType", is("FT")))
	                .andExpect(jsonPath("$.groupValue", is("SNO")))
	                .andExpect(jsonPath("$.creator", is("pmurugesan2012@gmail.com")));
	    }
	 	
	 	static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (IOException e) {
	        	return "{'Error': 'error in converting to JSON object'}";
	        }
	    }
}
