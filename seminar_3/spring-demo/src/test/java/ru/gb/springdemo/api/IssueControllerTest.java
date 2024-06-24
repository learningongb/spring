package ru.gb.springdemo.api;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.gb.springdemo.JUnitSpringBootBase;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

public class IssueControllerTest extends JUnitSpringBootBase {
    @Autowired
    WebTestClient webTestClient;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ReaderRepository readerRepository;

    @Autowired
    IssueRepository issueRepository;

    public IssueControllerTest() {
        this.faker = new Faker();
    }

    Faker faker;

    @Test
    void testReturnBook() {
        Reader reader = readerRepository.create(faker.name().fullName());
        Book book = bookRepository.createBook(faker.book().title());
        Issue issue = new Issue(book.getId(), reader.getId());
        issue = issueRepository.save(issue);

        // Проверить, что исходное состояние - книга не возвращена
        Assertions.assertNull(issue.getReturnedAt());

        // Вернуть книгу
        webTestClient.put()
                .uri("/issue/" + issue.getId())
                .exchange()
                .expectStatus().isOk();

        // Проверить, что новое состояние - книга возвращена
        issue = issueRepository.get(issue.getId());
        Assertions.assertNotNull(issue.getReturnedAt());
    }

    @Test
    void testIssueBook() {
        Reader reader = readerRepository.create(faker.name().fullName());
        Book book = bookRepository.createBook(faker.book().title());
        IssueRequest issueRequest = new IssueRequest(reader.getId(), book.getId());

        webTestClient.post()
                .uri("/issue")
                .bodyValue(issueRequest)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testIssueBookNotFound() {
        Reader reader = readerRepository.create(faker.name().fullName());
        Book book = bookRepository.createBook(faker.book().title());
        IssueRequest issueRequest = new IssueRequest(reader.getId(), book.getId());
        bookRepository.deleteBook(book);

        webTestClient.post()
                .uri("/issue")
                .bodyValue(issueRequest)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testGetIssue() {
        Reader reader = readerRepository.create(faker.name().fullName());
        Book book = bookRepository.createBook(faker.book().title());
        Issue issue = new Issue(book.getId(), reader.getId());
        issue = issueRepository.save(issue);

        webTestClient.get()
                .uri("/issue/" + issue.getId())
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testGetIssueNotFound() {
        Reader reader = readerRepository.create(faker.name().fullName());
        Book book = bookRepository.createBook(faker.book().title());
        Issue issue = new Issue(book.getId(), reader.getId());
        issue = issueRepository.save(issue);

        webTestClient.get()
                .uri("/issue/" + (issue.getId() + 1))
                .exchange()
                .expectStatus().isNotFound();
    }

    @AllArgsConstructor
    @Data
    static class IssueRequest {
        private long readerId;
        private long bookId;
    }

}
