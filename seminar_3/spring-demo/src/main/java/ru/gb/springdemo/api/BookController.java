package ru.gb.springdemo.api;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.service.BookService;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/book")
public class BookController {

    private static final Logger log = LogManager.getLogger(BookController.class);
    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable long id) {
        log.info("Получен запрос на поиск книги id={" + id + "}");

        Book book;
        try {
            book = bookService.getBook(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> DeleteBook(@PathVariable long id) {
        log.info("Получен запрос на удаление книги id={" + id + "}");

        try {
            bookService.deleteBook(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookRequest bookRequest) {
        final Book book = bookService.createBook(bookRequest);
        return ResponseEntity.ok(book);
    }
}
