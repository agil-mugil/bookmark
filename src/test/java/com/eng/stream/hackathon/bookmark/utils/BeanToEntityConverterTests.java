package com.eng.stream.hackathon.bookmark.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.eng.stream.hackathon.bookmark.models.Card;
import com.eng.stream.hackathon.bookmark.models.CardBean;
import com.eng.stream.hackathon.bookmark.models.Group;
import com.eng.stream.hackathon.bookmark.models.GroupAdmin;
import com.eng.stream.hackathon.bookmark.models.GroupAdminBean;
import com.eng.stream.hackathon.bookmark.models.GroupBean;

public class BeanToEntityConverterTests {

	@Test
	public void testconvertToGroupEntity() {
		GroupBean groupBean = new GroupBean();
		groupBean.setGroupType("TRIBE");
		groupBean.setGroupValue("RCM");
		Group group =BeanToEntityConverter.convertToEntity(groupBean);
		assertNotNull(group);
		assertEquals(group.getGroupType(), groupBean.getGroupType());
		assertEquals(group.getGroupValue(), groupBean.getGroupValue());
		assertEquals(1,group.getGroupAdmins().size());
	}
	
	@Test
	public void testconvertToGroupAdminEntity() {
		GroupAdminBean groupAdminBean = new GroupAdminBean();
		groupAdminBean.setGroupId(10L);
		groupAdminBean.setUserId("pmurugesan2012@gmail.com");
		GroupAdmin groupAdmin =BeanToEntityConverter.convertToEntity(groupAdminBean);
		assertNotNull(groupAdmin);
		assertEquals(groupAdmin.getUserId(), groupAdminBean.getUserId());
		assertEquals(groupAdmin.getGroup().getGroupId(), groupAdminBean.getGroupId());
	}
	
	@Test
	public void testconvertToCardEntity() {
		
		CardBean  cardBean  = new CardBean();
		cardBean.setBookmarkUrl("https://gitter.im/engineering-stream-hackathon/community#");
		cardBean.setCardTitle("Spring");
		cardBean.setImageUrl("image/spring.img");
		cardBean.setShortUrl("http://localhost:8080/bookmark/engineering-stream-hackathon");
		cardBean.setGroupId(10L);
		cardBean.setPublish("Y");
		
		Card card =BeanToEntityConverter.convertToEntity(cardBean);
		
		assertNotNull(card);
		assertEquals(cardBean.getBookmarkUrl(),card.getBookmarkUrl());
		assertEquals(cardBean.getCardTitle(),card.getCardTitle());
		assertEquals(cardBean.getImageUrl(),card.getImageUrl());
		assertEquals(cardBean.getShortUrl(),card.getShortUrl());
		assertEquals(cardBean.getGroupId(),card.getGroupId());
		assertEquals(cardBean.getPublish(), card.getPublish());
	}
	
}
