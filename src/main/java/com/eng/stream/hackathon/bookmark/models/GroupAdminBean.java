package com.eng.stream.hackathon.bookmark.models;

public class GroupAdminBean {

	private Long groupId;
	private String userId;
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
	public GroupAdminBean(Long groupId, String userId) {
		super();
		this.groupId = groupId;
		this.userId = userId;
	}
	
}
