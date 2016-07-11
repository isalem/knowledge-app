package com.ness.app.service;

import java.util.List;

import com.ness.app.domain.model.Area;

public interface AreaService {

	List<Area> findAllAreas();
	
	Area findAreaByTitle(String title);
	
	void save(Area area);

}
