package com.eng.stream.hackathon.bookmark.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "TGROUPADMINSENG")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@ApiModel(description = "Entity to store group admin details")
@EntityListeners(AuditingEntityListener.class)
public class GroupAdmin extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Unique id of a group" , hidden = true)
	@Column(name="GROUPUSERID", nullable=false, unique=true)
	private Long groupUserId;
	
	@Column(name="USERID", length=100, nullable=false, unique=false)
	private String userId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GROUPID",referencedColumnName="groupid")
	@JsonIgnoreProperties("groupAdmins")
	private Group group = new Group();
	
	public GroupAdmin() {
		super();
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

	public Long getGroupId() {
		return this.group.getGroupId();
	}

	@JsonIgnore
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "GroupAdmin [groupUserId=" + groupUserId + ", userId=" + userId + "]";
	}

	
}
