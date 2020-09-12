package com.eng.stream.hackathon.bookmark.models;

public class GroupAdminBean {

	private Long groupId;
	private String userId;
	private String creator;
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
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public GroupAdminBean(Long groupId, String userId, String creator) {
		super();
		this.groupId = groupId;
		this.userId = userId;
		this.creator = creator;
	}
	
}
