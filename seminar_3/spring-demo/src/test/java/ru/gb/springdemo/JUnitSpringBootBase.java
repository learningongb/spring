package ru.gb.springdemo;

import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public abstract class JUnitSpringBootBase {

//    @TestConfiguration
//    static class TestSecurityConfiguration {
//        @Bean
//        SecurityFilterChain testSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
//            return httpSecurity.authorizeHttpRequests(registry -> registry
//                    .anyRequest().permitAll()
//            ).build();
//        }
//    }

}
