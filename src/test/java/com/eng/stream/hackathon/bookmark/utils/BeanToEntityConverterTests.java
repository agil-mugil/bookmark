package com.eng.stream.hackathon.bookmark.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.eng.stream.hackathon.bookmark.models.Group;
import com.eng.stream.hackathon.bookmark.models.GroupAdmin;
import com.eng.stream.hackathon.bookmark.models.GroupAdminBean;
import com.eng.stream.hackathon.bookmark.models.GroupBean;

public class BeanToEntityConverterTests {

	@Test
	public void testconvertToGroupEntity() {
		GroupBean groupBean = new GroupBean();
		groupBean.setCreator("prabhu");
		groupBean.setGroupType("TRIBE");
		groupBean.setGroupValue("RCM");
		Group group =BeanToEntityConverter.convertToEntity(groupBean);
		assertNotNull(group);
		assertEquals(group.getCreator(), groupBean.getCreator());
		assertEquals(group.getGroupType(), groupBean.getGroupType());
		assertEquals(group.getGroupValue(), groupBean.getGroupValue());
		assertEquals(1,group.getGroupAdmins().size());
	}
	
	@Test
	public void testconvertToGroupAdminEntity() {
		GroupAdminBean groupAdminBean = new GroupAdminBean();
		groupAdminBean.setCreator("prabhu");
		groupAdminBean.setGroupId(10L);
		groupAdminBean.setUserId("pmurugesan2012@gmail.com");
		GroupAdmin groupAdmin =BeanToEntityConverter.convertToEntity(groupAdminBean);
		assertNotNull(groupAdmin);
		assertEquals(groupAdmin.getCreator(), groupAdminBean.getCreator());
		assertEquals(groupAdmin.getUserId(), groupAdminBean.getUserId());
		assertEquals(groupAdmin.getGroup().getGroupId(), groupAdminBean.getGroupId());
	}
	
}
