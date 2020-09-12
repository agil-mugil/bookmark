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
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.eng.stream.hackathon.bookmark.models.GroupAdmin;
import com.eng.stream.hackathon.bookmark.repositories.GroupAdminRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class GroupAdminServiceTests {

	@Autowired
	private GroupAdminService adminService;
	
	@MockBean
	private GroupAdminRepository adminRepository;

	private List<GroupAdmin> groupAdminList = new ArrayList<GroupAdmin>();
	@BeforeEach
	public void setUp() {
		GroupAdmin groupAdmin = new GroupAdmin("pmurugesan2012@gmail.com", "prabhu.murugesan", new Date(System.currentTimeMillis()));
		groupAdminList.add(groupAdmin);
	    Mockito.when(adminRepository.findAllByGroupId(any())).thenReturn(groupAdminList);
	    Mockito.when(adminRepository.countyByGroupId(any())).thenReturn(2);
	    Mockito.when(adminRepository.findAllByGroupId(any())).thenReturn(groupAdminList);
	}
	
	@Test
	@Order(1)
	void testCreateGroup() {
		GroupAdmin groupAdmin = new GroupAdmin("pmurugesan2012@gmail.com", "prabhu.murugesan", new Date(System.currentTimeMillis()));
	    doReturn(groupAdmin).when(adminRepository).save(any());
	    GroupAdmin created = adminService.addGroupAdmin(groupAdmin);
		assertNotNull(created);
		assertEquals("pmurugesan2012@gmail.com",created.getUserId());
	}

	@Test
	@Order(2)
	void testCountByGroupId() {
		Long groupId = 1L;
	    int adminsCount = adminService.getAdminsCountOfGroup(groupId);
	    assertEquals(2,adminsCount);
	    
	}

	@Test
	@Order(4)
	void testDeleteGroupAdmin() {
		adminService.deleteGroupAdmin(1l);
		groupAdminList.remove(0);
		assertEquals(0, groupAdminList.size());
	}
	
	@Test
	@Order(3)
	void testFindAllGroupAdmins() {
		Long groupId = 1L;
		List<GroupAdmin> groupAdmins = adminService.getGroupAdmins(groupId);
		assertThat(groupAdmins.size()).isGreaterThan(0);
	}

}