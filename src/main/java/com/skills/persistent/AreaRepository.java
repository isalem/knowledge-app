package com.skills.persistent;

import org.springframework.data.repository.CrudRepository;

import com.skills.domain.model.Area;

public interface AreaRepository extends CrudRepository<Area, Long> {
	Area findAreaByTitle(String title);
}
