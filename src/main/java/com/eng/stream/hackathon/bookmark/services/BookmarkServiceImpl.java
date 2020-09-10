package com.eng.stream.hackathon.bookmark.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eng.stream.hackathon.bookmark.models.Bookmark;
import com.eng.stream.hackathon.bookmark.repositories.BookmarkRepository;

@Service
public class BookmarkServiceImpl implements BookmarkService {

	@Autowired
	private BookmarkRepository bookmarkRepository;
	
	@Override
	public Bookmark createBookmark(Bookmark bookmark) {
		return bookmarkRepository.save(bookmark);
	}

	@Override
	public List<Bookmark> getAllBookmarks() {
		return bookmarkRepository.findAll();
	}

}
