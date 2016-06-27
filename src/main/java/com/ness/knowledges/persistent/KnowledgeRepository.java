package com.ness.knowledges.persistent;

import org.springframework.data.repository.CrudRepository;

import com.ness.knowledges.persistent.model.KnowledgeEntity;

public interface KnowledgeRepository extends CrudRepository<KnowledgeEntity, Long> {
	KnowledgeEntity findKnowledgeByTitle(String title);
}
