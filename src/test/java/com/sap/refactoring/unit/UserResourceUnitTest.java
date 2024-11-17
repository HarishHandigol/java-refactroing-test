package com.sap.refactoring.unit;

import com.sap.refactoring.controller.UserController;
import com.sap.refactoring.dao.UserDao;
import com.sap.refactoring.model.User;
import com.sap.refactoring.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserResourceUnitTest
{
	private final UserController userController;
	private final UserService userService;

	@Autowired
	public UserResourceUnitTest(UserDao userDao, UserController userController) {
		this.userService = userDao;
		this.userController = userController;
	}

	@Test
	public void getUsersTest() {

		User user = new User();
		user.setName("fake user");
		user.setEmail("fake@user.com");

		ResponseEntity response = userController.getUsers();
		assertThat(response.getStatusCode().is2xxSuccessful());
	}
}
