package com.eng.stream.hackathon.bookmark.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "TBOOKMARKSENG")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@ApiModel(description = "Bookmark details bean")
@EntityListeners(AuditingEntityListener.class)
public class Bookmark extends Auditable<String>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="BOOKMARKID", nullable=false, unique=true)
	@ApiModelProperty(hidden = true)
	private Long bookmarkId;
	
	@Column(name="BOOKMARKURL", length=1000, nullable=false, unique=false)
	private String bookmarkUrl;
	
	@Column(name="SHORTURL", length=250, nullable=false, unique=true)
	private String shortUrl;
	
	@Column(name="EXPIRYDATE",nullable=false, unique=false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
	private Date expiryDate;
	
	public Bookmark() {
		super();
	}
	
	
	public Bookmark( String bookmarkUrl, String shortUrl, Date expiryDate) {
		super();
		this.bookmarkUrl = bookmarkUrl;
		this.shortUrl = shortUrl;
		this.expiryDate = expiryDate;
	}


	public Long getBookmarkId() {
		return bookmarkId;
	}

	public void setBookmarkId(Long bookmarkId) {
		this.bookmarkId = bookmarkId;
	}

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

	@Override
	public String toString() {
		return "Bookmark [bookmarkId=" + bookmarkId + ", bookmarkUrl=" + bookmarkUrl + ", shortUrl=" + shortUrl
				+ ", expiryDate=" + expiryDate + "]";
	}
	
}
