package ru.gb.springdemo.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.springdemo.model.Issue;

import java.util.List;

public interface IIssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByReaderId(Long readerId);
}
