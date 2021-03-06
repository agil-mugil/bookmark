package com.eng.stream.hackathon.bookmark.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.junit.jupiter.api.Test;

public class CardTests {

	public Class<Card> cardClass = Card.class; 
	@Test
	void mustHaveEntityAnnotation() {
		assertTrue(cardClass.isAnnotationPresent(Entity.class));
		
	}
	
	@Test
	void mustHaveTableAnnotation() {
		assertTrue(cardClass.isAnnotationPresent(Entity.class));
	}

	@Test
	void mustMatchTableName()  {
		Table table = cardClass.getAnnotation(Table.class);
		assertEquals("TCARDSENG",table.name());
	}
	
	@Test
	void validateCardId() throws NoSuchFieldException, SecurityException {
		Field cardField = cardClass.getDeclaredField("cardId");
		
		//check @Id present
		Id idAnnotation = cardField.getAnnotation(Id.class);
		assertNotNull(idAnnotation);
		
		//check @GeneratedValue present
		GeneratedValue generateValue = cardField.getAnnotation(GeneratedValue.class);
		assertNotNull(generateValue);
		
		//check @Column present
		Column columnAnnotation = cardField.getAnnotation(Column.class);
		assertNotNull(columnAnnotation);
		
		// check for column name and length matches
		assertEquals("CARDID", columnAnnotation.name());
		assertEquals(255, columnAnnotation.length());
	}
	
	@Test
	void validateBookmarkUrl() throws NoSuchFieldException, SecurityException {
		Field cardField = cardClass.getDeclaredField("bookmarkUrl");
		Column columnAnnotation = cardField.getAnnotation(Column.class);
		assertNotNull(columnAnnotation);
		assertEquals("BOOKMARKURL", columnAnnotation.name());
		assertEquals(1000, columnAnnotation.length());
	}
	
	@Test
	void validateCardTitle() throws NoSuchFieldException, SecurityException {
		Field cardField = cardClass.getDeclaredField("cardTitle");
		Column columnAnnotation = cardField.getAnnotation(Column.class);
		assertNotNull(columnAnnotation);
		assertEquals("CARDTITLE", columnAnnotation.name());
		assertEquals(45, columnAnnotation.length());
	}
	@Test
	void validateCardDesc() throws NoSuchFieldException, SecurityException {
		Field cardField = cardClass.getDeclaredField("cardDesc");
		Column columnAnnotation = cardField.getAnnotation(Column.class);
		assertNotNull(columnAnnotation);
		assertEquals("CARDDESC", columnAnnotation.name());
		assertEquals(250, columnAnnotation.length());
	}
	@Test
	void validateImageUrl() throws NoSuchFieldException, SecurityException {
		Field cardField = cardClass.getDeclaredField("shortUrl");
		Column columnAnnotation = cardField.getAnnotation(Column.class);
		assertNotNull(columnAnnotation);
		assertEquals("SHORTURL", columnAnnotation.name());
		assertEquals(250, columnAnnotation.length());
	}
	
	@Test
	void validateShortUrl() throws NoSuchFieldException, SecurityException {
		Field cardField = cardClass.getDeclaredField("shortUrl");
		Column columnAnnotation = cardField.getAnnotation(Column.class);
		assertNotNull(columnAnnotation);
		assertEquals("SHORTURL", columnAnnotation.name());
		assertEquals(250, columnAnnotation.length());
	}
	
	@Test
	void validateGroupId() throws NoSuchFieldException, SecurityException {
		Field cardField = cardClass.getDeclaredField("groupId");
		Column columnAnnotation = cardField.getAnnotation(Column.class);
		assertNotNull(columnAnnotation);
		assertEquals("GROUPID", columnAnnotation.name());
		assertEquals(255, columnAnnotation.length());
	}
	
	@Test
	void validatePublish() throws NoSuchFieldException, SecurityException {
		Field cardField = cardClass.getDeclaredField("publish");
		Column columnAnnotation = cardField.getAnnotation(Column.class);
		assertNotNull(columnAnnotation);
		assertEquals("PUBLISH", columnAnnotation.name());
		assertEquals(1, columnAnnotation.length());
	}
	
}
