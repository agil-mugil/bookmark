package com.eng.stream.hackathon.bookmark.services;

import java.util.List;

import com.eng.stream.hackathon.bookmark.models.Bookmark;

public interface BookmarkService {

	public Bookmark createBookmark(Bookmark bookmark);
	public List<Bookmark> getAllBookmarks();
	public Bookmark findByShortUrl(String shortUrl);
}
