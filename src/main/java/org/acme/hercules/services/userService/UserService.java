package org.acme.hercules.services.userService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.hercules.entity.User;
import org.acme.hercules.repository.userRepository.UserRepository;

import java.util.List;

@ApplicationScoped
public class UserService implements UserServiceInterface {

    @Inject
    UserRepository userRepository;

    @Transactional
    public List<User> listAllUsers() {
        return userRepository.listAll();
    }

    @Transactional
    public User findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public void createUser(User user) {
        user.encryptSensitiveData();
        userRepository.addUser(user);
    }

    @Transactional
    public void updateUser(User user) {
        user.encryptSensitiveData();
        userRepository.updateUser(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteUser(id);
    }
}
