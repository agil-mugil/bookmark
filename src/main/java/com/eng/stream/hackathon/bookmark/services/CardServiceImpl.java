package com.eng.stream.hackathon.bookmark.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eng.stream.hackathon.bookmark.EntityNotFoundException;
import com.eng.stream.hackathon.bookmark.models.Card;
import com.eng.stream.hackathon.bookmark.repositories.CardRepository;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private CardRepository cardRepository;
	
	@Override
	public Card createCard(Card card) {
		return cardRepository.saveAndFlush(card);
	}

	@Override
	public List<Card> findAllCards() {
		List<Card> cards = cardRepository.findAll();
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
			throw new EntityNotFoundException(Card.class,"Publish", "Y");
		}
		return cards;
	}

	@Override
	public void deleteCard(long cardId) {
		cardRepository.deleteById(cardId);
	}

}
