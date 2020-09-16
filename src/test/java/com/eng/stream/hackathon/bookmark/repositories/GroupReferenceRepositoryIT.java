package com.eng.stream.hackathon.bookmark.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.eng.stream.hackathon.bookmark.models.GroupReference;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
//@AutoConfigureTestDatabase(replace = Replace.NONE)
public class GroupReferenceRepositoryIT {

	@Autowired
	private GroupReferenceRepository groupReferenceRepository;
	
	@Test
	public void testCreateGroupRefernce() {
		GroupReference groupRefernce = new GroupReference("FT", "SCOW", "Onboarding team");
		groupRefernce.setCreatedBy("prabhu.murugesan");
		GroupReference saved = groupReferenceRepository.save(groupRefernce);
		assertNotNull(saved);
	}
	
	@Test
	public void testGroupTypes() {
		String groupTypes[] = groupReferenceRepository.groupTypes();
		assertThat(groupTypes.length).isEqualTo(3);
	}
	
	@Test
	public void testFindByGroupType() {
		String groupType = "FT";
		List<GroupReference> groupRefrences = groupReferenceRepository.findByGroupType(groupType);
		assertThat(groupRefrences.size()).isEqualTo(5);
		for (GroupReference groupReference : groupRefrences) {
			System.out.println(groupReference.getGroupValue());
		}
	}
	
}
