package com.skills.service;

import java.util.List;

import com.skills.domain.model.Area;
import com.skills.web.dto.AutocompleteDto;

public interface AreaService {
	public List<Area> findAllAreas();	
	public Area findAreaByTitle(String title);	
	public void save(Area area);
	public List<AutocompleteDto> getAutocomplete();
}
