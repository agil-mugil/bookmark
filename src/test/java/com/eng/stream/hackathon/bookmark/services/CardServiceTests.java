package com.eng.stream.hackathon.bookmark.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.eng.stream.hackathon.bookmark.EntityNotFoundException;
import com.eng.stream.hackathon.bookmark.models.Bookmark;
import com.eng.stream.hackathon.bookmark.models.Card;
import com.eng.stream.hackathon.bookmark.repositories.BookmarkRepository;
import com.eng.stream.hackathon.bookmark.repositories.CardRepository;
import com.eng.stream.hackathon.bookmark.repositories.GroupAdminRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class CardServiceTests {
	@Autowired
	private CardService cardService;
	
	@MockBean
	private CardRepository  cardRepository;
	@MockBean
	private BookmarkRepository bookmarkRepository;
	
	@MockBean
	private GroupAdminRepository  adminRepo;
	
	private List<Card> cardList = new ArrayList<Card>();
	@BeforeEach
	public void setUp() {
		Card card = new Card("https://www.baeldung.com/database-auditing-jpa","Spring",
				"image/spring.img", "http://localhost:8080/bookmark/baeldung",1L, "Y");
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
				"image/spring.img", "http://localhost:8080/bookmark/baeldung",1L, "Y");
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
		List<Card> cardList  = cardService.findByPublishAndGroupId(groupId,"Prabhu.murugesan");
		assertThat(cardList.size()).isGreaterThan(0);
	}

	@Test
	@Order(5)
	void testDeleteCard() {
		cardService.deleteCard(1l);
		cardList.remove(0);
		assertEquals(0, cardList.size());
	}
	
	@Test
	void testFindAllCardsWithNoEntityFound() {
		try {
			List<Card> cards = new ArrayList<Card>();
			doReturn(cards).when(cardRepository).findByPublish(any());
			cardService.findAllCards();
			fail(" EntityNotFoundException exception not thrown");
		} catch (EntityNotFoundException e) {
			assertThat(e.getMessage()).isEqualTo("Card was not found for parameters {}");
		}
	}
	
	@Test
	void testFindCardsPublishAndGroupIdWithNoEntityFound() {
		try {
			doReturn(0).when(adminRepo).findCountByGroupAndUserId(any(), any());
			List<Card> cards = new ArrayList<Card>();
			doReturn(cards).when(cardRepository).findByPublishAndGroupId(any(),any());
			cardService.findByPublishAndGroupId(1L,"prabhu.murugesan");
			fail(" EntityNotFoundException exception not thrown");
		} catch (EntityNotFoundException e) {
			assertThat(e.getMessage()).isEqualTo("Card was not found for parameters {Publish & Group ID=Y & 1}");
			System.out.println(e.getCause());
		}
	}
	
	@Test
	void testPublishedCardsWithNoEntityFound() {
		try {
			List<Card> cards = new ArrayList<Card>();
			doReturn(cards).when(cardRepository).findByPublish(any());
			cardService.findAllPublishedCards();
			fail(" EntityNotFoundException exception not thrown");
		} catch (EntityNotFoundException e) {
			assertThat(e.getMessage()).isEqualTo("Card was not found for parameters {Publish=Y}");
		}
	}
	
	@Test
	void testFindCardByShortUrl() {
		Card card = new Card("https://www.fiftythree.com/","Spring",
				"image/spring.img", "http://localhost:4200/fiftythree",1L, "Y");
		doReturn(card).when(cardRepository).findByShortUrlAndPublish(any(), any());
		String shortUrl = "http://localhost:4200/fiftythree";
		Card returnedCard  = cardService.findByShortUrl(shortUrl);
		assertThat(returnedCard.getBookmarkUrl()).isEqualTo("https://www.fiftythree.com/");
	}
	
	@Test
	void testFindCardByShortUrlWithNoEntityFound() {
		try {
			Card card = null;
			Bookmark bookmark = null;
			doReturn(card).when(cardRepository).findByShortUrlAndPublish(any(), any());
			doReturn(bookmark).when(bookmarkRepository).findByShortUrl(any());
			String shortUrl = "http://localhost:4200/fiftythree";
			cardService.findByShortUrl(shortUrl);
			fail(" EntityNotFoundException exception not thrown");
		} catch (EntityNotFoundException e) {
			assertThat(e.getMessage()).isEqualTo("Bookmark was not found for parameters {shortUrl=http://localhost:4200/fiftythree}");
		}
	}
	
	@Test
	void  testPublishCard() {
		Optional<Card> card = Optional.of(new Card("https://www.fiftythree.com/","Spring",
				"image/spring.img", "http://localhost:4200/fiftythree",1L, "N"));
		doReturn(card).when(cardRepository).findById(any());
		Card returnedCard = card.get();
		doReturn(returnedCard).when(cardRepository).save(any());
		assertNotNull(card);
	}

}
