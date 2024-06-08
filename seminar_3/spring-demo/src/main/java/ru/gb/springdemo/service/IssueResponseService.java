package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.api.IssueResponse;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class IssueResponseService {
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final DataSource dataSource;

    public List<IssueResponse> getList() {
        final String sql = "Select issue.id, book.name as book, reader.name as reader, issue.issuedat, issue.returnedat from issue inner join book on issue.bookid = book.id  inner join reader on issue.readerid = reader.id";
        return getListFromQuery(sql, new Object[0]);
    }

    public List<IssueResponse> getList(Long readerId) {
        final String sql = "Select issue.id, book.name as book, reader.name as reader, issue.issuedat, issue.returnedat from issue inner join book on issue.bookid = book.id  inner join reader on issue.readerid = reader.id where issue.readerid = ?";
        return getListFromQuery(sql, new Object[] {readerId});
    }

    private List<IssueResponse> getListFromQuery(String sql, Object[] parameters) {
        JdbcOperations template = new JdbcTemplate(dataSource);
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        List<IssueResponse> newList = new ArrayList<>();
        for (Map<String, Object> stringObjectMap : template.query(sql, parameters, new ColumnMapRowMapper())) {
            LocalDateTime issuedAt = (LocalDateTime) stringObjectMap.get("issuedAt");
            LocalDateTime returnedAt = (LocalDateTime) stringObjectMap.get("returnedAt");
            newList.add(new IssueResponse(
                    (Long)stringObjectMap.get("id"),
                    (String)stringObjectMap.get("book"),
                    (String)stringObjectMap.get("reader"),
                    issuedAt.format(formatter),
                    returnedAt != null ? returnedAt.format(formatter) : "-"));
        }
        return newList;
    }
}
