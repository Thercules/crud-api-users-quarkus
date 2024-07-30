package org.acme.hercules;

import io.quarkus.test.junit.QuarkusTest;
import org.acme.hercules.entity.User;
import jakarta.transaction.Transactional;
import org.acme.hercules.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class UserRepositoryTest {

    @Inject
    UserRepository userRepository;

    private User testUser;

    @BeforeEach
    @Transactional
    public void setUp() {
        testUser = new User();
        testUser.name = "Alice Johnson";
        testUser.email = "alice.johnson@example.com";
        testUser.password = "password123";
        testUser.cpf = "12345678901";
        testUser.rg = "123456789";
        testUser.address = "456 Elm St, Springfield";
        testUser.phoneNumber = "987654321";
        testUser.dateOfBirth = LocalDate.of(1985, 5, 15);
        testUser.gender = "F";
        testUser.role = "ADMIN";
        testUser.isActive = true;

        userRepository.addUser(testUser);
    }

    @Test
    @Transactional
    public void testUpdateUser() {
        testUser.name = "Alice Smith";
        testUser.email = "alice.smith@example.com";
        userRepository.updateUser(testUser);

        User updatedUser = userRepository.findById(testUser.id);
        assertNotNull(updatedUser);
        assertEquals("Alice Smith", updatedUser.name);
        assertEquals("alice.smith@example.com", updatedUser.email);
    }

    @Test
    @Transactional
    public void testDeleteUser() {
        userRepository.deleteUser(testUser.id);

        User deletedUser = userRepository.findById(testUser.id);
        assertNull(deletedUser);
    }

    @Test
    @Transactional
    public void testUpdateAndDeleteUser() {
        // Atualiza o usuário
        testUser.name = "Alice Cooper";
        testUser.email = "alice.cooper@example.com";
        userRepository.updateUser(testUser);

        User updatedUser = userRepository.findById(testUser.id);
        assertNotNull(updatedUser);
        assertEquals("Alice Cooper", updatedUser.name);
        assertEquals("alice.cooper@example.com", updatedUser.email);

        // Deleta o usuário
        userRepository.deleteUser(testUser.id);

        User deletedUser = userRepository.findById(testUser.id);
        assertNull(deletedUser);
    }
}
