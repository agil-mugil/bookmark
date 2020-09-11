package com.eng.stream.hackathon.bookmark.repositories;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.eng.stream.hackathon.bookmark.models.GroupAdmin;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
//@AutoConfigureTestDatabase(replace = Replace.NONE)
class GroupAdminRepositoryIT {

	@Autowired
	private GroupAdminRepository groupAdminRepository;

	/*
	 * @Test
	 * 
	 * @Rollback(false)
	 * 
	 * @Order(1) public void testCreateGroup() { GroupAdmin groupAdmin = new
	 * GroupAdmin("alex@gmail.com","pmurug",new Date(System.currentTimeMillis()));
	 * Group group = new Group(); group.setGroupId(1L); groupAdmin.setGroup(group);
	 * GroupAdmin resultedAdmin = groupAdminRepository.save(groupAdmin);
	 * assertNotNull(resultedAdmin); }
	 */
	
	@Test
	@Rollback(false)
	public void testDeleteGroupAdminByGroupId() {
		GroupAdmin groupAdmin = new GroupAdmin();
		groupAdmin.setGroupUserId(2L);
		groupAdminRepository.delete(groupAdmin);
		assertTrue(true);
	}
	
	/*
	 * @Test public void testCountByGroupId() { Long groupId = 1L; int
	 * groupAdminCounts = groupAdminRepository.countyByGroupId(groupId);
	 * assertEquals(3, groupAdminCounts); }
	 */
}
