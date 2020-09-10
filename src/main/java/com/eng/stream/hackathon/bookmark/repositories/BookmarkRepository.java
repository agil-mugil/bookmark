package com.eng.stream.hackathon.bookmark.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eng.stream.hackathon.bookmark.models.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long>{

}
