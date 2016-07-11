package com.ness.app.service;

import java.util.List;
import java.util.Map;

import com.ness.app.domain.model.Knowledge;
import com.ness.app.domain.wraper.KnowledgeWithSelection;

public interface KnowledgeService {
	void save(Knowledge knowledge);
	Knowledge findKnowledgeByTitle(String title);
	Knowledge findKnowledgeById(Long id);
	List<Knowledge> findAll();
	Map<String, List<Knowledge>> findAllKnowledgesGropedByAreaTitle();
	List<KnowledgeWithSelection> findAllKnowledgesWithSelection();
	List<KnowledgeWithSelection> findAllKnowledgesSelectedForUser(String username);
}
