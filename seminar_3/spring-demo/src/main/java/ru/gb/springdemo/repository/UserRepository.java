package ru.gb.springdemo.repository;

import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.User;
import ru.gb.springdemo.repository.interfaces.IUserRepository;

import java.util.Optional;

@Repository
public class UserRepository {

    private final IUserRepository repository;

    public UserRepository(IUserRepository userRepository) {
        this.repository = userRepository;
    }

    public Optional<User> getUserByName(String name) {
        return Optional.of(repository.findByName(name));
    }
}
