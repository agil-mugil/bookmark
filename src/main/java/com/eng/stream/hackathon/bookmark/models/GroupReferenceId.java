package com.eng.stream.hackathon.bookmark.models;

import java.io.Serializable;

public class GroupReferenceId implements Serializable {

	private static final long serialVersionUID = 1L;

	private String groupType;
	private String groupValue;
	
	public GroupReferenceId() {
		
	}
	public GroupReferenceId(String groupType, String groupValue) {
		super();
		this.groupType = groupType;
		this.groupValue = groupValue;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((groupType == null) ? 0 : groupType.hashCode());
		result = prime * result + ((groupValue == null) ? 0 : groupValue.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupReferenceId other = (GroupReferenceId) obj;
		if (groupType == null) {
			if (other.groupType != null)
				return false;
		} else if (!groupType.equals(other.groupType))
			return false;
		if (groupValue == null) {
			if (other.groupValue != null)
				return false;
		} else if (!groupValue.equals(other.groupValue))
			return false;
		return true;
	}
	
}
