
package com.eng.stream.hackathon.bookmark.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@IdClass(GroupReferenceId.class)
@Table(name = "TGROUPREFDATAENG")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@EntityListeners(AuditingEntityListener.class)
public class GroupReference extends Auditable<String> implements Serializable {
private static final long serialVersionUID = 2761046565822681668L;

	@Id
	@Column(name = "GROUPTYPE", nullable = false)
	private String groupType;

	@Id
	@Column(name = "GROUPVALUE", nullable = false)
	private String groupValue;

	@Column(name = "GROUPVALUEDESC", nullable = false)
	private String groupValueDesc;

	public GroupReference() {
		
	}
	public GroupReference(String groupType, String groupValue, String groupValueDesc) {
		super();
		this.groupType = groupType;
		this.groupValue = groupValue;
		this.groupValueDesc = groupValueDesc;
	}
	public GroupReference(String groupType, String groupValue, String groupValueDesc, String createdBy, Date createdDate) {
		super();
		this.groupType = groupType;
		this.groupValue = groupValue;
		this.groupValueDesc = groupValueDesc;
		this.createdBy=createdBy;
		this.createdDate =createdDate;
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
	
	public String getGroupValueDesc() {
		return groupValueDesc;
	}
	public void setGroupValueDesc(String groupValueDesc) {
		this.groupValueDesc = groupValueDesc;
	}
	
	@Override
	public String toString() {
		return "GroupReference [groupType=" + groupType + ", groupValue=" + groupValue + ", groupValueDesc="
				+ groupValueDesc + "]";
	}
	

}
