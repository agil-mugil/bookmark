package com.eng.stream.hackathon.bookmark.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;
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
		Group group = new Group("FT", "SKT", "john@gmail.com", new Date(System.currentTimeMillis()));
		Group result = groupRepo.save(group);
		GroupAdmin groupAdmin = new GroupAdmin("anderson@gmail.com", "pmurug", new Date(System.currentTimeMillis()));
		groupAdmin.setGroup(result);
		GroupAdmin resultedAdmin = groupAdminRepository.save(groupAdmin);
		assertNotNull(resultedAdmin);
	}
	
	@Test
	@Rollback(false)
	@Order(3)
	public void testDeleteGroupAdminByGroupId() {
		GroupAdmin groupAdmin = new GroupAdmin();
		groupAdmin.setGroupUserId(2L);
		groupAdminRepository.delete(groupAdmin);
		assertTrue(true);
	}
	
	
	@Test
	@Order(2)
	public void testCountByGroupId() {
		Long groupId = 1L;
		int groupAdminCounts = groupAdminRepository.countyByGroupId(groupId);
		assertThat(groupAdminCounts).isGreaterThan(0);
	}
	 
}
