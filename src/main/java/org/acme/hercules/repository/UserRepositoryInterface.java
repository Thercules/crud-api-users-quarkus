package org.acme.hercules.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.acme.hercules.entity.User;

public interface UserRepositoryInterface extends PanacheRepository<User> {
    // Métodos personalizados podem ser definidos aqui
}
