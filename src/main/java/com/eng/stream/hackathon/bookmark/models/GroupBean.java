package com.eng.stream.hackathon.bookmark.models;

public class GroupBean {
	private String groupType;
	private String groupValue;
	 
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
	 
}
