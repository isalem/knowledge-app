package com.ness.knowledges.service.interfaces;

import com.ness.knowledges.domain.Knowledge;

public interface KnowledgeService {
	void save(Knowledge knowledge);
	Knowledge findKnowledgeByTitle(String title);
}
