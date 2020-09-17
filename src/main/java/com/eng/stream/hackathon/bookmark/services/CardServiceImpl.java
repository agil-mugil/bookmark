package com.eng.stream.hackathon.bookmark.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eng.stream.hackathon.bookmark.EntityNotFoundException;
import com.eng.stream.hackathon.bookmark.models.Bookmark;
import com.eng.stream.hackathon.bookmark.models.Card;
import com.eng.stream.hackathon.bookmark.repositories.BookmarkRepository;
import com.eng.stream.hackathon.bookmark.repositories.CardRepository;
import com.eng.stream.hackathon.bookmark.repositories.GroupAdminRepository;
import com.eng.stream.hackathon.bookmark.utils.Helper;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private GroupAdminRepository groupAdminRepository;
	
	@Autowired
	private BookmarkRepository bookmarkRepository;
	
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
	public List<Card> findByPublishAndGroupId(Long groupId,String currentUser) {
		List<Card> cards = new ArrayList<Card>();
		// if the user is admin, display all the cards including the not marked as published, else display published ones only
		if(groupAdminRepository.findCountByGroupAndUserId(groupId, currentUser)==0){
			cards =  cardRepository.findByPublishAndGroupId("Y", groupId);
		} else {
			cards=cardRepository.findByGroupId(groupId);
		}
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
			Bookmark bookmark = bookmarkRepository.findByShortUrl(shortUrl);
			if(bookmark==null) {
				throw new EntityNotFoundException(Bookmark.class,"shortUrl", shortUrl);
			}else {
				card = Helper.generateCardFromBookmark(bookmark);
			}
		}
		return card;
	}

	

	@Override
	public Card publishCard(Long cardId,String currentUser) {
		Optional<Card> card = cardRepository.findById(cardId);
		Card publishCard = card.get();
		publishCard.setPublish("Y");
		return cardRepository.save(publishCard);
	}


}
