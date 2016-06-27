package com.ness.knowledges.persistent;

import org.springframework.data.repository.CrudRepository;

import com.ness.knowledges.persistent.model.Knowledge;

public interface KnowledgeRepository extends CrudRepository<Knowledge, Long> {
	Knowledge findKnowledgeByTitle(String title);
}
