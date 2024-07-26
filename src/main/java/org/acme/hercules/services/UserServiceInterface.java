package org.acme.hercules.services;

import org.acme.hercules.entity.User;

import java.util.List;

public interface UserServiceInterface {
    List<User> listAllUsers();
    User findUserById(Long id);
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
}
