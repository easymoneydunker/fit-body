package ru.easymoneydunker.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.easymoneydunker.model.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
}