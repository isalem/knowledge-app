package com.skills.persistent;

import org.springframework.data.repository.CrudRepository;

import com.skills.domain.model.Skill;

public interface SkillRepository extends CrudRepository<Skill, Long> {
	Skill findSkillByTitle(String title);
	Skill findSkillBySkillId(Long skillId);
}
