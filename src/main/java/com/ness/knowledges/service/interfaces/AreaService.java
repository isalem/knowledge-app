package com.ness.knowledges.service.interfaces;

import com.ness.knowledges.domain.Area;

public interface AreaService {

	Iterable<Area> findAllAreas();
	
	Area findAreaByTitle(String title);
	
	void save(Area area);

}
