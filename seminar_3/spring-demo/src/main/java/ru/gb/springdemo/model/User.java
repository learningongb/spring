package ru.gb.springdemo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Table(name = "users")
//@Getter
//@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;

    @Column(name = "name")
    private String name;

    @Column(name = "pwdHash")
    private String passwordHash;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
}
