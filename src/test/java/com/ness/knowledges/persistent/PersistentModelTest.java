package com.ness.knowledges.persistent;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ness.knowledges.config.ApplicationConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ApplicationConfiguration.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class PersistentModelTest {

//	@Autowired
//	KnowledgeRepository knowledgeRepository;
//	
//	@Autowired
//	private UserRepository userRepository;
//	
//	@Autowired
//	private AreaRepository areaRepository;
//	
//	@Test
//	public void completeTest() {
//		UserEntity foo = new UserEntity("Foo", "Bar");
//		Area java = new Area("Java");
//		
//		Knowledge spring = new Knowledge("Spring");
//		spring.setArea(java);
//		Knowledge jpa = new Knowledge("Jpa");
//		jpa.setArea(java);
//		
//		java.setKnowledges(Arrays.asList(spring, jpa));
//		foo.setKnowledges(Arrays.asList(spring, jpa));
//		
//		knowledgeRepository.saveUser(spring);
//		knowledgeRepository.saveUser(jpa);
//		areaRepository.saveUser(java);
//		userRepository.saveUser(foo);
//		
//		foo = userRepository.findUserByFirstName("Foo");
//		assertNotNull(foo);
//		assertThat(foo.getKnowledges().size(), equalTo(2));
//		
//		java = areaRepository.findAreaByTitle("Java");
//		assertNotNull(java);
//		assertThat(java.getKnowledges().size(), equalTo(2));
//	}
//	
//	@Test
//	public void saveKnowledgeTest() {
//		Knowledge java = new Knowledge("Java", "Java programming language");
//		knowledgeRepository.saveUser(java);
//		assertThat(knowledgeRepository.count(), equalTo(1L));
//	}
//	
//	@Test
//	public void updateKnowledgeTest() {
//		Knowledge java = new Knowledge("Java", "Java programming language");
//		knowledgeRepository.saveUser(java);
//		
//		Knowledge javaFromDb = knowledgeRepository.findKnowledgeByTitle("Java");
//		javaFromDb.setDescription("Nope");
//		knowledgeRepository.saveUser(javaFromDb);
//		
//		Knowledge expectedJava = new Knowledge("Java", "Nope");
//		javaFromDb = knowledgeRepository.findKnowledgeByTitle("Java");
//		assertEquals(expectedJava, javaFromDb);
//	}
//	
//	@Test
//	public void findKnowledgeByTitleTest() {
//		Knowledge java = new Knowledge("Java", "Java programming language");
//		knowledgeRepository.saveUser(java);
//		Knowledge javaFromDb = knowledgeRepository.findKnowledgeByTitle("Java");
//		assertEquals(java, javaFromDb);
//	}
//	
//	@Test
//	public void saveUserWithKnowledgesTest() {
//		UserEntity user = new UserEntity();
//		user.setFirstName("Foo");
//		user.setLastName("Bar");
//		user.setEmail("foo.bar@mail.com");
//		
//		Knowledge java = new Knowledge("Java", "Java");
//		user.setKnowledges(Arrays.asList(java));
//		
//		knowledgeRepository.saveUser(java);
//		userRepository.saveUser(user);
//		
//		UserEntity userFromDb = userRepository.findUserByEmail("foo.bar@mail.com");
//		Assert.assertThat(userFromDb.getKnowledges().size(), Matchers.equalTo(1));
//		
//		Knowledge javaFromDb = userFromDb.getKnowledges().stream().findFirst().get();
//		
//		Assert.assertEquals(java, javaFromDb);
//	}
}
