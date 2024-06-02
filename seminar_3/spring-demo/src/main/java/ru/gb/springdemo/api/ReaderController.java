package ru.gb.springdemo.api;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.service.BookService;
import ru.gb.springdemo.service.ReaderService;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/reader")
public class ReaderController {

    private static final Logger log = LogManager.getLogger(ReaderController.class);
    @Autowired
    private ReaderService readerService;

    @GetMapping("/{id}")
    public ResponseEntity<Reader> getReader(@PathVariable long id) {
        log.info("Получен запрос на поиск читателя id={" + id + "}");

        Reader reader;
        try {
            reader = readerService.getReader(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(reader);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reader> DeleteReader(@PathVariable long id) {
        log.info("Получен запрос на удаление читателя id={" + id + "}");

        try {
            readerService.deleteReader(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    public ResponseEntity<Reader> createReader(@RequestBody ReaderRequest readerRequest) {
        final Reader reader = readerService.createReader(readerRequest);
        return ResponseEntity.ok(reader);
    }

    @GetMapping("/{id}/issue")
    public ResponseEntity<List<Issue>> getReaderIssues(@PathVariable long id) {
        log.info("Получен запрос на получение выдач читателя id={" + id + "}");
        try {
            Reader reader = readerService.getReader(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

        List<Issue> readersIssues = readerService.issues(id);
        return ResponseEntity.ok().body(readersIssues);

    }
}
