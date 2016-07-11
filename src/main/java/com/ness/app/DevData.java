package com.ness.app;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ness.app.domain.model.Area;
import com.ness.app.domain.model.Knowledge;
import com.ness.app.domain.model.User;
import com.ness.app.domain.model.UserRole;
import com.ness.app.service.AreaService;
import com.ness.app.service.KnowledgeService;
import com.ness.app.service.UserService;

@Component
public class DevData {
	
	private UserService userService;
	private KnowledgeService knowledgeService;
	private AreaService areaService;
	
	@Autowired
	public DevData(UserService userService, KnowledgeService knowledgeService, AreaService areaService) {
		this.userService = userService;
		this.knowledgeService = knowledgeService;
		this.areaService = areaService;
	}

	@PostConstruct
	public void init() {
		addUsers();
		addAreasAndKnowledges();
	}
	
	private void addAreasAndKnowledges() {
		Area java = new Area("Java");
		Area dotNet = new Area(".NET");
		
		areaService.save(java);
		areaService.save(dotNet);
		
		java = areaService.findAreaByTitle("Java");
		dotNet = areaService.findAreaByTitle(".NET");
		
		// ===================================================================
		
		Knowledge spring = new Knowledge("Spring", null);
		Knowledge hibernate = new Knowledge("Hibernate", null);
		Knowledge ef = new Knowledge("Entity Framework", null);
		
		knowledgeService.save(spring);
		knowledgeService.save(hibernate);
		knowledgeService.save(ef);
		
		spring = knowledgeService.findKnowledgeByTitle("Spring");
		hibernate = knowledgeService.findKnowledgeByTitle("Hibernate");
		ef = knowledgeService.findKnowledgeByTitle("Entity Framework");
		
		// ====================================================================
		
		spring.setArea(java);
		hibernate.setArea(java);
		ef.setArea(dotNet);
		
		Set<Knowledge> javaKnowledges = new HashSet<>();
		javaKnowledges.add(spring);
		javaKnowledges.add(hibernate);
		
		Set<Knowledge> dotNetKnowledges = new HashSet<>();
		dotNetKnowledges.add(ef);
		
		java.setKnowledges(javaKnowledges);
		areaService.save(java);
		
		dotNet.setKnowledges(dotNetKnowledges);
		areaService.save(dotNet);
		
		java = areaService.findAreaByTitle("Java");
		ef = knowledgeService.findKnowledgeByTitle("Entity Framework");
		
	}

	private void addUsers() {
		User employ = new User("Alce", "alice@foo.com", "alice", "alice", UserRole.ROLE_EMPLOY);
		User hr = new User("Bob", "bob@foo.com", "bob", "bob", UserRole.ROLE_HR);
		User business = new User("Bill", "bill@foo.com", "bill", "bill", UserRole.ROLE_BUSINESS);
		
		userService.save(employ);
		userService.save(hr);
		userService.save(business);
	}
}
