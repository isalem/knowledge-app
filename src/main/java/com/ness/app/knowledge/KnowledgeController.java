package com.ness.app.knowledge;

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

import com.ness.app.area.Area;
import com.ness.app.area.AreaService;
import com.ness.app.exception.KnowledgeNotFountException;
import com.ness.app.util.BaseController;

@Controller
@RequestMapping("/knowledge")
public class KnowledgeController extends BaseController {
	
	@Autowired
	private KnowledgeService knowledgeService;
	
	@Autowired
	private AreaService areaService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String knowledgeHome(Principal principal, Model model) {
		
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
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ResponseEntity<String> editKnowledge(@ModelAttribute EditKnowledgeForm body) {
		Knowledge knowledge = knowledgeService.findKnowledgeById(body.getPk());
		if (knowledge == null) throw new KnowledgeNotFountException();
		knowledge.setTitle(body.getValue());
		knowledgeService.save(knowledge);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
