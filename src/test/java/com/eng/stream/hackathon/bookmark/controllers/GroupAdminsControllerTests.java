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

import com.eng.stream.hackathon.bookmark.models.GroupAdmin;
import com.eng.stream.hackathon.bookmark.services.GroupAdminService;
import com.eng.stream.hackathon.bookmark.utils.CommonUtils;

@SpringBootTest
@AutoConfigureMockMvc
public class GroupAdminsControllerTests {

	@MockBean
	private GroupAdminService groupAdminService;
	
	@Autowired
    private MockMvc mockMvc;
	
	@Test
    @DisplayName("GET /Get Group Admins")
    void testGetAllGroupAdminsSuccess() throws Exception {
        // Setup our mocked service
 		GroupAdmin adminOne = new GroupAdmin( "pmurugesan2012@gmail.com","pmurugesan2012@gmail.com",new Date(System.currentTimeMillis()));
 		GroupAdmin adminTwo =  new GroupAdmin( "pmurugesan@gmail.com","pmurugesan2012@gmail.com",new Date(System.currentTimeMillis()));
 		List<GroupAdmin> groupAdmis = new ArrayList<GroupAdmin>();
 		groupAdmis.add(adminOne);
 		groupAdmis.add(adminTwo);
 		Long groupId = 1L;
        doReturn(groupAdmis).when(groupAdminService).getGroupAdmins(groupId);

        // Execute the GET request
        mockMvc.perform(get("/api/v1/groupAdmins?groupId="+groupId))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Validate headers
                .andExpect(header().string(HttpHeaders.LOCATION, "/api/v1/groupAdmins"))

                // Validate the returned fields
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].userId", is("pmurugesan2012@gmail.com")))
                .andExpect(jsonPath("$[0].creator", is("pmurugesan2012@gmail.com")))
                .andExpect(jsonPath("$[1].userId", is("pmurugesan@gmail.com")))
                .andExpect(jsonPath("$[1].creator", is("pmurugesan2012@gmail.com")));
    }

 	@Test
    @DisplayName("POST /api/v1/groupAdmins/createGroupAdmin")
    void testCreateGroup() throws Exception {
        // Setup our mocked service
 		GroupAdmin inputGroupAdmin = new GroupAdmin( "pmurugesan2012@gmail.com","pmurugesan2012@gmail.com",new Date(System.currentTimeMillis()));
 		GroupAdmin returnedGroupAdmin =  new GroupAdmin( "pmurugesan2012@gmail.com","pmurugesan2012@gmail.com",new Date(System.currentTimeMillis()));
 		returnedGroupAdmin.setGroupUserId(1L);
        doReturn(returnedGroupAdmin).when(groupAdminService).addGroupAdmin(any());

        // Execute the POST request
        mockMvc.perform(post("/api/v1/groupAdmins/createGroupAdmin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CommonUtils.asJsonString(inputGroupAdmin)))
                // Validate the response code and content type
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Validate the returned fields
                .andExpect(jsonPath("$.groupUserId", is(1)))
                .andExpect(jsonPath("$.userId", is("pmurugesan2012@gmail.com")))
                .andExpect(jsonPath("$.creator", is("pmurugesan2012@gmail.com")));
    }
}
