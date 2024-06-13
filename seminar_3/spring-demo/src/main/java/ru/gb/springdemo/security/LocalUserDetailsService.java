package ru.gb.springdemo.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.gb.springdemo.model.User;
import ru.gb.springdemo.repository.UserRepository;

import java.util.stream.Collectors;

@Component
public class LocalUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    public LocalUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.getUserByName(username).stream().findFirst().orElse(null);
        if (user == null) throw new UsernameNotFoundException(username + " not found");

        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPasswordHash(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList()));
    }
}
