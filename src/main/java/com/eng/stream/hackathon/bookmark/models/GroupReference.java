
package com.eng.stream.hackathon.bookmark.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "TGROUPREFDATAENG")
@EntityListeners(AuditingEntityListener.class)
public class GroupReference extends Auditable<String> implements Serializable {

	private static final long serialVersionUID = -414141740190754960L;

	@Column(name = "GROUPTYPE", nullable = false)
	@Id
	private String groupType;

	@Column(name = "GROUPVALUE", nullable = false)
	@Id
	private String groupValue;

	@Column(name = "GROUPVALUEDESC", nullable = false)
	private String groupValueDesc;

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

	public GroupReference() {

	}

	public GroupReference(String groupType, String groupValue) {
		super();
		this.groupType = groupType;
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
		GroupReference other = (GroupReference) obj;
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
