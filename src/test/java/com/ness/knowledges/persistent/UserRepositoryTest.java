package com.ness.knowledges.persistent;

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
import com.ness.knowledges.domain.Knowledge;
import com.ness.knowledges.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ApplicationConfiguration.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class UserRepositoryTest {
	
	@Autowired
	private KnowledgeRepository knowledgeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void saveUserWithKnowledgesTest() {
		User user = new User();
		user.setFirstName("Foo");
		user.setLastName("Bar");
		user.setEmail("foo.bar@mail.com");
		
		Knowledge java = new Knowledge("Java", "Java");
		user.setKnowledges(Arrays.asList(java));
		
		knowledgeRepository.save(java);
		userRepository.save(user);
		
		User userFromDb = userRepository.findUserByEmail("foo.bar@mail.com");
		Assert.assertThat(userFromDb.getKnowledges().size(), Matchers.equalTo(1));
		
		Knowledge javaFromDb = userFromDb.getKnowledges().stream().findFirst().get();
		
		Assert.assertEquals(java, javaFromDb);
	}
}
