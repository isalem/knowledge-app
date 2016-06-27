package com.ness.knowledges.persistent;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ness.knowledges.config.ApplicationConfiguration;
import com.ness.knowledges.persistent.model.AreaEntity;
import com.ness.knowledges.persistent.model.KnowledgeEntity;
import com.ness.knowledges.persistent.model.UserEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ApplicationConfiguration.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class PersistentModelTest {

	@Autowired
	KnowledgeRepository knowledgeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AreaRepository areaRepository;
	
	@Test
	public void completeTest() {
		UserEntity foo = new UserEntity("Foo", "Bar");
		AreaEntity java = new AreaEntity("Java");
		
		KnowledgeEntity spring = new KnowledgeEntity("Spring");
		spring.setArea(java);
		KnowledgeEntity jpa = new KnowledgeEntity("Jpa");
		jpa.setArea(java);
		
		java.setKnowledges(Arrays.asList(spring, jpa));
		foo.setKnowledges(Arrays.asList(spring, jpa));
		
		knowledgeRepository.save(spring);
		knowledgeRepository.save(jpa);
		areaRepository.save(java);
		userRepository.save(foo);
		
		foo = userRepository.findUserByFirstName("Foo");
		assertNotNull(foo);
		assertThat(foo.getKnowledges().size(), equalTo(2));
		
		java = areaRepository.findAreaByTitle("Java");
		assertNotNull(java);
		assertThat(java.getKnowledges().size(), equalTo(2));
	}
	
	@Test
	public void saveKnowledgeTest() {
		KnowledgeEntity java = new KnowledgeEntity("Java", "Java programming language");
		knowledgeRepository.save(java);
		assertThat(knowledgeRepository.count(), equalTo(1L));
	}
	
	@Test
	public void updateKnowledgeTest() {
		KnowledgeEntity java = new KnowledgeEntity("Java", "Java programming language");
		knowledgeRepository.save(java);
		
		KnowledgeEntity javaFromDb = knowledgeRepository.findKnowledgeByTitle("Java");
		javaFromDb.setDescription("Nope");
		knowledgeRepository.save(javaFromDb);
		
		KnowledgeEntity expectedJava = new KnowledgeEntity("Java", "Nope");
		javaFromDb = knowledgeRepository.findKnowledgeByTitle("Java");
		assertEquals(expectedJava, javaFromDb);
	}
	
	@Test
	public void findKnowledgeByTitleTest() {
		KnowledgeEntity java = new KnowledgeEntity("Java", "Java programming language");
		knowledgeRepository.save(java);
		KnowledgeEntity javaFromDb = knowledgeRepository.findKnowledgeByTitle("Java");
		assertEquals(java, javaFromDb);
	}
	
	@Test
	public void saveUserWithKnowledgesTest() {
		UserEntity user = new UserEntity();
		user.setFirstName("Foo");
		user.setLastName("Bar");
		user.setEmail("foo.bar@mail.com");
		
		KnowledgeEntity java = new KnowledgeEntity("Java", "Java");
		user.setKnowledges(Arrays.asList(java));
		
		knowledgeRepository.save(java);
		userRepository.save(user);
		
		UserEntity userFromDb = userRepository.findUserByEmail("foo.bar@mail.com");
		Assert.assertThat(userFromDb.getKnowledges().size(), Matchers.equalTo(1));
		
		KnowledgeEntity javaFromDb = userFromDb.getKnowledges().stream().findFirst().get();
		
		Assert.assertEquals(java, javaFromDb);
	}
}
