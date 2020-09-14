package com.eng.stream.hackathon.bookmark.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eng.stream.hackathon.bookmark.models.Group;

public interface GroupRepository extends JpaRepository<Group, Long>{
	public List<Group> findByGroupValue(String groupValue);
	
	@Query(value = "select distinct grouptype from tgrouprefdataeng order by grouptype", nativeQuery = true)
	public String[] getGroupTypes();
	 
}
