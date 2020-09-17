package com.eng.stream.hackathon.bookmark.utils;

import java.sql.Date;

import com.eng.stream.hackathon.bookmark.models.Bookmark;
import com.eng.stream.hackathon.bookmark.models.Card;

public class Helper {
	public static Card generateCardFromBookmark(Bookmark bookmark) {
		Card card = new Card();
		card.setBookmarkUrl(bookmark.getBookmarkUrl());
		card.setShortUrl(bookmark.getShortUrl());
		card.setExpired(isUrlExpired(bookmark.getExpiryDate()));
		return card;
	}
	
	public static String isUrlExpired(Date expiryDate) {
		String expired="N";
		Date currentDate = new Date(System.currentTimeMillis());
		if(currentDate.after(expiryDate)) {
			expired = "Y";
		}
		return expired;
	}
}
