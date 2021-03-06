package com.eng.stream.hackathon.bookmark.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.eng.stream.hackathon.bookmark.models.Bookmark;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class BookmarkRepositoryIT {

	@Autowired
	private BookmarkRepository bookmarkRepository;
	
	@Test
	@Order(1)
	public void testCreateBookmark() {
		Bookmark bookmark = new Bookmark("https://www.baeldung.com/database-auditing-jpa", "http://localhost:8080/bookmark/baeldung", 
				new Date(System.currentTimeMillis()+10));
		bookmark.setCreatedBy("prabhu.murugesan");
		Bookmark saved =  bookmarkRepository.save(bookmark);
		assertNotNull(saved);
		List<Bookmark> bookmarks = bookmarkRepository.findAll();
		assertThat(bookmarks.size()).isGreaterThan(0);
		bookmarkRepository.delete(saved);
	}
	
	
}
