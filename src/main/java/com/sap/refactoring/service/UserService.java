package com.sap.refactoring.service;

import com.sap.refactoring.model.User;

import java.util.List;

public interface UserService {

    User findUser(String name);
    List<User> getUsers();
    void saveUser(User user);
    void deleteUser(User userToDelete);
    void updateUser(User userToUpdate);
}
