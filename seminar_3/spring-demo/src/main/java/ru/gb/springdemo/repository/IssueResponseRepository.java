package ru.gb.springdemo.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.api.IssueResponse;
import ru.gb.springdemo.model.Issue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class IssueResponseRepository {
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    public List<IssueResponse> getList(List<Issue> issues) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        List<IssueResponse> newList = new ArrayList<>();
        for (Issue issue : issues) {
            LocalDateTime returnedAt = issue.getReturnedAt();
            newList.add(new IssueResponse(issue.getId(),
                    bookRepository.getBookById(issue.getBookId()).getName(),
                    readerRepository.getReaderById(issue.getReaderId()).getName(),
                    issue.getIssuedAt().format(formatter),
                    returnedAt != null ? returnedAt.format(formatter) : "-"));
        }
        return newList;
    }
}
