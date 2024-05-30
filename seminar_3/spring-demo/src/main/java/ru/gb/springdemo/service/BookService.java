package ru.gb.springdemo.service;

import org.springframework.stereotype.Service;
import ru.gb.springdemo.api.BookRequest;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.BookRepository;

import java.util.NoSuchElementException;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getBook(long id) {
        Book book = bookRepository.getBookById(id);
        if (book == null) {
            throw new NoSuchElementException("Не найдена книга по ID \"" + id + "\"");
        }
        return book;
    }

    public Book createBook(BookRequest bookRequest) {
        return bookRepository.createBook(bookRequest.getName());
    }

    public void deleteBook(long id) {
        Book book = bookRepository.getBookById(id);
        if (book == null) {
            throw new NoSuchElementException("Не найдена книга по ID \"" + id + "\"");
        }
        bookRepository.deleteBook(book);
    }

    public Book createBook(String name) {
        return bookRepository.createBook(name);
    }
}
