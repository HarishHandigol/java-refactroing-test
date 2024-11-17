package com.sap.refactoring.unit;

import com.sap.refactoring.dao.UserDao;
import com.sap.refactoring.model.User;
import com.sap.refactoring.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class UserDaoUnitTest
{
	private final UserService userService;

	@Autowired
	public UserDaoUnitTest(UserDao userDao) {
		this.userService = userDao;
	}

	@Test
	public void saveUserTest() {

		User user = new User();
		user.setId(1L);
		user.setName("Fake Name");
		user.setEmail("fake@email.com");
		user.setRoles(Arrays.asList("admin", "master"));

		userService.saveUser(user);
	}

	@Test
	public void deleteUserTest() {

		User user = new User();
		user.setId(1L);
		user.setName("Fake Name");
		user.setEmail("fake@email.com");
		user.setRoles(Arrays.asList("admin", "master"));

		try {
			userService.deleteUser(user);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
