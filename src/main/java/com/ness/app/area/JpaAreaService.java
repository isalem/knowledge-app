package com.ness.app.area;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

@Service
@Transactional(readOnly = true)
public class JpaAreaService implements AreaService {

	@Autowired
	private AreaRepository areaRepository;
	
	@Override
	@Transactional(readOnly = false)
	@CacheEvict(cacheNames = "area", allEntries = true)
	public void save(Area area) {
		areaRepository.save(area);
		
	}

	@Override
	@Cacheable("area")
	public List<Area> findAllAreas() {
		return Lists.newArrayList(areaRepository.findAll());
	}

	@Override
	@Cacheable("area")
	public Area findAreaByTitle(String title) {
		return areaRepository.findAreaByTitle(title);
	}
}
