package com.ness.app.knowledge;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.ness.app.user.UserService;

@Service
@Transactional(readOnly = true)
public class JpaKnowlegeService implements KnowledgeService {

	@Autowired
	private KnowledgeRepository knowledgeRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	@Transactional(readOnly = false)
	@CacheEvict(cacheNames = { "knowledge", "area" }, allEntries = true)
	public void save(Knowledge knowledge) {
		knowledgeRepository.save(knowledge);
	}

	@Override
	@Cacheable("knowledge")
	public Knowledge findKnowledgeByTitle(String title) {
		return knowledgeRepository.findKnowledgeByTitle(title);
	}

	@Override
	public Map<String, List<Knowledge>> findAllKnowledgesGropedByAreaTitle() {
		Iterable<Knowledge> knowledges = knowledgeRepository.findAll();
		return StreamSupport.stream(knowledges.spliterator(), false)
				.collect(Collectors.groupingBy(k -> k.getArea().getTitle()));
	}

	@Override
	@Cacheable("knowledge")
	public List<Knowledge> findAll() {
		return Lists.newArrayList(knowledgeRepository.findAll());
	}

	@Override
	@Cacheable("knowledge")
	public Knowledge findKnowledgeById(Long id) {
		return knowledgeRepository.findKnowledgeByKnowledgeId(id);
	}

	@Override
	@Cacheable("knowledge")
	public List<KnowledgeWithSelection> findAllKnowledgesWithSelection() {
		Iterable<Knowledge> allKnowledges = knowledgeRepository.findAll();
		return StreamSupport.stream(allKnowledges.spliterator(), false)
				.map(k -> new KnowledgeWithSelection(k, false))
				.collect(Collectors.toList());
	}

	@Override
	@Cacheable("knowledge")
	public List<KnowledgeWithSelection> findAllKnowledgesSelectedForUser(String username) {
		List<KnowledgeWithSelection> usersKnowledgesWithSelections = userService.findUsersKnowledgesWithSelection(username);
		List<KnowledgeWithSelection> allKnowledgesWithSelection = findAllKnowledgesWithSelection();
		
		return Stream.concat(usersKnowledgesWithSelections.stream(), allKnowledgesWithSelection.stream())
			.filter(new TreeSet<>(KnowledgeWithSelection.getComparatorWithoutSelectedField())::add)
			.collect(Collectors.toList());
	}

}
