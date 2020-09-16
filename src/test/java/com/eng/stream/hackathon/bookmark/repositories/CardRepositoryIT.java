package com.eng.stream.hackathon.bookmark.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.eng.stream.hackathon.bookmark.models.Card;
import com.eng.stream.hackathon.bookmark.models.Group;
import com.eng.stream.hackathon.bookmark.models.GroupAdmin;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CardRepositoryIT {

	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private CardRepository cardRepository;
	
	public Group createGroup() {
		Group group = new Group("FT", "SNO");
		group.setCreatedBy("prabhu.murugesan");
		List<GroupAdmin> groupAdmins = new ArrayList<GroupAdmin>();
		GroupAdmin groupAdmin = new GroupAdmin();
		groupAdmin.setUserId("prabhu.murugesan");
		groupAdmin.setCreatedBy("prabhu.murugesan");
		groupAdmin.setGroup(group);
		groupAdmins.add(groupAdmin);
		group.setGroupAdmins(groupAdmins);
		Group saved =  groupRepository.save(group);
		return saved;
	}
	
	@Test
	@Rollback(false)
	@Order(1)
	public void testCreateCard() {
		Group group = createGroup();
		Card card = new Card("https://www.baeldung.com/database-auditing-jpa","Spring",
				"image/spring.img", "http://localhost:8080/bookmark/baeldung",group.getGroupId(), "N");
		card.setCreatedBy("prabhu.murugesan");
		Card saved =cardRepository.saveAndFlush(card);
		assertNotNull(saved);
		
		Card cardTwo = new Card("https://www.fiftythree.com/","Spring",
				"image/spring.img", "http://localhost:4200/fiftythree",group.getGroupId(), "Y");
		cardTwo.setCreatedBy("prabhu.murugesan");
		saved =cardRepository.saveAndFlush(cardTwo);
		assertNotNull(saved);
	}
	
	@Test
	@Order(2)
	public void testAllCards() {
		List<Card> cards = cardRepository.findAll();
		assertEquals(2, cards.size());
	}
	
	@Test
	@Order(3)
	@Rollback(false)
	public void publishCard() {
		List<Card> cards = cardRepository.findAll();
		Card card = cards.get(0);
		card.setPublish("Y");
		Card saved = cardRepository.saveAndFlush(card);
		assertNotNull(saved);
		assertEquals("Y",saved.getPublish());
	}
	
	@Test
	@Order(4)
	public void testAllPublishedCards() {
		List<Card> cards = cardRepository.findByPublish("Y");
		assertEquals(2, cards.size());
	}
	
	@Test
	@Order(5)
	public void testAllPublishedCardsByGroup() {
		Long groupId = 1L;
		List<Card> cards = cardRepository.findByPublishAndGroupId("Y",groupId );
		assertEquals(2,cards.size());
		 assertThat(cards).extracting(Card::getPublish).contains("Y");
	}
	
	@Test
	@Order(6)
	public void testFindByShortUrlAndPublish() {
		String shortUrl = "http://localhost:4200/fiftythree";
		Card card = cardRepository.findByShortUrlAndPublish(shortUrl, "Y");
		assertNotNull(card);
		 assertThat(card.getBookmarkUrl()).isEqualTo("https://www.fiftythree.com/");
	}
	
	@Test
	@Rollback(false)
	@Order(7)
	public void testDeleteCard() {
		List<Card> cardList = cardRepository.findAll();
		Optional<Card> existingCards = cardList.stream().findFirst();
		Long cardId = existingCards.isPresent()? existingCards.get().getCardId():0;
		boolean dataPresentBeforeDelete=  cardRepository.findById(cardId).isPresent();
		cardRepository.deleteById(cardId);
		boolean dataNotPresentAfterDelete = cardRepository.findById(cardId).isPresent();
		assertTrue(dataPresentBeforeDelete);
		assertFalse(dataNotPresentAfterDelete);
	}
}
