package com.ness.app.knowledge;

import org.springframework.data.repository.CrudRepository;

public interface KnowledgeRepository extends CrudRepository<Knowledge, Long> {
	Knowledge findKnowledgeByTitle(String title);
	Knowledge findKnowledgeByKnowledgeId(Long knowledgeId);
}
