package com.eng.stream.hackathon.bookmark.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eng.stream.hackathon.bookmark.models.GroupAdmin;

public interface GroupAdminRepository extends JpaRepository<GroupAdmin, Long> {
    @Query("select g  from GroupAdmin g where g.group.groupId=:groupId")
    public List<GroupAdmin> findAllByGroupId(@Param("groupId") Long groupId);
}
