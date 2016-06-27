package com.ness.knowledges.persistent;

import org.springframework.data.repository.CrudRepository;

import com.ness.knowledges.persistent.model.Area;

public interface AreaRepository extends CrudRepository<Area, Long> {
	Area findAreaByTitle(String title);
}
