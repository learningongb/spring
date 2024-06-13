package ru.gb.springdemo.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.springdemo.model.User;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}
