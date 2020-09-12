package com.eng.stream.hackathon.bookmark.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eng.stream.hackathon.bookmark.models.Group;
import com.eng.stream.hackathon.bookmark.repositories.GroupRepository;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupRepository groupRepository;
	
	@Override
	public Group createGroup(Group group) {
		return groupRepository.save(group);
	}

	@Override
	public List<Group> findAllGroups() {
		return groupRepository.findAll();
	}

	@Override
	public void deleteGroup(Long groupId) {
		groupRepository.deleteById(groupId);
	}

	@Override
	public List<Group> findByGroupValue(String groupValue) {
		return groupRepository.findByGroupValue(groupValue);
	}

} 
