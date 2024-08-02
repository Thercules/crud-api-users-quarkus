package org.acme.hercules.repository.userRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.hercules.entity.User;

@ApplicationScoped
public class UserRepository implements UserRepositoryInterface {

    @Transactional
    public void addUser(User user) {
        persist(user);
    }

    @Transactional
    public User updateUser(User user) {
        return getEntityManager().merge(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        deleteById(id);
    }
}
