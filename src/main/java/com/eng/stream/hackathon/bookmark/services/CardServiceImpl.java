package com.eng.stream.hackathon.bookmark.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eng.stream.hackathon.bookmark.EntityNotFoundException;
import com.eng.stream.hackathon.bookmark.models.Card;
import com.eng.stream.hackathon.bookmark.repositories.CardRepository;
import com.eng.stream.hackathon.bookmark.repositories.GroupAdminRepository;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private GroupAdminRepository groupAdminRepository;
	
	@Override
	public Card createCard(Card card) {
		String publish = "Y";
		if(groupAdminRepository.findCountByGroupAndUserId(card.getGroupId(), card.getCreatedBy())==0){
			publish = "N";
		}
		card.setPublish(publish);
		return cardRepository.saveAndFlush(card);
	}

	@Override
	public List<Card> findAllCards() {
		List<Card> cards = cardRepository.findByPublish("Y");
		if(cards.isEmpty()) {
			throw new EntityNotFoundException(Card.class);
		}
		return cards;
	}

	@Override
	public List<Card> findAllPublishedCards() {
		List<Card> cards = cardRepository.findByPublish("Y");
		if(cards.isEmpty()) {
			throw new EntityNotFoundException(Card.class,"Publish", "Y");
		}
		return cards;
	}

	@Override
	public List<Card> findByPublishAndGroupId(Long groupId) {
		List<Card> cards =  cardRepository.findByPublishAndGroupId("Y", groupId);
		if(cards.isEmpty()) {
			throw new EntityNotFoundException(Card.class,"Publish & Group ID", "Y & "+groupId);
		}
		return cards;
	}

	@Override
	public void deleteCard(long cardId) {
		cardRepository.deleteById(cardId);
	}

	@Override
	public Card findByShortUrl(String shortUrl) {
		Card card = cardRepository.findByShortUrlAndPublish(shortUrl, "Y");
		if(card==null) {
			throw new EntityNotFoundException(Card.class,"Short Url & Publish", shortUrl+" & Y");
		}
		return card;
	}

}
