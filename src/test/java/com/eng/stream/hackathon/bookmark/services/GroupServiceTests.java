package com.eng.stream.hackathon.bookmark.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.eng.stream.hackathon.bookmark.models.Group;
import com.eng.stream.hackathon.bookmark.repositories.GroupRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class GroupServiceTests {

	@Autowired
	private GroupServiceImpl groupService;
	
	@MockBean
	private GroupRepository  groupRepository;
	
	private List<Group> groupList = new ArrayList<Group>();
	@BeforeEach
	public void setUp() {
		Group group = new Group("FT", "SNO", "pmurugesan2012@gmail.com",new Date(System.currentTimeMillis()));
		groupList.add(group);
	    Mockito.when(groupRepository.findByGroupValue("SNO")).thenReturn(groupList);
	    Mockito.when(groupRepository.findAll()).thenReturn(groupList);
	    Mockito.when(groupRepository.findByEraserIsNull()).thenReturn(groupList);
	}
	
	@Test
	@Order(1)
	void testCreateGroup() {
		Group group = new Group("FT", "SNO", "pmurugesan2012@gmail.com",new Date(System.currentTimeMillis()));
	    doReturn(group).when(groupRepository).save(any());
		Group created = groupService.createGroup(group);
		assertNotNull(created);
		assertEquals("SNO",created.getGroupValue());
	}

	@Test
	@Order(2)
	void testFindAllGroups() {
		List<Group> groupList = groupService.findAllGroups();
		assertThat(groupList.size()).isGreaterThan(0);
	}

	@Test
	@Order(3)
	void testFinaAllActiveGroups() {
		List<Group> groupList = groupService.finaAllActiveGroups();
		assertThat(groupList.size()).isGreaterThan(0);
	}

	@Test
	@Order(6)
	void testDeleteGroup() {
		groupService.deleteGroup(1l);
		groupList.remove(0);
		assertEquals(0, groupList.size());
	}

	@Test
	@Order(5)
	void testFindByGroupValue() {
		String groupValue = "SNO";
		List<Group> groupList = groupService.findByGroupValue(groupValue);
		assertThat(groupList.size()).isGreaterThan(0);
	}

}
