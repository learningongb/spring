package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Книга")
public class BookController {

    private static final Logger log = LogManager.getLogger(BookController.class);
    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    @Operation(summary = "get one book", description = "Ищет книгу по идентификатору")
    public ResponseEntity<Book> getBook(@PathVariable long id) {
        log.info("Получен запрос на поиск книги id={" + id + "}");

        Book book;
        try {
            book = bookService.getBook(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete one book", description = "Удаляет книгу по идентификатору")
    @ApiResponses(@ApiResponse(responseCode = "204"))
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
    @Operation(summary = "create one book", description = "Создает книгу")
    public ResponseEntity<Book> createBook(@RequestBody BookRequest bookRequest) {
        final Book book = bookService.createBook(bookRequest);
        return ResponseEntity.ok(book);
    }
}
