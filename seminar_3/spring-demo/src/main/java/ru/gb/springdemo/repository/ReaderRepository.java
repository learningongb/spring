package ru.gb.springdemo.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.interfaces.IReaderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ReaderRepository {

  private final IReaderRepository repository;

  public ReaderRepository(IReaderRepository repository) {
      this.repository = repository;
  }

  public Reader getReaderById(long id) {
    return repository.findById(id).stream().findFirst().orElse(null);

  }

  public void deleteReader(Reader reader) {
    repository.delete(reader);
  }

  public Reader create(String name) {
    Reader reader = new Reader();
    reader.setName(name);
    reader = repository.save(reader);
    return reader;
  }


  public List<Reader> getReaders() {
    return repository.findAll().stream().toList();
  }
}
