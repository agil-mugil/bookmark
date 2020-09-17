package com.eng.stream.hackathon.bookmark.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "TCARDSENG")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@ApiModel(description = "Provides details about  a group")
@EntityListeners(AuditingEntityListener.class)
public class Card extends Auditable<String>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Unique id of a card", hidden = true)
	@Column(name="CARDID", nullable=false, unique=true)
	private Long cardId;
	
	@Column(name="BOOKMARKURL", length=1000, nullable=false, unique=false)
	private String bookmarkUrl;
	
	@Column(name="CARDTITLE", length=45, nullable=false, unique=false)
	private String cardTitle;
	
	@Column(name="CARDDESC", length=250, nullable=false, unique=false)
	private String cardDesc;
	
	@Column(name="IMAGEURL", length=250, nullable=false, unique=false)
	private String imageUrl;
	
	@Column(name="SHORTURL", length=250, nullable=false, unique=true)
	private String shortUrl;
	
	@Column(name="GROUPID", nullable=false, unique=false)
	private Long groupId;
	
	@Column(name="PUBLISH",length=1,  nullable=false, unique=false)
	private String publish;
	
	public Card() {
		super();
	}
	
	public Card(String bookmarkUrl, String cardTitle,  String imageUrl, String shortUrl, Long groupId,
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

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
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

	@Override
	public String toString() {
		return "Card [cardId=" + cardId + ", bookmarkUrl=" + bookmarkUrl + ", cardTitle=" + cardTitle + ", cardDesc="
				+ cardDesc + ", imageUrl=" + imageUrl + ", shortUrl=" + shortUrl + ", groupId=" + groupId + ", publish="
				+ publish + "]";
	}
	
}
