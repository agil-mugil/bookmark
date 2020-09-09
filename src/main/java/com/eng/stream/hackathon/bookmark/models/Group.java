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

/**
 * @Author Prabhu MURUGESAN
 */

@Entity
@Table(name = "TBOOKMARKGROUPSENG")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="GROUPID", nullable=false, unique=true)
	 private Long groupId;
	
	@Column(name="GROUPTYPE", length=15, nullable=false, unique=false)
	 private String groupType;
	
	@Column(name="GROUPVALUE", length=30, nullable=false, unique=false)
	 private String groupValue;
	
	@Column(name="GROUPCREATOR", length=100, nullable=false, unique=false)
	 private String creator;
	
	@Column(name="OPEMOD", length=100, nullable=true, unique=false)
	 private String modifier;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
	@Column(name="DATCRE", nullable=false, unique=false)
	 private Date createdDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
	@Column(name="DATMOD", nullable=true, unique=false)
	 private Date modifiedDate;
	 
	@Column(name="OPEDEL", length=100, nullable=true, unique=false)
	 private String eraser;
	 
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public String getGroupValue() {
		return groupValue;
	}
	public void setGroupValue(String groupValue) {
		this.groupValue = groupValue;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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
		return "Group [groupId=" + groupId + ", groupType=" + groupType + ", groupValue=" + groupValue + ", creator="
				+ creator + ", modifier=" + modifier + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate
				+ ", eraser=" + eraser + "]";
	}
	 
}
