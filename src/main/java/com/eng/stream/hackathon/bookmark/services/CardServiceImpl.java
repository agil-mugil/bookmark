package com.eng.stream.hackathon.bookmark.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return cardRepository.findAll();
	}

	@Override
	public List<Card> findAllPublishedCards() {
		return cardRepository.findByPublish("Y");
	}

	@Override
	public List<Card> findByPublishAndGroupId(Long groupId) {
		return cardRepository.findByPublishAndGroupId("Y", groupId);
	}

	@Override
	public void deleteCard(long cardId) {
		cardRepository.deleteById(cardId);
	}

}
