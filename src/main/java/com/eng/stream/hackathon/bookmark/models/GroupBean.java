package com.eng.stream.hackathon.bookmark.models;

public class GroupBean {
	private String groupType;
	 private String groupValue;
	 private String creator;
	 
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
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	 
	public GroupBean(String groupType, String groupValue, String creator) {
		super();
		this.groupType = groupType;
		this.groupValue = groupValue;
		this.creator = creator;
	}
	 
}
