package com.ness.knowledges.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ness.knowledges.domain.Area;
import com.ness.knowledges.domain.Knowledge;
import com.ness.knowledges.service.interfaces.AreaService;
import com.ness.knowledges.service.interfaces.KnowledgeService;

@Controller
@RequestMapping("/knowledge")
public class KnowledgeController {
	
	@Autowired
	private KnowledgeService knowledgeService;
	
	@Autowired
	private AreaService areaService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String knowledgeHome(Principal principal, Model model) {
		model.addAttribute("username", principal.getName());
		
		List<Area> areas = (List<Area>) areaService.findAllAreas();
		Boolean isAreasEmpty = areas.isEmpty();
		
		model.addAttribute("newArea", Area.getEmptyArea());
		model.addAttribute("areas", areas);
		model.addAttribute("isAreasEmpty", isAreasEmpty);
		
		return "knowledge/home";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String saveNewArea(@Valid Area area) {
		areaService.save(area);
		return "redirect:/knowledge";
	}
	
	@RequestMapping(value = "/area/{areaTitle:.+}", method = RequestMethod.GET)
	public String areaPage(@PathVariable String areaTitle, Principal principal, Model model) {
		model.addAttribute("username", principal.getName());
		
		List<Area> areas = (List<Area>) areaService.findAllAreas();
		Boolean isAreasEmpty = areas.isEmpty();
		model.addAttribute("isAreasEmpty", isAreasEmpty);
		model.addAttribute("areas", areas);
		
		Area area = areaService.findAreaByTitle(areaTitle);
		
		model.addAttribute("newKnowledge", Knowledge.getEmtyKnowledge());
		model.addAttribute("activeArea", area.getTitle());
		model.addAttribute("knowledges", area.getKnowledges());
		return "knowledge/area";
	}
	
	@RequestMapping(value = "/area/{areaTitle:.+}", method = RequestMethod.POST)
	public String saveNewKnowledge(@PathVariable String areaTitle, @Valid Knowledge knowledge) {
		Area area = areaService.findAreaByTitle(areaTitle);
		knowledge.setArea(area);
		knowledgeService.save(knowledge);
		return "redirect:/knowledge/area/" + areaTitle;
	}
}
