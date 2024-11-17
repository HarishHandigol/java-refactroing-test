package com.sap.refactoring.integration;

import com.sap.refactoring.controller.UserController;
import com.sap.refactoring.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserIntegrationTest
{
	private final UserController userController;

	@Autowired
	public UserIntegrationTest(UserController userController) {
		this.userController = userController;
	}

	@Test
	public void createUserTest() {
		User integration = new User();
		integration.setId(1L);
		integration.setName("integration");
		integration.setEmail("initial@integration.com");
		integration.setRoles(new ArrayList<>());

		ResponseEntity response = userController.addUser(integration.getName(), integration.getEmail(), integration.getRoles());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
	}

	@Test
	public void updateUserTest() {
//		createUserTest();
		List<String> roles = new ArrayList<>();
		roles.add("Manager");

		User updated = new User();
		updated.setId(1L);
		updated.setName("integration");
		updated.setEmail("updated@integration.com");
		updated.setRoles(roles);

		ResponseEntity response = userController.updateUser(updated);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
	}
}
