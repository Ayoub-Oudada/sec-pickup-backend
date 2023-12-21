package com.backend;

import com.backend.entities.User;
import com.backend.repositories.UsersRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendApplicationTests {
	private final UsersRepository usersRepository;


	@Autowired
	BackendApplicationTests(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	@Test
	void contextLoads() {
	}

//	@Test
//	void assertConnectionEstablishedWithDb() {
//		var oldCount = usersRepository.count();
//		User user = usersRepository.save(User.builder()
//				.username("test@test.com")
//				.password("password")
//				.build()
//		);
//		Assert.assertNotNull(user);
//		Assert.assertEquals(
//				usersRepository.count(), oldCount + 1
//		);
//	}


}
