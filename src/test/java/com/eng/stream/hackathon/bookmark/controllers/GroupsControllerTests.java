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
import com.eng.stream.hackathon.bookmark.utils.CommonUtils;

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
	 		Group snoGroup = new Group("FT", "SNO");
	 		Group enoGroup = new Group("FT", "ENO");
	 		List<Group> allGroups = new ArrayList<Group>();
	 		allGroups.add(snoGroup);
	 		allGroups.add(enoGroup);
	        doReturn(allGroups).when(groupService).findAllGroups();

	        // Execute the GET request
	        mockMvc.perform(get("/api/v1/groups").
	        		header("username", "prabhu.murugesan@gmail.com"))
	        		
	                // Validate the response code and content type
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                // Validate headers
	                .andExpect(header().string(HttpHeaders.LOCATION, "/api/v1/groups"))

	                // Validate the returned fields
	                .andExpect(jsonPath("$", hasSize(2)))
	                .andExpect(jsonPath("$[0].groupType", is("FT")))
	                .andExpect(jsonPath("$[0].groupValue", is("SNO")))
	                .andExpect(jsonPath("$[1].groupType", is("FT")))
	                .andExpect(jsonPath("$[1].groupValue", is("ENO")));
	    }

	 	@Test
	    @DisplayName("POST /api/v1/groups/createGroup")
	    void testCreateGroup() throws Exception {
	        // Setup our mocked service
	 		GroupBean snoGroup = new GroupBean("FT", "SNO");
	        Group snoGroupToRetuns = new Group("FT", "SNO");
	        snoGroupToRetuns.setGroupId(10l);
	        doReturn(snoGroupToRetuns).when(groupService).createGroup(any());

	        // Execute the POST request
	        mockMvc.perform(post("/api/v1/groups/createGroup")
	                .contentType(MediaType.APPLICATION_JSON)
	                .header("username", "prabhu.murugesan@gmail.com")
	                .content(CommonUtils.asJsonString(snoGroup)))
	                // Validate the response code and content type
	                .andExpect(status().isCreated())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                // Validate the returned fields
	                .andExpect(jsonPath("$.groupId", is(10)))
	                . andExpect(jsonPath("$.groupType", is("FT")))
	                .andExpect(jsonPath("$.groupValue", is("SNO")));
	    }
	 	
}
