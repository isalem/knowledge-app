package com.ness.app.service;

import java.util.List;
import java.util.Map;

import com.ness.app.domain.model.Skill;
import com.ness.app.domain.wrapper.SkillWithSelection;

public interface SkillService {
	void save(Skill skill);
	Skill findSkillByTitle(String title);
	Skill findSkillById(Long id);
	List<Skill> findAll();
	Map<String, List<Skill>> findAllSkillsGropedByAreaTitle();
	List<SkillWithSelection> findAllSkillsWithSelection();
	List<SkillWithSelection> findAllSkillsSelectedForUser(String username);
}
