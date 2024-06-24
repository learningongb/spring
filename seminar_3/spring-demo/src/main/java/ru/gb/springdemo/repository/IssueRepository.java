package ru.gb.springdemo.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.repository.interfaces.IIssueRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class IssueRepository {

  private final IIssueRepository repository;

  public IssueRepository(IIssueRepository repository) {
      this.repository = repository;
  }

  public Issue save(Issue issue) {

    return repository.save(issue);

  }

  public Issue get(long id) {
    return repository.findById(id).stream().findFirst().orElse(null);
  }

  public int countIssues(Long readerId) {
    return repository.findByReaderId(readerId).size();
  }

  public List<Issue> readersIssues(long readerId) {
    return null;
  }

  public Issue returnBook(Issue issue) {
    issue.setReturnedAt(LocalDateTime.now());
    return repository.save(issue);
  }

}
