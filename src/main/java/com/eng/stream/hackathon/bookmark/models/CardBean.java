package com.eng.stream.hackathon.bookmark.models;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CardBean {

	private String bookmarkUrl;
	private String cardTitle;
	private String cardDesc;
	private String imageUrl;
	private String shortUrl;
	private Long groupId;
	private String publish;
	private String creator;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
	private Date createdDate;
	
	public CardBean() {
		super();
	}
	public CardBean(String bookmarkUrl, String cardTitle,  String imageUrl, String shortUrl, Long groupId,
			String publish, String creator) {
		super();
		this.bookmarkUrl = bookmarkUrl;
		this.cardTitle = cardTitle;
		this.cardDesc = cardTitle;
		this.imageUrl = imageUrl;
		this.shortUrl = shortUrl;
		this.groupId = groupId;
		this.publish = publish;
		this.creator = creator;
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
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Override
	public String toString() {
		return "CardBean [bookmarkUrl=" + bookmarkUrl + ", cardTitle=" + cardTitle + ", cardDesc=" + cardDesc
				+ ", imageUrl=" + imageUrl + ", shortUrl=" + shortUrl + ", groupId=" + groupId + ", publish=" + publish
				+ ", creator=" + creator + ", createdDate=" + createdDate + "]";
	}
	
	
}
