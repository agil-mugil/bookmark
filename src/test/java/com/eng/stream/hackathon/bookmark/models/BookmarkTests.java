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

import com.fasterxml.jackson.annotation.JsonFormat;

public class BookmarkTests {

public Class<Bookmark> bookmarkClass = Bookmark.class; 
	
	@Test
	void mustHaveEntityAnnotation() {
		assertTrue(bookmarkClass.isAnnotationPresent(Entity.class));
		
	}
	
	@Test
	void mustHaveTableAnnotation() {
		assertTrue(bookmarkClass.isAnnotationPresent(Entity.class));
	}

	@Test
	void mustMatchTableName()  {
		Table table = bookmarkClass.getAnnotation(Table.class);
		assertEquals("TBOOKMARKSENG",table.name());
	}
	
	@Test
	void validateBookmarkId() throws NoSuchFieldException, SecurityException {
		Field bookmarkField = bookmarkClass.getDeclaredField("bookmarkId");
		
		//check @Id present
		Id idAnnotation = bookmarkField.getAnnotation(Id.class);
		assertNotNull(idAnnotation);
		
		//check @GeneratedValue present
		GeneratedValue generateValue = bookmarkField.getAnnotation(GeneratedValue.class);
		assertNotNull(generateValue);
		
		//check @Column present
		Column columnAnnotation = bookmarkField.getAnnotation(Column.class);
		assertNotNull(columnAnnotation);
		
		// check for column name and length matches
		assertEquals("BOOKMARKID", columnAnnotation.name());
		assertEquals(255, columnAnnotation.length());
	}
	
	@Test
	void validateBookmarkUrl() throws NoSuchFieldException, SecurityException {
		Field bookmarkField = bookmarkClass.getDeclaredField("bookmarkUrl");
		Column columnAnnotation = bookmarkField.getAnnotation(Column.class);
		assertNotNull(columnAnnotation);
		assertEquals("BOOKMARKURL", columnAnnotation.name());
		assertEquals(1000, columnAnnotation.length());
	}
	
	@Test
	void validateShortUrl() throws NoSuchFieldException, SecurityException {
		Field bookmarkField = bookmarkClass.getDeclaredField("shortUrl");
		Column columnAnnotation = bookmarkField.getAnnotation(Column.class);
		assertNotNull(columnAnnotation);
		assertEquals("SHORTURL", columnAnnotation.name());
		assertEquals(250, columnAnnotation.length());
	}
	
	@Test
	void validateExpiryDate() throws NoSuchFieldException, SecurityException {
		Field bookmarkField = bookmarkClass.getDeclaredField("expiryDate");
		Column columnAnnotation = bookmarkField.getAnnotation(Column.class);
		assertNotNull(columnAnnotation);
		assertEquals("EXPIRYDATE", columnAnnotation.name());
		
		//check for date format 
		JsonFormat dateFomat=bookmarkField.getAnnotation(JsonFormat.class);
		assertNotNull(dateFomat);
		assertEquals("MM-dd-yyyy", dateFomat.pattern());
	}
	
}
