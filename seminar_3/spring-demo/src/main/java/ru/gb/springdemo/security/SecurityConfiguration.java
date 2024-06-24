package ru.gb.springdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@Configuration
public class SecurityConfiguration {

//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity.authorizeHttpRequests(registry -> registry
//                .anyRequest().permitAll()
//        ).build();

//        return httpSecurity
//                .authorizeHttpRequests(registry -> registry
//                        .requestMatchers("/ui/readers/**").hasAuthority("reader")
//                        .requestMatchers("/ui/reader/**").hasAuthority("reader")
//                        .requestMatchers("/ui/issues/**").hasAuthority("admin")
//                        .requestMatchers("/ui/books/**").authenticated()
//                )
//                .oauth2ResourceServer(configurer -> configurer
//                                .jwt(Customizer.withDefaults())
//                )
//                .build();
//    }

}
