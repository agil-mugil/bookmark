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

import com.eng.stream.hackathon.bookmark.models.Bookmark;
import com.eng.stream.hackathon.bookmark.models.BookmarkBean;
import com.eng.stream.hackathon.bookmark.services.BookmarkService;
import com.eng.stream.hackathon.bookmark.utils.CommonUtils;

@SpringBootTest
@AutoConfigureMockMvc
public class BookmarksControllerTests {
	
	@MockBean
	private BookmarkService bookmarkService;
	
	@Autowired
    private MockMvc mockMvc;
	
	@Test
    @DisplayName("GET /bookmark success")
    void testGetAllBookmarkSuccess() throws Exception {
        // Setup our mocked service
		Bookmark bookmark1 = new Bookmark("https://www.baeldung.com/database-auditing-jpa", "http://localhost:8080/bookmark/baeldung", 
				new Date(System.currentTimeMillis()+10));
		Bookmark bookmark2 = new Bookmark("https://rashidi.github.io/spring-boot-data-audit/", "http://localhost:8080/bookmark/rashidi", 
				new Date(System.currentTimeMillis()+10));
 		List<Bookmark> allBookmarks = new ArrayList<Bookmark>();
 		allBookmarks.add(bookmark1);
 		allBookmarks.add(bookmark2);
        doReturn(allBookmarks).when(bookmarkService).getAllBookmarks();

        // Execute the GET request
        mockMvc.perform(get("/api/v1/bookmarks"))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Validate headers
                .andExpect(header().string(HttpHeaders.LOCATION, "/api/v1/bookmarks"))

                // Validate the returned fields
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].bookmarkUrl", is("https://www.baeldung.com/database-auditing-jpa")))
                .andExpect(jsonPath("$[0].shortUrl", is("http://localhost:8080/bookmark/baeldung")))
                .andExpect(jsonPath("$[1].bookmarkUrl", is("https://rashidi.github.io/spring-boot-data-audit/")))
                .andExpect(jsonPath("$[1].shortUrl", is("http://localhost:8080/bookmark/rashidi")));
    }

 	@Test
    @DisplayName("POST /api/v1/bookmarks/createBookmark")
    void testCreateBookmark() throws Exception {
        // Setup our mocked service
 		BookmarkBean bookmarkBean = new BookmarkBean("https://www.baeldung.com/database-auditing-jpa", "http://localhost:8080/bookmark/baeldung", 
				new Date(System.currentTimeMillis()+10));
 		Bookmark bookmarkToReturns =  new Bookmark("https://www.baeldung.com/database-auditing-jpa", "http://localhost:8080/bookmark/baeldung", 
				new Date(System.currentTimeMillis()+10));
 		bookmarkToReturns.setBookmarkId(2L);
        doReturn(bookmarkToReturns).when(bookmarkService).createBookmark(any());

        // Execute the POST request
        mockMvc.perform(post("/api/v1/bookmarks/createBookmark")
                .contentType(MediaType.APPLICATION_JSON)
                .header("username", "prabhu.murugesan@gmail.com")
                .content(CommonUtils.asJsonString(bookmarkBean)))
                // Validate the response code and content type
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Validate the returned fields
                .andExpect(jsonPath("$.bookmarkId", is(2)))
                . andExpect(jsonPath("$.bookmarkUrl", is("https://www.baeldung.com/database-auditing-jpa")))
                .andExpect(jsonPath("$.shortUrl", is("http://localhost:8080/bookmark/baeldung")));
    }
 	
}
