package com.eng.stream.hackathon.bookmark.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.eng.stream.hackathon.bookmark.models.Card;
import com.eng.stream.hackathon.bookmark.repositories.CardRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class CardServiceTests {
	@Autowired
	private CardService cardService;
	
	@MockBean
	private CardRepository  cardRepository;
	
	private List<Card> cardList = new ArrayList<Card>();
	@BeforeEach
	public void setUp() {
		Card card = new Card("https://www.baeldung.com/database-auditing-jpa","Spring",
				"image/spring.img", "http://localhost:8080/bookmark/baeldung",1L, "Y","pmurugesan2012@gmail.com" );
		cardList.add(card);
	    Mockito.when(cardRepository.findByPublish(any())).thenReturn(cardList);
	    Mockito.when(cardRepository.findByPublishAndGroupId(any(),any())).thenReturn(cardList);
	    Mockito.when(cardRepository.findAll()).thenReturn(cardList);
	    Mockito.when(cardRepository.findByGroupId(any())).thenReturn(cardList);
	}
	
	@Test
	@Order(1)
	void testCreateCard() {
		Card card = new Card("https://www.baeldung.com/database-auditing-jpa","Spring",
				"image/spring.img", "http://localhost:8080/bookmark/baeldung",1L, "Y","pmurugesan2012@gmail.com" );
	    doReturn(card).when(cardRepository).saveAndFlush(any());
		Card created = cardService.createCard(card);
		assertNotNull(created);
	}

	@Test
	@Order(2)
	void testFindAllCards() {
		List<Card> cardList = cardService.findAllCards();
		assertThat(cardList.size()).isGreaterThan(0);
	}

	@Test
	@Order(3)
	void testFindAllPublishedCards() {
		List<Card> cardList  = cardService.findAllPublishedCards();
		assertThat(cardList.size()).isGreaterThan(0);
	}
	
	@Test
	@Order(4)
	void testFindAllGroupPublishedCards() {
		Long groupId = 1L;
		List<Card> cardList  = cardService.findByPublishAndGroupId(groupId);
		assertThat(cardList.size()).isGreaterThan(0);
	}

	@Test
	@Order(5)
	void testDeleteCard() {
		cardService.deleteCard(1l);
		cardList.remove(0);
		assertEquals(0, cardList.size());
	}

}
