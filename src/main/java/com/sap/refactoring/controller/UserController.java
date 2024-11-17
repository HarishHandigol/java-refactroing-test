package com.sap.refactoring.controller;

import java.util.ArrayList;
import java.util.List;

import com.sap.refactoring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.sap.refactoring.model.User;
import com.sap.refactoring.dao.UserDao;

@Controller
@RequestMapping("/users")
public class UserController
{
	private final UserService userService;

	@Autowired
	public UserController(UserDao userDao) {
		this.userService = userDao;
	}

	@PostMapping(value = "add/")
	public ResponseEntity addUser(@RequestParam("name") String name,
	                              @RequestParam("email") String email,
	                              @RequestParam("role") List<String> roles) {

		User user = new User();
		user.setId(3L);
		user.setName(name);
		user.setEmail(email);
		user.setRoles(roles);

		userService.saveUser(user);
		return ResponseEntity.ok(user);
	}

	@PutMapping(value = "update/")
	public ResponseEntity updateUser(@RequestParam("user") User user) {
		user.setId(user.getId());
		user.setName(user.getName());
		user.setEmail(user.getEmail());
		user.setRoles(user.getRoles());

		userService.updateUser(user);
		return ResponseEntity.ok(user);
	}
	@DeleteMapping(value = "delete/")
	public ResponseEntity deleteUser(@RequestParam("name") String name,
	                           @RequestParam("email") String email,
	                           @RequestParam("role") List<String> roles) {
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setRoles(roles);

		userService.deleteUser(user);
		return ResponseEntity.ok(user);
	}
	@GetMapping(value = "find/")
	public ResponseEntity getUsers() {

		List<User> users = userService.getUsers();
		if (users == null) {
			users = new ArrayList<>();
		}

		return ResponseEntity.ok(users);
	}
	@GetMapping(value = "search/")
	public ResponseEntity findUser(@RequestParam("name") String name) {

		User user = userService.findUser(name);
		return ResponseEntity.ok(user);
	}
}
