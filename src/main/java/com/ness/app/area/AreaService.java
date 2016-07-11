package com.ness.app.area;

import java.util.List;

public interface AreaService {

	List<Area> findAllAreas();
	
	Area findAreaByTitle(String title);
	
	void save(Area area);

}
