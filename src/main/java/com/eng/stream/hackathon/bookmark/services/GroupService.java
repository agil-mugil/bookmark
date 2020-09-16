package com.eng.stream.hackathon.bookmark.services;

import java.util.List;

import com.eng.stream.hackathon.bookmark.models.Group;
import com.eng.stream.hackathon.bookmark.models.GroupReference;

public interface GroupService {

	public Group createGroup(Group group);
	public List<Group> findAllGroups(String userId);
	public void deleteGroup(Long groupId);
	public List<Group> findByGroupValue(String groupValue);
	public String[] groupTypes();
	public List<GroupReference> groupValues(String groupType);
	
}
