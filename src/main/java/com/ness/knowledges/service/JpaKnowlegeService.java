package com.ness.knowledges.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ness.knowledges.domain.Knowledge;
import com.ness.knowledges.persistent.KnowledgeRepository;
import com.ness.knowledges.service.interfaces.KnowledgeService;

@Service
public class JpaKnowlegeService implements KnowledgeService {

	@Autowired
	private KnowledgeRepository knowledgeRepository;
	
	@Override
	public void save(Knowledge knowledge) {
		knowledgeRepository.save(knowledge);
	}

	@Override
	public Knowledge findKnowledgeByTitle(String title) {
		return knowledgeRepository.findKnowledgeByTitle(title);
	}

}
