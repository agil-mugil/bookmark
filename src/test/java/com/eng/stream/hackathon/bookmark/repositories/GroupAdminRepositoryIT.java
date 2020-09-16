package com.eng.stream.hackathon.bookmark.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
class GroupAdminRepositoryIT {

	@Autowired
	private GroupAdminRepository groupAdminRepository;
	
	@Autowired
	private GroupRepository groupRepo;

	
	@Test
	@Rollback(false)
	@Order(1)
	public void testCreateGroup() {
		Group group = new Group("FT", "ENO");
		group.setCreatedBy("anderson@gmail.com");
		Group result = groupRepo.save(group);
		GroupAdmin groupAdmin = new GroupAdmin();
		groupAdmin.setUserId("anderson@gmail.com");
		groupAdmin.setCreatedBy("anderson@gmail.com");
		groupAdmin.setGroup(result);
		GroupAdmin resultedAdmin = groupAdminRepository.save(groupAdmin);
		assertNotNull(resultedAdmin);
	}
	
	@Test
	@Order(2)
	public void testFindAdminsByGroupAndUser() {
		int adminCount = groupAdminRepository.findCountByGroupAndUserId(1L, "anderson@gmail.com");
		assertThat(adminCount).isEqualTo(1);
	}
	@Test
	@Order(3)
	public void testFindAdminsByGroupAndUserNotFound() {
		int adminCount = groupAdminRepository.findCountByGroupAndUserId(1L, "anderson1@gmail.com");
		assertThat(adminCount).isEqualTo(0);
	}
	
	@Test
	@Rollback(false)
	@Order(4)
	public void testDeleteGroupAdminByGroupId() {
		Long groupUserId = 1L;
		assertNotNull(groupAdminRepository.findById(groupUserId));
		groupAdminRepository.deleteById(groupUserId);
		assertTrue(groupAdminRepository.findById(groupUserId).isEmpty());
	}
	
	 
}
