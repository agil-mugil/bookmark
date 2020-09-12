package com.eng.stream.hackathon.bookmark.models;

public class CardBean {

	private String bookmarkUrl;
	private String cardTitle;
	private String cardDesc;
	private String imageUrl;
	private String shortUrl;
	private Long groupId;
	private String publish;
	
	public CardBean() {
		super();
	}
	public CardBean(String bookmarkUrl, String cardTitle,  String imageUrl, String shortUrl, Long groupId,
			String publish) {
		super();
		this.bookmarkUrl = bookmarkUrl;
		this.cardTitle = cardTitle;
		this.cardDesc = cardTitle;
		this.imageUrl = imageUrl;
		this.shortUrl = shortUrl;
		this.groupId = groupId;
		this.publish = publish;
	}
	public String getBookmarkUrl() {
		return bookmarkUrl;
	}
	public void setBookmarkUrl(String bookmarkUrl) {
		this.bookmarkUrl = bookmarkUrl;
	}
	public String getCardTitle() {
		return cardTitle;
	}
	public void setCardTitle(String cardTitle) {
		this.cardTitle = cardTitle;
	}
	public String getCardDesc() {
		return cardDesc;
	}
	public void setCardDesc(String cardDesc) {
		this.cardDesc = cardDesc;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	
}
