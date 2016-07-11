package com.ness.app.persistent;

import org.springframework.data.repository.CrudRepository;

import com.ness.app.domain.model.Knowledge;

public interface KnowledgeRepository extends CrudRepository<Knowledge, Long> {
	Knowledge findKnowledgeByTitle(String title);
	Knowledge findKnowledgeByKnowledgeId(Long knowledgeId);
}
