package com.eng.stream.hackathon.bookmark.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eng.stream.hackathon.bookmark.models.GroupAdmin;

public interface GroupAdminRepository extends JpaRepository<GroupAdmin, Long> {
    @Query("select g  from GroupAdmin g where g.group.groupId=:groupId")
    public List<GroupAdmin> findAllByGroupId(@Param("groupId") Long groupId);
    @Query(value = "select count(*) from tgroupadminseng g where g.groupId=:groupId and g.userid=:userId", nativeQuery = true)
    public int findCountByGroupAndUserId(@Param("groupId")Long groupId,  @Param("userId")String userId);
     
    
}
