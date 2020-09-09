package com.eng.stream.hackathon.bookmark.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.eng.stream.hackathon.bookmark.models.Group;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
//@AutoConfigureTestDatabase(replace = Replace.NONE)
public class GroupRespositoryIT {

	@Autowired
	private GroupRepository  groupRepository;
	
	@Test
	@Rollback(false)
	@Order(1)
	public void testCreateGroup() {
		Group group = new Group("FT", "SNO", "pmurugesan2012@gmail.com",new Date(System.currentTimeMillis()));
		Group saved =  groupRepository.save(group);
		assertNotNull(saved);
	}
	
	@Test
	@Order(2)
	public void testFindGroupByValueExists() {
		String groupValue = "SNO";
		List<Group> groups = groupRepository.findByGroupValue(groupValue);
		assertEquals(1,groups.size());
		 assertThat(groups).extracting(Group::getGroupValue).contains(groupValue);
	}
	
	@Test
	@Order(3)
	public void testFindGroupByValueNotExists() {
		String groupValue = "ENO";
		List<Group> groups = groupRepository.findByGroupValue(groupValue);
		assertEquals(0,groups.size());
	}
	
	@Test
	@Order(4)
	public void testListGroups() {
		List<Group> groups = groupRepository.findAll();
		assertThat(groups.size()).isGreaterThan(0);
	}
	
	@Test
	@Rollback(false)
	@Order(7)
	public void testDeleteProduct() {
		String groupValue = "SNO";
		List<Group> groupList = groupRepository.findByGroupValue(groupValue);
		Optional<Group> existingGroup = groupList.stream()
	      .filter(Group -> groupValue.contains(groupValue)).findFirst();
		
		Long groupId =existingGroup.get().getGroupId() ;
		boolean dataPresentBeforeDelete=  groupRepository.findById(groupId).isPresent();
		groupRepository.deleteById(groupId);
		boolean dataNotPresentAfterDelete = groupRepository.findById(groupId).isPresent();
		assertTrue(dataPresentBeforeDelete);
		assertFalse(dataNotPresentAfterDelete);
	}
	
	@Test
	@Rollback(false)
	@Order(5)
	public void testSoftDelete() {
		String groupValue = "SNO";
		List<Group> groupList = groupRepository.findByGroupValue(groupValue);
		Group existingGroup = groupList.stream()
	      .filter(Group -> groupValue.contains(groupValue)).findFirst().get();
		
		existingGroup.setEraser("pmurugesan2012@gmail.com");
		Group deleteGroup = groupRepository.save(existingGroup);
		assertEquals("pmurugesan2012@gmail.com", deleteGroup.getEraser());
	}

	@Test
	@Order(6)
	public void testListActiveGroups() {
		List<Group> groups = groupRepository.findByEraserIsNull();
		assertThat(groups.size()).isEqualTo(0);
	}
}
