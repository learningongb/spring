package ru.my.api;

import com.gb.timer.aspect.Timer;
import com.github.javafaker.Faker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final Faker faker;
    private final List<Book> books;

    BookController() {
        List<Book> books = new ArrayList<>();
        faker = new Faker();
        for (int i = 0; i<15; i++) {
            Book book = new Book();
            book.setId(UUID.randomUUID());
            book.setName(faker.book().title());

            Author author = new Author();
            author.setId(UUID.randomUUID());
            author.setLastName(faker.name().lastName());
            author.setFirstName(faker.name().firstName());
            book.setAuthor(author);

            books.add(book);
        }
        this.books = List.copyOf(books);

    }

    @Timer
    @GetMapping()
    public List<Book> getAll() {
        return books;
    }

    @Timer
    @GetMapping("/random")
    public Book getRandom() {
        final int randomIndex = faker.number().numberBetween(0, books.size());
        return books.get(randomIndex);
    }

}
