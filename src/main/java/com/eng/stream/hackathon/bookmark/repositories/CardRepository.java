package com.eng.stream.hackathon.bookmark.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eng.stream.hackathon.bookmark.models.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

	public List<Card> findByPublish(String publish);

	public List<Card> findByPublishAndGroupId(String publish, Long groupId);

	public List<Card> findByGroupId(Long groupId);

	public void deleteByGroupId(Long groupId);

}
