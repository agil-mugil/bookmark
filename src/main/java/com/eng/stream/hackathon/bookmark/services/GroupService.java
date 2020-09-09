package com.eng.stream.hackathon.bookmark.services;

import java.util.List;

import com.eng.stream.hackathon.bookmark.models.Group;

public interface GroupService {

	public Group createGroup(Group group);
	public List<Group> findAllGroups();
	public List<Group> finaAllActiveGroups();
	public void deleteGroup(Long groupId);
	public List<Group> findByGroupValue(String groupValue);
	
	
}
