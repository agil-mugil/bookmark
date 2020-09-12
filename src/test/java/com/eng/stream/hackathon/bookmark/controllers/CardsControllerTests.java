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

import com.eng.stream.hackathon.bookmark.models.Card;
import com.eng.stream.hackathon.bookmark.models.CardBean;
import com.eng.stream.hackathon.bookmark.services.CardService;
import com.eng.stream.hackathon.bookmark.utils.CommonUtils;

@SpringBootTest
@AutoConfigureMockMvc
public class CardsControllerTests {

	@MockBean
	private CardService cardService;
	
	@Autowired
    private MockMvc mockMvc;
	
	@Test
    @DisplayName("GET /cards success")
    void testGetAllBookmarkSuccess() throws Exception {
        // Setup our mocked service
		Card cardOne = new Card("https://www.baeldung.com/database-auditing-jpa","Spring",
				"image/spring.img", "http://localhost:8080/bookmark/baeldung",1L, "N" );
		
		Card cardTwo = new Card("https://gitter.im/engineering-stream-hackathon/community#","Hackathon",
				"image/spring.img", "http://localhost:8080/bookmark/engineering-stream-hackathon",1L, "Y");
		
 		List<Card> cards = new ArrayList<Card>();
 		cards.add(cardOne);
 		cards.add(cardTwo);
        doReturn(cards).when(cardService).findAllCards();

        // Execute the GET request
        mockMvc.perform(get("/api/v1/cards"))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Validate headers
                .andExpect(header().string(HttpHeaders.LOCATION, "/api/v1/cards"))

                // Validate the returned fields
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].bookmarkUrl", is("https://www.baeldung.com/database-auditing-jpa")))
                .andExpect(jsonPath("$[0].shortUrl", is("http://localhost:8080/bookmark/baeldung")))
                .andExpect(jsonPath("$[0].cardTitle", is("Spring")))
                .andExpect(jsonPath("$[0].publish", is("N")))
                .andExpect(jsonPath("$[1].bookmarkUrl", is("https://gitter.im/engineering-stream-hackathon/community#")))
                .andExpect(jsonPath("$[1].shortUrl", is("http://localhost:8080/bookmark/engineering-stream-hackathon")))
                .andExpect(jsonPath("$[1].cardTitle", is("Hackathon")))
                .andExpect(jsonPath("$[1].publish", is("Y")));
    }

 	@Test
    @DisplayName("POST /api/v1/cards/createCard")
    void testCreateBookmark() throws Exception {
        // Setup our mocked service
 		CardBean cardBean = new CardBean("https://www.baeldung.com/database-auditing-jpa","Spring",
				"image/spring.img", "http://localhost:8080/bookmark/baeldung",1L, "Y" );
		
		Card returnedCard = new Card("https://www.baeldung.com/database-auditing-jpa","Spring",
				"image/spring.img", "http://localhost:8080/bookmark/baeldung",1L, "Y");
		returnedCard.setCardId(1L);
        doReturn(returnedCard).when(cardService).createCard(any());

        // Execute the POST request
        mockMvc.perform(post("/api/v1/cards/createCard")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CommonUtils.asJsonString(cardBean)))
                // Validate the response code and content type
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Validate the returned fields
                .andExpect(jsonPath("$.cardId", is(1)))
                .andExpect(jsonPath("$.bookmarkUrl", is("https://www.baeldung.com/database-auditing-jpa")))
                .andExpect(jsonPath("$.shortUrl", is("http://localhost:8080/bookmark/baeldung")))
                .andExpect(jsonPath("$.cardTitle", is("Spring")))
                .andExpect(jsonPath("$.publish", is("Y")));
 	}
}
