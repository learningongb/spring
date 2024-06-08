package ru.gb.springdemo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.interfaces.IBookRepository;

import java.util.List;

@Repository
public class BookRepository {

  @Autowired
  private final IBookRepository repository;

  public BookRepository(IBookRepository repository) {
      this.repository = repository;
  }

  public Book getBookById(long id) {
    return repository.findById(id).stream().findFirst().orElse(null);
  }

  public void deleteBook(Book book) {
    repository.delete(book);
  }

  public Book createBook(String name) {
    Book book = new Book();
    book.setName(name);
    book = repository.save(book);
    return book;
  }

  public List<Book> getBooks() {
    return repository.findAll().stream().toList();
  }

}
