package com.skills.persistent;

import com.skills.domain.model.Area;
import org.springframework.data.repository.CrudRepository;

public interface AreaRepository extends CrudRepository<Area, Long> {
    Area findAreaByTitle(String title);
}
