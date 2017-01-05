package com.skills;

import com.skills.domain.model.Area;
import com.skills.domain.model.Skill;
import com.skills.domain.model.User;
import com.skills.domain.model.UserRole;
import com.skills.service.AreaService;
import com.skills.service.SkillService;
import com.skills.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DevData {

    private UserService userService;
    private SkillService knowledgeService;
    private AreaService areaService;

    @Autowired
    public DevData(UserService userService, SkillService knowledgeService, AreaService areaService) {
        this.userService = userService;
        this.knowledgeService = knowledgeService;
        this.areaService = areaService;
    }

    @PostConstruct
    public void init() {
        addUsers();
        addAreasAndSkills();
    }

    private void addAreasAndSkills() {
        Area java = new Area("Java");
        Area dotNet = new Area(".NET");

        areaService.save(java);
        areaService.save(dotNet);

        java = areaService.findAreaByTitle("Java");
        dotNet = areaService.findAreaByTitle(".NET");

        // ===================================================================

        Skill spring = new Skill("Spring", null);
        Skill hibernate = new Skill("Hibernate", null);
        Skill ef = new Skill("Entity Framework", null);

        knowledgeService.save(spring);
        knowledgeService.save(hibernate);
        knowledgeService.save(ef);

        spring = knowledgeService.findSkillByTitle("Spring");
        hibernate = knowledgeService.findSkillByTitle("Hibernate");
        ef = knowledgeService.findSkillByTitle("Entity Framework");

        // ====================================================================

        spring.setArea(java);
        hibernate.setArea(java);
        ef.setArea(dotNet);

        Set<Skill> javaSkills = new HashSet<>();
        javaSkills.add(spring);
        javaSkills.add(hibernate);

        Set<Skill> dotNetSkills = new HashSet<>();
        dotNetSkills.add(ef);

        java.setSkills(javaSkills);
        areaService.save(java);

        dotNet.setSkills(dotNetSkills);
        areaService.save(dotNet);

        java = areaService.findAreaByTitle("Java");
        ef = knowledgeService.findSkillByTitle("Entity Framework");

    }

    private void addUsers() {
        User employ = new User("Alce", "alice@foo.com", "alice", "123456", UserRole.ROLE_EMPLOY);
        User hr = new User("Bob", "bob@foo.com", "bob", "123456", UserRole.ROLE_HR);
        User business = new User("Bill", "bill@foo.com", "bill", "123456", UserRole.ROLE_BUSINESS);

        userService.save(employ);
        userService.save(hr);
        userService.save(business);
    }
}
