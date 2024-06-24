package ru.gb.springdemo.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.gb.springdemo.JUnitSpringBootBase;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.BookRepository;

public class BookControllerTest extends JUnitSpringBootBase {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    BookRepository bookRepository;

    @Test
    void testFindById() {

        Book book = bookRepository.createBook("book1");

        webTestClient.get()
                .uri("/book/" + book.getId())
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testFindByIdNotFound() {

        Book book = bookRepository.createBook("book2");
        long id = bookRepository.getBooks().stream().map(Book::getId).max(Long::compare).orElse(0L);
        id++;

        webTestClient.get()
                .uri("/book/" + id)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteById() {
        Book book = bookRepository.createBook("book3");
        long id = book.getId();

        webTestClient.delete()
                .uri("/book/" + id)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void testCreateBook() {

        BookRequest bookRequest = new BookRequest();
        bookRequest.setName("Book4");

        webTestClient.post()
                .uri("/book")
                .bodyValue(bookRequest)
                .exchange()
                .expectStatus().isOk();
    }
}
