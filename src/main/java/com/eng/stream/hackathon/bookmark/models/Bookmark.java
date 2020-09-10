package com.eng.stream.hackathon.bookmark.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "TBOOKMARKSENG")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@ApiModel(description = "Bookmark details bean")
public class Bookmark {

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
	
	@ApiModelProperty(hidden = true)
	@Column(name="OPECRE", length=100, nullable=false, unique=false)
	 private String creator;
	
	@ApiModelProperty(hidden = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
	@Column(name="DATCRE", nullable=false, unique=false)
	 private Date createdDate;
	
	@ApiModelProperty(hidden = true)
	@Column(name="OPEMOD", length=100, nullable=true, unique=false)
	 private String modifier;
	
	@ApiModelProperty(hidden = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
	@Column(name="DATMOD", nullable=true, unique=false)
	 private Date modifiedDate;
	 
	@ApiModelProperty(hidden = true)
	@Column(name="OPEDEL", length=100, nullable=true, unique=false)
	 private String eraser;

	public Bookmark() {
		super();
	}
	
	
	public Bookmark( String bookmarkUrl, String shortUrl, Date expiryDate, String creator, Date createdDate) {
		super();
		this.bookmarkUrl = bookmarkUrl;
		this.shortUrl = shortUrl;
		this.expiryDate = expiryDate;
		this.creator = creator;
		this.createdDate = createdDate;
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

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getEraser() {
		return eraser;
	}

	public void setEraser(String eraser) {
		this.eraser = eraser;
	}


	@Override
	public String toString() {
		return "Bookmark [bookmarkId=" + bookmarkId + ", bookmarkUrl=" + bookmarkUrl + ", shortUrl=" + shortUrl
				+ ", expiryDate=" + expiryDate + ", creator=" + creator + ", createdDate=" + createdDate + ", modifier="
				+ modifier + ", modifiedDate=" + modifiedDate + ", eraser=" + eraser + "]";
	}
	
	
}
