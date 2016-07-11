package com.ness.app.area;

import org.springframework.data.repository.CrudRepository;

public interface AreaRepository extends CrudRepository<Area, Long> {
	Area findAreaByTitle(String title);
}
