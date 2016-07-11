package com.ness.app.persistent;

import org.springframework.data.repository.CrudRepository;

import com.ness.app.domain.model.Area;

public interface AreaRepository extends CrudRepository<Area, Long> {
	Area findAreaByTitle(String title);
}
