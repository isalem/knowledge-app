package com.ness.knowledges.persistent;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ness.knowledges.config.ApplicationConfiguration;
import com.ness.knowledges.domain.Knowledge;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ApplicationConfiguration.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class KnowledgeRepositoryTest {

	@Autowired
	KnowledgeRepository knowledgeRepository;
	
	@Test
	public void saveKnowledgeTest() {
		Knowledge java = new Knowledge("Java", "Java programming language");
		knowledgeRepository.save(java);
		assertThat(knowledgeRepository.count(), equalTo(1L));
	}
	
	@Test
	public void updateKnowledgeTest() {
		Knowledge java = new Knowledge("Java", "Java programming language");
		knowledgeRepository.save(java);
		
		Knowledge javaFromDb = knowledgeRepository.findKnowledgeByTitle("Java");
		javaFromDb.setDescription("Nope");
		knowledgeRepository.save(javaFromDb);
		
		Knowledge expectedJava = new Knowledge("Java", "Nope");
		javaFromDb = knowledgeRepository.findKnowledgeByTitle("Java");
		assertEquals(expectedJava, javaFromDb);
	}
	
	@Test
	public void findKnowledgeByTitleTest() {
		Knowledge java = new Knowledge("Java", "Java programming language");
		knowledgeRepository.save(java);
		Knowledge javaFromDb = knowledgeRepository.findKnowledgeByTitle("Java");
		assertEquals(java, javaFromDb);
	}
	
	@Test
	public void saveKnowledgeWithSubknowledgeTest() {
		Knowledge java = new Knowledge("Java", "Java programming language");
		Knowledge spring = new Knowledge("Spring", "Spring framework");
		spring.setParent(java);
		java.setKnowledges(Arrays.asList(spring));
		
		Knowledge javaInDb = knowledgeRepository.save(java);
		knowledgeRepository.save(spring);
		
		Knowledge javaFromDb = knowledgeRepository.findOne(javaInDb.getId());
		assertThat(javaFromDb.getKnowledges().size(), equalTo(1));
		Knowledge springFromDb = javaFromDb.getKnowledges().stream().findFirst().get();
		assertEquals(spring, springFromDb);
	}
	
	@Test
	public void addKnowledgeToAlreadyExistedParentTest() {
		Knowledge java = new Knowledge("Java", "Java programming language");
		knowledgeRepository.save(java);
		
		Knowledge javaFromDb = knowledgeRepository.findKnowledgeByTitle("Java");
		
		Knowledge spring = new Knowledge("Spring", "Spring framework");
		spring.setParent(javaFromDb);
		javaFromDb.setKnowledges(Arrays.asList(spring));
		
		knowledgeRepository.save(spring);
		knowledgeRepository.save(javaFromDb);
		
		assertThat(knowledgeRepository.count(), equalTo(2L));
		
		javaFromDb = knowledgeRepository.findKnowledgeByTitle("Java");
		Knowledge springFromDb = javaFromDb.getKnowledges().stream().findFirst().get();
		assertEquals(spring, springFromDb);
	}
}
