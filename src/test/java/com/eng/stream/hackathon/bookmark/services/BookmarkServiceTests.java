package com.eng.stream.hackathon.bookmark.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.eng.stream.hackathon.bookmark.EntityNotFoundException;
import com.eng.stream.hackathon.bookmark.models.Bookmark;
import com.eng.stream.hackathon.bookmark.repositories.BookmarkRepository;

@SpringBootTest
public class BookmarkServiceTests {
	
	@Autowired
	private BookmarkService bookmarkService;
	
	@MockBean
	private BookmarkRepository bookmarkRepository;

	
	
	@BeforeEach
	public void setUp() {
		List<Bookmark> bookmarkList = new ArrayList<Bookmark>();
		Bookmark bookmark = new Bookmark("https://www.baeldung.com/database-auditing-jpa", "http://localhost:8080/bookmark/baeldung", 
				new Date(System.currentTimeMillis()+10));
		bookmarkList.add(bookmark);
	    Mockito.when(bookmarkRepository.findAll()).thenReturn(bookmarkList);
	}
	
	@Test
	void testCreateGroup() {
		Bookmark bookmark = new Bookmark("https://www.baeldung.com/database-auditing-jpa", "http://localhost:8080/bookmark/baeldung", 
				new Date(System.currentTimeMillis()+10));
	    doReturn(bookmark).when(bookmarkRepository).save(any());
	    Bookmark created = bookmarkService.createBookmark(bookmark);
		assertNotNull(created);
	}

	@Test
	void testFindAllGroups() {
		List<Bookmark> bookmarkList = bookmarkService.getAllBookmarks();
		assertThat(bookmarkList.size()).isGreaterThan(0);
	}

	@Test
	void testFindAllGroupsWithNoEntityFound() {
		try {
			List<Bookmark> bookmarkList = new ArrayList<Bookmark>();
			doReturn(bookmarkList).when(bookmarkRepository).findAll();
			bookmarkService.getAllBookmarks();
			fail(" EntityNotFoundException exception not thrown");
		} catch (EntityNotFoundException e) {
			assertThat(e.getMessage()).isEqualTo("Bookmark was not found for parameters {}");
			System.out.println(e.getCause());
		}
	}
	
}
