package com.ness.app.persistent;

import org.springframework.data.repository.CrudRepository;

import com.ness.app.domain.model.Skill;

public interface SkillRepository extends CrudRepository<Skill, Long> {
	Skill findSkillByTitle(String title);
	Skill findSkillBySkillId(Long skillId);
}
