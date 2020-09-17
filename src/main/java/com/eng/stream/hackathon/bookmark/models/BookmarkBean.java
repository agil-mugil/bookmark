package com.eng.stream.hackathon.bookmark.models;

import java.sql.Date;

public class BookmarkBean {
	private String bookmarkUrl;
	private String shortUrl;
	private Date expiryDate;
	private String currentUser;
	
	public String getBookmarkUrl() {
		return bookmarkUrl;
	}
	public void setBookmarkUrl(String bookmarkUrl) {
		this.bookmarkUrl = bookmarkUrl;
	}
	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public BookmarkBean() {
		
	}
	public String getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}
	public BookmarkBean(String bookmarkUrl, String shortUrl, Date expiryDate) {
		super();
		this.bookmarkUrl = bookmarkUrl;
		this.shortUrl = shortUrl;
		this.expiryDate = expiryDate;
	}
	
	
}
