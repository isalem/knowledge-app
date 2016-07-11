package com.ness.app.knowledge;

import java.util.List;
import java.util.Map;

public interface KnowledgeService {
	void save(Knowledge knowledge);
	Knowledge findKnowledgeByTitle(String title);
	Knowledge findKnowledgeById(Long id);
	List<Knowledge> findAll();
	Map<String, List<Knowledge>> findAllKnowledgesGropedByAreaTitle();
	List<KnowledgeWithSelection> findAllKnowledgesWithSelection();
	List<KnowledgeWithSelection> findAllKnowledgesSelectedForUser(String username);
}
