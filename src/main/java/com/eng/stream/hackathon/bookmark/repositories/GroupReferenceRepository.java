package com.eng.stream.hackathon.bookmark.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eng.stream.hackathon.bookmark.models.GroupReference;
import com.eng.stream.hackathon.bookmark.models.GroupReferenceId;

public interface GroupReferenceRepository extends JpaRepository<GroupReference, GroupReferenceId> {
		@Query(value = "select distinct groupRef.groupType from GroupReference groupRef order by groupRef.groupType")
		public String[] groupTypes();
		@Query(value = "select * from tgrouprefdataeng gdata where gdata.GROUPTYPE='FT' AND gdata.groupvalue not in (select distinct g.GROUPVALUE from tbookmarkgroupseng g)", nativeQuery = true)
		public List<GroupReference> findByGroupType(String groupType);
		
}
