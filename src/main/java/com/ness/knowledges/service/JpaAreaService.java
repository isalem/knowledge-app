package com.ness.knowledges.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ness.knowledges.domain.Area;
import com.ness.knowledges.persistent.AreaRepository;
import com.ness.knowledges.service.interfaces.AreaService;

@Service
public class JpaAreaService implements AreaService {

	@Autowired
	private AreaRepository areaRepository;
	
	@Override
	public Iterable<Area> findAllAreas() {
		return areaRepository.findAll();
	}

	@Override
	public Area findAreaByTitle(String title) {
		return areaRepository.findAreaByTitle(title);
	}

	@Override
	public void save(Area area) {
		areaRepository.save(area);
		
	}

}
