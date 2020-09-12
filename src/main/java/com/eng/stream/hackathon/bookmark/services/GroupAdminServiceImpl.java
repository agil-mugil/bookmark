package com.eng.stream.hackathon.bookmark.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eng.stream.hackathon.bookmark.EntityNotFoundException;
import com.eng.stream.hackathon.bookmark.models.GroupAdmin;
import com.eng.stream.hackathon.bookmark.repositories.GroupAdminRepository;

@Service
public class GroupAdminServiceImpl implements GroupAdminService {

	@Autowired
	private GroupAdminRepository groupAdminRepository;
	@Override
	public GroupAdmin addGroupAdmin(GroupAdmin groupAdmin) {
		return groupAdminRepository.save(groupAdmin);
	}

	@Override
	public void deleteGroupAdmin(long groupId) {
		groupAdminRepository.deleteById(groupId);
	}

	@Override
	public int getAdminsCountOfGroup(Long groupId) {
		return groupAdminRepository.countyByGroupId(groupId);
	}

	@Override
	public List<GroupAdmin> getGroupAdmins(Long groupId) {
		List<GroupAdmin> groupAdmins = groupAdminRepository.findAllByGroupId(groupId);
		if(groupAdmins.isEmpty()) {
			throw new EntityNotFoundException(GroupAdmin.class, "Group Id", groupId.toString());
		}
		return groupAdmins;
	}

}
