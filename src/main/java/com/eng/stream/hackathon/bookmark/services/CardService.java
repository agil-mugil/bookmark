package com.eng.stream.hackathon.bookmark.services;

import java.util.List;

import com.eng.stream.hackathon.bookmark.models.Card;

public interface CardService {

	public Card  createCard(Card card);

	public List<Card> findAllCards();

	public List<Card> findAllPublishedCards();

	public List<Card> findByPublishAndGroupId(Long groupId);

	public void deleteCard(long cardId);

}