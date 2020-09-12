package com.eng.stream.hackathon.bookmark.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.ArrayList;
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
import com.eng.stream.hackathon.bookmark.models.GroupAdmin;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
//@AutoConfigureTestDatabase(replace = Replace.NONE)
public class GroupRespositoryIT {

	@Autowired
	private GroupRepository  groupRepository;
	
	@Autowired
	private CardRepository cardRepository;
	
	@Test
	@Rollback(false)
	@Order(1)
	public void testCreateGroup() {
		Group group = new Group("FT", "SNO", "alex2012@gmail.com",new Date(System.currentTimeMillis()));
		List<GroupAdmin> groupAdmins = new ArrayList<GroupAdmin>();
		GroupAdmin groupAdmin = new GroupAdmin();
		groupAdmin.setUserId(group.getCreator());
		groupAdmin.setCreator("pmruugesan2012@gmail.com");
		groupAdmin.setCreatedDate(group.getCreatedDate());
		groupAdmin.setGroup(group);
		groupAdmins.add(groupAdmin);
		group.setGroupAdmins(groupAdmins);
		Group saved =  groupRepository.save(group);
		
		assertNotNull(saved);
		System.out.println( saved.toString());
		GroupAdmin groupAdmin2 = new GroupAdmin();
		groupAdmin2.setUserId("james@gmail.com");
		groupAdmin2.setCreator("pmruugesan2012@gmail.com");
		groupAdmin2.setCreatedDate(new Date(System.currentTimeMillis()));
		groupAdmin2.setGroup(saved);
		saved.getGroupAdmins().add(groupAdmin2);
		saved = groupRepository.save(saved);
		assertEquals(2, saved.getGroupAdmins().size());
		System.out.println(saved.toString());
		
		saved.getGroupAdmins().remove(0);
		saved= groupRepository.save(saved);
		assertEquals(1, saved.getGroupAdmins().size());
		System.out.println(saved.toString());
	}
	
	@Test
	@Order(2)
	public void testFindGroupByValueExists() {
		String groupValue = "SNO";
		List<Group> groups = groupRepository.findByGroupValue(groupValue);
		assertThat(groups.size()).isGreaterThan(0);
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
		for (Group group : groups) {
			System.out.println(group.toString());
		}
	}
	
	@Test
	@Rollback(false)
	@Order(7)
	public void testDeleteGroup() {
		String groupValue = "SNO";
		List<Group> groupList = groupRepository.findByGroupValue(groupValue);
		Optional<Group> existingGroup = groupList.stream()
	      .filter(group -> groupValue.equalsIgnoreCase(group.getGroupValue())).findFirst();
		
		assertTrue(existingGroup.isPresent());
		Long groupId =existingGroup.isPresent()? existingGroup.get().getGroupId():0 ;
		boolean dataPresentBeforeDelete=  groupRepository.findById(groupId).isPresent();
		cardRepository.deleteByGroupId(groupId);
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
		Optional<Group> existingGroup = groupList.stream()
			      .filter(group -> groupValue.equalsIgnoreCase(group.getGroupValue())).findFirst();
		
		if(existingGroup.isPresent()) {
			existingGroup.get().setEraser("pmurugesan@gmail.com");
			Group deleteGroup = groupRepository.save(existingGroup.get());
			assertEquals("pmurugesan@gmail.com", deleteGroup.getEraser());
		}
	}

	@Test
	@Order(6)
	public void testListActiveGroups() {
		List<Group> groups = groupRepository.findByEraserIsNull();
		assertThat(groups.size()).isEqualTo(1);
	}
}
