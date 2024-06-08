package ru.gb.springdemo.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.service.IssuerService;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/issue")
public class IssuerController {

  @Autowired
  private IssuerService service;
  @Autowired
  private IssueRepository repository;

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Issue> returnBook(@PathVariable long id) {
    // найти в репозитории выдачу и проставить ей returned_at
    final Issue issue;
    try {
      issue = service.issue(id);
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }
    repository.returnBook(issue);
    return ResponseEntity.ok().body(issue);
  }

  @PostMapping
  public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
    log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());

    final Issue issue;
    try {
      issue = service.issue(request);
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }

    if (issue == null)
      return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

    return ResponseEntity.status(HttpStatus.OK).body(issue);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Issue> getIssue(@PathVariable long id) {
    final Issue issue;
    try {
      issue = service.issue(id);
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(issue);
  }
}