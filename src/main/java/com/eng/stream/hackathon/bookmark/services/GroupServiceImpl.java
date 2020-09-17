package com.eng.stream.hackathon.bookmark.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eng.stream.hackathon.bookmark.EntityNotFoundException;
import com.eng.stream.hackathon.bookmark.models.Group;
import com.eng.stream.hackathon.bookmark.models.GroupReference;
import com.eng.stream.hackathon.bookmark.repositories.GroupReferenceRepository;
import com.eng.stream.hackathon.bookmark.repositories.GroupRepository;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private GroupReferenceRepository groupReferenceRepo;
	
	@Override
	public Group createGroup(Group group) {
		return groupRepository.save(group);
	}

	@Override
	public List<Group> findAllGroups() {
		List<Group> groups = groupRepository.findAll();
		if(groups.isEmpty()) {
			throw new EntityNotFoundException(Group.class);
		}
		return groups;
	}

	@Override
	public void deleteGroup(Long groupId) {
		groupRepository.deleteById(groupId);
	}

	@Override
	public List<Group> findByGroupValue(String groupValue) {
		List<Group> groups = groupRepository.findByGroupValue(groupValue);
		if(groups.isEmpty()) {
			throw new EntityNotFoundException(Group.class, "Group By", groupValue);
		}
		return groups;
	}

	@Override
	public String[] groupTypes() {
		return groupRepository.getGroupTypes();
	}

	@Override
	public List<GroupReference> groupValues(String groupType) {
		return groupReferenceRepo.findByGroupType(groupType);
	}

} 
