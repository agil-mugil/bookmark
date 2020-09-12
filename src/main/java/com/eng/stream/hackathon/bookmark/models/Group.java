package com.eng.stream.hackathon.bookmark.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.eng.stream.hackathon.bookmark.audit.AuditorAwareImpl;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author Prabhu MURUGESAN
 */

@Entity
@Table(name = "TBOOKMARKGROUPSENG")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@ApiModel(description = "Provides details about  a group")
@EntityListeners(AuditingEntityListener.class)
public class Group extends Auditable<String>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Unique id of a group", hidden = true)
	@Column(name="GROUPID", nullable=false, unique=true)
	 private Long groupId;
	
	@ApiModelProperty(notes = "Group type details like TRIBE, FT, APPLICATION/PLATFORM")
	@Column(name="GROUPTYPE", length=15, nullable=false, unique=false)
	 private String groupType;
	
	@ApiModelProperty(notes = "Group value holds the type of group based on ")
	@Column(name="GROUPVALUE", length=30, nullable=false, unique=false)
	 private String groupValue;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="group", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("group")
	@ApiModelProperty(hidden = true)
	private List<GroupAdmin> groupAdmins = new ArrayList<>();
	
	@OneToMany
	@JoinColumn(name = "GROUPID",  referencedColumnName = "GROUPID" , insertable = false, updatable = false)
	private List<Card> groupCards= new ArrayList<>();
	
	public Group() {
	}
	
	public Group(String groupType, String groupValue) {
		super();
		this.setGroupType(groupType);
		this.setGroupValue(groupValue);
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
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
	
	public List<GroupAdmin> getGroupAdmins() {
		return groupAdmins;
	}

	public void setGroupAdmins(List<GroupAdmin> groupAdmins) {
		this.groupAdmins = groupAdmins;
		setGroupAdminUserId();
	}

	private void setGroupAdminUserId() {
		AuditorAwareImpl auditor = new AuditorAwareImpl();
		Optional<String> currentUser = auditor.getCurrentAuditor();
		if(currentUser.isPresent()) {
			String userId = currentUser.get();
			this.groupAdmins.get(0).setUserId(userId);
		}
	}
	
	public List<Card> getGroupCards() {
		return groupCards;
	}
	
	public void setGroupCards(List<Card> groupCards) {
		this.groupCards = groupCards;
	}

	@Override
	public String toString() {
		return "Group [groupId=" + groupId + ", groupType=" + groupType + ", groupValue=" + groupValue
				+ ", groupAdmins=" + groupAdmins + ", groupCards=" + groupCards + "]";
	}

	 
}
