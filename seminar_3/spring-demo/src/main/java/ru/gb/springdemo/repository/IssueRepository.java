package ru.gb.springdemo.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Issue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class IssueRepository {

  private final List<Issue> issues;

  @Value("${application.max-allowed-books:3}")
  private int maxAllowedBooks;

  public IssueRepository() {
    this.issues = new ArrayList<>();
  }

  public void save(Issue issue) {
    // insert into ....
    if (countIssues(issue.getReaderId()) < maxAllowedBooks)
    {
      issues.add(issue);
    } else {
      throw new RuntimeException("Книга не выдана, т.к. превышен допустимый лимит взятых книг.");
    }

  }

  public Issue get(long id) {
    System.out.println(maxAllowedBooks);
    return issues.stream().filter(it -> Objects.equals(it.getId(), id))
            .findFirst()
            .orElse(null);
  }

  public int countIssues(long readerId) {
    return readersIssues(readerId).size();
  }

  public List<Issue> readersIssues(long readerId) {
    return issues.stream().filter(it -> Objects.equals(it.getReaderId(), readerId))
            .filter(it -> it.getReturnedAt() == null)
            .toList();
  }

  public void returnBook(Issue issue) {
    issue.setReturnedAt(LocalDateTime.now());
  }

  public List<Issue> getIssues() {
    return List.copyOf(issues);
  }

}
