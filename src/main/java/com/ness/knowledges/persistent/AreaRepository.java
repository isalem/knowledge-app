package com.ness.knowledges.persistent;

import org.springframework.data.repository.CrudRepository;

import com.ness.knowledges.persistent.model.AreaEntity;

public interface AreaRepository extends CrudRepository<AreaEntity, Long> {
	AreaEntity findAreaByTitle(String title);
}
