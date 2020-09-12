package com.eng.stream.hackathon.bookmark.services;

import java.util.List;

import com.eng.stream.hackathon.bookmark.models.GroupAdmin;

public interface GroupAdminService {

	public GroupAdmin addGroupAdmin(GroupAdmin groupAdmin);
	public void deleteGroupAdmin(long groupId);
	public int getAdminsCountOfGroup(Long groupId);
	public List<GroupAdmin> getGroupAdmins(Long groupId);
}