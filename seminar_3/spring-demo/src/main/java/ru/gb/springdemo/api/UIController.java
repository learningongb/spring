package ru.gb.springdemo.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.IssueResponseRepository;
import ru.gb.springdemo.repository.ReaderRepository;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/ui")
public class UIController {

    final private BookRepository bookRepository;
    final private ReaderRepository readerRepository;
    final private IssueResponseRepository issueResponseRepository;
    final private IssueRepository issueRepository;

    @GetMapping("/books")
    public String getBooks(Model model) {
        model.addAttribute("books", bookRepository.getBooks());
        return "books";
    }

    @GetMapping("/readers")
    public String getReaders(Model model) {
        model.addAttribute("readers", readerRepository.retReaders());
        return "readers";
    }

    @GetMapping("/issues")
    public String gerIssues(Model model) {
        model.addAttribute("issues", issueResponseRepository.getList(issueRepository.getIssues()));
        return "issues";
    }

    @GetMapping("/reader/{id}")
    public String getReaderById(@PathVariable long id, Model model) {
        Reader reader = readerRepository.getReaderById(id);
        if (reader == null)
            return "404";
        model.addAttribute("reader", reader);
        model.addAttribute("issues", issueResponseRepository.getList(issueRepository.readersIssues(id)));
        return "onereader";
    }

}
