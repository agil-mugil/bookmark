package com.eng.stream.hackathon.bookmark.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author Prabhu MURUGESAN
 */

@Entity
@Table(name = "TBOOKMARKGROUPSENG")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@ApiModel(description = "Provides details about  a group")
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Unique id of a group", hidden = true)
	@Column(name="GROUPID", nullable=false, unique=true)
	 private Long groupId;
	
	@ApiModelProperty(notes = "Group type details like TRIBE, FT, APPLICATION/PLATFORM")
	@Column(name="GROUPTYPE", length=15, nullable=false, unique=false)
	 private String groupType;
	
	@ApiModelProperty(notes = "Group value holds the type of group based on ")
	@Column(name="GROUPVALUE", length=30, nullable=false, unique=false)
	 private String groupValue;
	
	@ApiModelProperty(hidden = true)
	@Column(name="GROUPCREATOR", length=100, nullable=false, unique=false)
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
	 
	@OneToMany(fetch = FetchType.LAZY, mappedBy="group", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("group")
	@ApiModelProperty(hidden = true)
	private List<GroupAdmin> groupAdmins = new ArrayList<>();
	
	public Group() {
	}
	
	public Group(String groupType, String groupValue, String creator, Date createdDate) {
		super();
		this.groupType = groupType;
		this.groupValue = groupValue;
		this.creator = creator;
		this.createdDate = createdDate;
	}
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
	
	public List<GroupAdmin> getGroupAdmins() {
		return groupAdmins;
	}

	public void setGroupAdmins(List<GroupAdmin> groupAdmins) {
		this.groupAdmins = groupAdmins;
	}

	@Override
	public String toString() {
		return "Group [groupId=" + groupId + ", groupType=" + groupType + ", groupValue=" + groupValue + ", creator="
				+ creator + ", createdDate=" + createdDate + ", modifier=" + modifier + ", modifiedDate=" + modifiedDate
				+ ", eraser=" + eraser + ", groupAdmins=" + groupAdmins + "]";
	}


	 
}
