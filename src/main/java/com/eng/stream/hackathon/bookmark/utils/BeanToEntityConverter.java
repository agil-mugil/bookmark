package com.eng.stream.hackathon.bookmark.utils;

import java.util.ArrayList;
import java.util.List;

import com.eng.stream.hackathon.bookmark.models.Card;
import com.eng.stream.hackathon.bookmark.models.CardBean;
import com.eng.stream.hackathon.bookmark.models.Group;
import com.eng.stream.hackathon.bookmark.models.GroupAdmin;
import com.eng.stream.hackathon.bookmark.models.GroupAdminBean;
import com.eng.stream.hackathon.bookmark.models.GroupBean;

public class BeanToEntityConverter {

	private BeanToEntityConverter() {
		
	}
	public static Group convertToEntity(GroupBean groupBean) {
			Group group = new Group(groupBean.getGroupType(), groupBean.getGroupValue());
			group.setCreatedBy(groupBean.getUsername());
			GroupAdmin admin = new GroupAdmin();
			admin.setUserId(group.getCreatedBy());
			admin.setCreatedBy(group.getCreatedBy());
			admin.setGroup(group);
			List<GroupAdmin> adminGroups = new ArrayList<>();
			adminGroups.add(admin);
			group.setGroupAdmins(adminGroups);
			return group;
	}
	
	public static GroupAdmin convertToEntity(GroupAdminBean groupAdminBean) {
		GroupAdmin admin = new GroupAdmin();
		admin.setUserId(groupAdminBean.getUserId());
		Group group = new Group();
		group.setGroupId(groupAdminBean.getGroupId());
		admin.setCreatedBy(groupAdminBean.getUsername());
		admin.setGroup(group);
		return admin;
}
	public static Card convertToEntity(CardBean cardBean) {
		Card card = new Card();
		card.setBookmarkUrl(cardBean.getBookmarkUrl());
		card.setCardTitle(cardBean.getCardTitle());
		card.setCardDesc(cardBean.getCardTitle());
		card.setImageUrl(cardBean.getImageUrl());
		card.setShortUrl(cardBean.getShortUrl());
		card.setGroupId(cardBean.getGroupId());
		card.setPublish(cardBean.getPublish());
		card.setCreatedBy(cardBean.getUsername());
		return card;
	}
}
