package com.sap.refactoring.dao;

import com.sap.refactoring.exception.InvalidUserException;
import com.sap.refactoring.exception.UserNotFoundException;
import com.sap.refactoring.model.User;
import com.sap.refactoring.repository.UserRepository;
import com.sap.refactoring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserDao implements UserService
{
	private final UserRepository userRepository;

	@Autowired
	public UserDao(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void saveUser(User user) {
		try {
			isValidUser(user);
			if (user != null) {
				userRepository.save(user);
			} else {
				throw new InvalidUserException("The user cannot be empty!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> getUsers() {
		try {
			List<User> users = userRepository.findAll();
			return users;
		} catch (Exception e) {
			System.out.println("Error retrieving all users");
			return null;
		}
	}

	@Override
	public void deleteUser(User userToDelete) {
		try {
			Optional<User> existingUser = userRepository.findById(userToDelete.getId());
			if (existingUser.isPresent()) {
				userRepository.delete(userToDelete);
			} else {
				throw new UserNotFoundException("User with name " + userToDelete.getName() + " not found.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateUser(User userToUpdate) {
		try {
			isValidUser(userToUpdate);
			Optional<User> existingUser = userRepository.findById(userToUpdate.getId());
			if (existingUser.isPresent()) {
				existingUser.get().setEmail(userToUpdate.getEmail());
				existingUser.get().setRoles(userToUpdate.getRoles());
			} else {
				throw new UserNotFoundException("User with name " + userToUpdate.getName() + " not found.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public User findUser(String name) {
		try {
			Optional<User> user = userRepository.findByName(name);
			if (user.isPresent()) {
				return user.get();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new UserNotFoundException("User with name " + name + " not found.");
	}

	private boolean isValidUser(User user) {
		if (user.getName() != null && user.getEmail() != null && user.getId() != null) {
			return true;
		}

		throw new InvalidUserException("User Email, Name and ID cannot be null");
	}
}
