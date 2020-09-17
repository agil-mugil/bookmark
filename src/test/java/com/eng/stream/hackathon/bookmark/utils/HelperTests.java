package com.eng.stream.hackathon.bookmark.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import com.eng.stream.hackathon.bookmark.models.Bookmark;
import com.eng.stream.hackathon.bookmark.models.Card;

public class HelperTests {

	@Test
	public void testGenerateCard() {
		Bookmark bookmark = new Bookmark();
		bookmark.setBookmarkUrl("https://www.fiftythree.com/");
		bookmark.setShortUrl("http://localhost:8080/fiftythree");
		bookmark.setExpiryDate(new Date(System.currentTimeMillis()));
		
		Card card = Helper.generateCardFromBookmark(bookmark);
		assertThat(card.getBookmarkUrl().equals(bookmark.getBookmarkUrl()));
		assertThat(card.getShortUrl().equals(bookmark.getShortUrl()));
		
	}
	
	@Test
	public void testExpire() {
		String expired = Helper.isUrlExpired(new Date(System.currentTimeMillis()-1000000000L));
		assertThat(expired).isEqualTo("Y");
		System.out.println(expired);
		expired = Helper.isUrlExpired(new Date(System.currentTimeMillis()+10000000000L));
		assertThat(expired).isEqualTo("N");
		System.out.println(expired);
	}
}
