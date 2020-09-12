package com.eng.stream.hackathon.bookmark.utils;

import java.sql.Date;

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
}
