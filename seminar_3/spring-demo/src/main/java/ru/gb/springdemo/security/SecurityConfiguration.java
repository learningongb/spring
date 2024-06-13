package ru.gb.springdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(registry -> registry
                        .requestMatchers("/ui/readers/**").hasAuthority("reader")
                        .requestMatchers("/ui/reader/**").hasAuthority("reader")
                        .requestMatchers("/ui/books/**").authenticated()
                        .requestMatchers("/ui/issues/**").hasAuthority("admin")
                )
                .formLogin(Customizer.withDefaults())
                .build();
    }

}
