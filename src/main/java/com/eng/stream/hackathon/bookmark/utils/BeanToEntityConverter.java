package com.eng.stream.hackathon.bookmark.utils;

import java.sql.Date;

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
			Group group = new Group(groupBean.getGroupType(), groupBean.getGroupValue(), 
					groupBean.getCreator(),new Date(System.currentTimeMillis()));
			
			GroupAdmin admin = new GroupAdmin(group.getCreator(),group.getCreator()
					,group.getCreatedDate());
			
			admin.setGroup(group);
			
			group.getGroupAdmins().add(admin);
			return group;
	}
	
	public static GroupAdmin convertToEntity(GroupAdminBean groupAdminBean) {
		GroupAdmin admin = new GroupAdmin(groupAdminBean.getUserId(), groupAdminBean.getCreator()
				,new Date(System.currentTimeMillis()));
		
		Group group = new Group();
		group.setGroupId(groupAdminBean.getGroupId());
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
		card.setCreator(cardBean.getCreator());
		card.setCreatedDate(new Date(System.currentTimeMillis()));
		return card;
	}
}
