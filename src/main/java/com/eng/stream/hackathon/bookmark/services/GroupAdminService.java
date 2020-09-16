package com.eng.stream.hackathon.bookmark.services;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.eng.stream.hackathon.bookmark.models.GroupAdmin;

public interface GroupAdminService {

	public GroupAdmin addGroupAdmin(GroupAdmin groupAdmin) throws SQLIntegrityConstraintViolationException;
	public void deleteGroupAdmin(long groupId);
	public List<GroupAdmin> getGroupAdmins(Long groupId);
}
