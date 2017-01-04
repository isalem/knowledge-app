package com.skills.service;

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
import com.skills.domain.model.Skill;
import com.skills.domain.wrapper.SkillWithSelection;
import com.skills.persistent.SkillRepository;

@Service
@Transactional(readOnly = true)
public class SkillServiceImpl implements SkillService {

	@Autowired
	private SkillRepository skillRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	@Transactional(readOnly = false)
	@CacheEvict(cacheNames = { "skill", "area" }, allEntries = true)
	public void save(Skill skill) {
		skillRepository.save(skill);
	}

	@Override
	@Cacheable("skill")
	public Skill findSkillByTitle(String title) {
		return skillRepository.findSkillByTitle(title);
	}

	@Override
	public Map<String, List<Skill>> findAllSkillsGropedByAreaTitle() {
		Iterable<Skill> skills = skillRepository.findAll();
		return StreamSupport.stream(skills.spliterator(), false)
				.collect(Collectors.groupingBy(k -> k.getArea().getTitle()));
	}

	@Override
	@Cacheable("skill")
	public List<Skill> findAll() {
		return Lists.newArrayList(skillRepository.findAll());
	}

	@Override
	@Cacheable("skill")
	public Skill findSkillById(Long id) {
		return skillRepository.findSkillBySkillId(id);
	}

	@Override
	@Cacheable("skill")
	public List<SkillWithSelection> findAllSkillsWithSelection() {
		Iterable<Skill> allSkills = skillRepository.findAll();
		return StreamSupport.stream(allSkills.spliterator(), false)
				.map(s -> new SkillWithSelection(s, false))
				.collect(Collectors.toList());
	}

	@Override
	@Cacheable("skill")
	public List<SkillWithSelection> findAllSkillsSelectedForUser(String username) {
		List<SkillWithSelection> usersSkillsWithSelections = userService.findUsersSkillsWithSelection(username);
		List<SkillWithSelection> allSkillsWithSelection = findAllSkillsWithSelection();
		
		return Stream.concat(usersSkillsWithSelections.stream(), allSkillsWithSelection.stream())
			.filter(new TreeSet<>(SkillWithSelection.getComparatorWithoutSelectedField())::add)
			.collect(Collectors.toList());
	}

}
