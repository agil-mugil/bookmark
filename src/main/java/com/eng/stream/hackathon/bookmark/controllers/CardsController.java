package com.eng.stream.hackathon.bookmark.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eng.stream.hackathon.bookmark.models.Card;
import com.eng.stream.hackathon.bookmark.models.CardBean;
import com.eng.stream.hackathon.bookmark.services.CardService;
import com.eng.stream.hackathon.bookmark.utils.BeanToEntityConverter;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/cards")
public class CardsController {

	@Autowired
	private CardService cardService;
	
	private  static final String ALL_CARDS = "/api/v1/cards";
	private  static final String CREATE_CARD = "/createCard";
	private static final String CARD_BY_SHORTURL="/cardByShortUrl";
	
	@GetMapping
	@ApiOperation(value = "Get all the cards", notes = "This service is to get all the cards", response =ResponseEntity.class )
	public ResponseEntity<List<Card>> allCards() {
		try {
            return ResponseEntity.ok()
                    .location((new URI(ALL_CARDS)))
                    .body(cardService.findAllCards());
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	}
	
	@GetMapping("/cardByShortUrl")
	@ApiOperation(value = "Get all the card", notes = "This service is to get all the a particular card that matches the short URL", response =ResponseEntity.class )
	public ResponseEntity<Card> cardByShortURL(@RequestParam String shortUrl) {
		try {
            return ResponseEntity.ok()
                    .location((new URI(CARD_BY_SHORTURL)))
                    .body(cardService.findByShortUrl(shortUrl));
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	}
	
	@PostMapping("/createCard")
	@ApiOperation(value = "Create the Card", notes = "Service to create a card", response =ResponseEntity.class )
	public ResponseEntity<Card> createBookmark(@RequestBody CardBean cardBean) {
		try {
            return ResponseEntity.created(new URI(CREATE_CARD))
                    .body(cardService.createCard(BeanToEntityConverter.convertToEntity(cardBean)));
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	}
}
