package com.ness.app.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ness.app.domain.model.Area;
import com.ness.app.domain.model.Skill;
import com.ness.app.exception.SkillNotFountException;
import com.ness.app.service.AreaService;
import com.ness.app.service.SkillService;
import com.ness.app.view.EditSkillForm;

@Controller
@RequestMapping("/skill")
public class SkillController {
	
	@Autowired
	private SkillService skillService;
	
	@Autowired
	private AreaService areaService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String skillHome(Principal principal, Model model) {
		
		List<Area> areas = areaService.findAllAreas();
		Boolean isAreasEmpty = areas.isEmpty();
		
		model.addAttribute("newArea", Area.getEmptyArea());
		model.addAttribute("areas", areas);
		model.addAttribute("isAreasEmpty", isAreasEmpty);
		
		return "skill/area";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String saveNewArea(@Valid Area area) {
		areaService.save(area);
		return "redirect:/skill";
	}
	
	@RequestMapping(value = "/area/{areaTitle:.+}", method = RequestMethod.GET)
	public String areaPage(@PathVariable String areaTitle, Principal principal, Model model) {
		
		List<Area> areas = areaService.findAllAreas();
		Boolean isAreasEmpty = areas.isEmpty();
		model.addAttribute("isAreasEmpty", isAreasEmpty);
		model.addAttribute("areas", areas);
		
		Area area = areaService.findAreaByTitle(areaTitle);
		
		model.addAttribute("newSkill", new Skill());
		model.addAttribute("activeArea", area.getTitle());
		model.addAttribute("skills", area.getSkills());
		return "skill/skill";
	}
	
	@RequestMapping(value = "/area/{areaTitle:.+}", method = RequestMethod.POST)
	public String saveNewSkill(@PathVariable String areaTitle, @Valid Skill skill) {
		Area area = areaService.findAreaByTitle(areaTitle);
		skill.setArea(area);
		skillService.save(skill);
		return "redirect:/skill/area/" + areaTitle;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ResponseEntity<String> editSkill(@ModelAttribute EditSkillForm body) {
		Skill skill = skillService.findSkillById(body.getPk());
		if (skill == null) throw new SkillNotFountException();
		skill.setTitle(body.getValue());
		skillService.save(skill);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
