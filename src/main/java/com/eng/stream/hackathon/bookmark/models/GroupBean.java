package com.eng.stream.hackathon.bookmark.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class GroupBean {
	private String groupType;
	private String groupValue;
	private String username;
	 
	 public GroupBean() {
		 super();
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
	public GroupBean(String groupType, String groupValue) {
		super();
		this.groupType = groupType;
		this.groupValue = groupValue;
	}
	@JsonIgnore
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	 
}
