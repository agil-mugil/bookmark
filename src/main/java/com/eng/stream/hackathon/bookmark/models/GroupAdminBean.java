package com.eng.stream.hackathon.bookmark.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class GroupAdminBean {

	private Long groupId;
	private String userId;
	private String username;
	public GroupAdminBean() {
		super();
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@JsonIgnore
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public GroupAdminBean(Long groupId, String userId) {
		super();
		this.groupId = groupId;
		this.userId = userId;
	}
	
}
