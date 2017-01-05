package com.skills.service;

import com.google.common.collect.Lists;
import com.skills.domain.model.Area;
import com.skills.persistent.AreaRepository;
import com.skills.web.dto.AutocompleteDto;
import com.skills.web.dto.AutocompleteDto.AutocompleteChildrenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional(readOnly = true)
public class AreaServiceImpl implements AreaService {

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

    @Override
    public List<AutocompleteDto> getAutocomplete() {
        return StreamSupport.stream(areaRepository.findAll().spliterator(), false)
                .map(area -> {
                    AutocompleteDto node = new AutocompleteDto();
                    node.setText(area.getTitle());

                    List<AutocompleteChildrenDto> children = area.getSkills().stream()
                            .map(skill -> {
                                AutocompleteChildrenDto child = new AutocompleteChildrenDto();
                                child.setId(skill.getSkillId());
                                child.setText(skill.getTitle());
                                return child;
                            })
                            .collect(Collectors.toList());

                    node.setChildren(children);

                    return node;
                })
                .collect(Collectors.toList());
    }
}
