package com.ness.app.service;

import java.util.List;

import com.ness.app.domain.model.Area;
import com.ness.app.web.dto.AutocompleteDto;

public interface AreaService {
	public List<Area> findAllAreas();	
	public Area findAreaByTitle(String title);	
	public void save(Area area);
	public List<AutocompleteDto> getAutocomplete();
}
