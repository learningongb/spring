package ru.gb.springdemo.service;

import org.springframework.stereotype.Service;
import ru.gb.springdemo.api.BookRequest;
import ru.gb.springdemo.api.ReaderRequest;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReaderService {

    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    public ReaderService(ReaderRepository readerRepository, IssueRepository issueRepository) {
        this.readerRepository = readerRepository;
        this.issueRepository = issueRepository;
    }

    public Reader getReader(long id) {
        Reader reader = readerRepository.getReaderById(id);
        if (reader == null) {
            throw new NoSuchElementException("Не найден читатель по ID \"" + id + "\"");
        }
        return reader;
    }

    public Reader createReader(ReaderRequest readerRequest) {
        return readerRepository.create(readerRequest.getName());
    }

    public void deleteReader(long id) {
        readerRepository.deleteReader(getReader(id));
    }

    public Reader createReader(String name) {
        return readerRepository.create(name);
    }

    public List<Issue> issues(long id) {
        return issueRepository.readersIssues(id);
    }
}
