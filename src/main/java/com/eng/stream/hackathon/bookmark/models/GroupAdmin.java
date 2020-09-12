package com.eng.stream.hackathon.bookmark.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "TGROUPADMINSENG")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@ApiModel(description = "Entity to store group admin details")
public class GroupAdmin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Unique id of a group" , hidden = true)
	@Column(name="GROUPUSERID", nullable=false, unique=true)
	private Long groupUserId;
	
	@Column(name="USERID", length=100, nullable=false, unique=true)
	private String userId;
	
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GROUPID",referencedColumnName="groupid")
	@JsonIgnoreProperties("groupAdmins")
	private Group group = new Group();
	
	public GroupAdmin() {
		super();
	}

	public GroupAdmin(String userId, String creator, Date createdDate) {
		super();
		this.userId = userId;
		this.creator = creator;
		this.createdDate = createdDate;
	}

	public Long getGroupUserId() {
		return groupUserId;
	}

	public void setGroupUserId(Long groupUserId) {
		this.groupUserId = groupUserId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public Long getGroupId() {
		return this.group.getGroupId();
	}

	@JsonIgnore
	public Group getGroup() {
		return group;
	}

	@JsonIgnore
	public void setGroup(Group group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "GroupAdmin [groupUserId=" + groupUserId + ", userId=" + userId + ", creator=" + creator
				+ ", createdDate=" + createdDate + ", modifier=" + modifier + ", modifiedDate=" + modifiedDate
				+ ", eraser=" + eraser + "]";
	}


	
}
