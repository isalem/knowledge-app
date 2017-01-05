package com.skills.persistent;

import com.skills.domain.model.Skill;
import org.springframework.data.repository.CrudRepository;

public interface SkillRepository extends CrudRepository<Skill, Long> {
    Skill findSkillByTitle(String title);

    Skill findSkillBySkillId(Long skillId);
}
