package com.eng.stream.hackathon.bookmark.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.eng.stream.hackathon.bookmark.EntityNotFoundException;
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
		Group group = new Group("FT", "SNO");
		groupList.add(group);
	    Mockito.when(groupRepository.findByGroupValue("SNO")).thenReturn(groupList);
	    Mockito.when(groupRepository.findByGroupAdminsUserId(any())).thenReturn(groupList);
	}
	
	@Test
	@Order(1)
	void testCreateGroup() {
		Group group = new Group("FT", "SNO");
	    doReturn(group).when(groupRepository).save(any());
		Group created = groupService.createGroup(group);
		assertNotNull(created);
		assertEquals("SNO",created.getGroupValue());
	}

	@Test
	@Order(2)
	void testFindAllGroups() {
		List<Group> groupList = groupService.findAllGroups(any());
		assertThat(groupList.size()).isGreaterThan(0);
	}

	@Test
	@Order(5)
	void testDeleteGroup() {
		groupService.deleteGroup(1l);
		groupList.remove(0);
		assertEquals(0, groupList.size());
	}

	@Test
	@Order(4)
	void testFindByGroupValue() {
		String groupValue = "SNO";
		List<Group> groupList = groupService.findByGroupValue(groupValue);
		assertThat(groupList.size()).isGreaterThan(0);
	}
	
	@Test
	void testFindGroupAdminsWithNoEntityFound() {
		try {
			List<Group> groupList = new ArrayList<Group>();
			doReturn(groupList).when(groupRepository).findByGroupAdminsUserId(any());
			groupService.findAllGroups(any());
			fail(" EntityNotFoundException exception not thrown");
		} catch (EntityNotFoundException e) {
			assertThat(e.getMessage()).isEqualTo("Group was not found for parameters {}");
			System.out.println(e.getCause());
		}
	}

	@Test
	void testPublishedCardsWithNoEntityFound() {
		try {
			List<Group> groupList = new ArrayList<Group>();
			doReturn(groupList).when(groupRepository).findByGroupValue(any());
			groupService.findByGroupValue("SNO");
			fail(" EntityNotFoundException exception not thrown");
		} catch (EntityNotFoundException e) {
			assertThat(e.getMessage()).isEqualTo("Group was not found for parameters {Group By=SNO}");
			System.out.println(e.getCause());
		}
	}
}
