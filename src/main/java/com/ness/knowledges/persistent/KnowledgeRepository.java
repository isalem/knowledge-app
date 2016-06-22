package com.ness.knowledges.persistent;

import org.springframework.data.repository.CrudRepository;

import com.ness.knowledges.domain.Knowledge;

public interface KnowledgeRepository extends CrudRepository<Knowledge, Long> {
	Knowledge findKnowledgeByTitle(String title);
}
