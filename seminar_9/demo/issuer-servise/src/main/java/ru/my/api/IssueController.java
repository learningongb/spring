package ru.my.api;

import com.gb.timer.aspect.Timer;
import com.github.javafaker.Faker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.my.BookProvider;

import java.time.ZoneId;
import java.util.*;

@Timer
@RestController
@RequestMapping("/api/issue")
public class IssueController {

    private final Faker faker;
    private final List<Issue> issues;
    private final BookProvider bookProvider;

    public IssueController(BookProvider bookProvider) {
        this.issues = new ArrayList<>();
        this.faker = new Faker();
        this.bookProvider = bookProvider;
        refreshData();
    }

    private void refreshData() {
        issues.clear();
        for (int i = 0; i<15; i++) {
            Issue issue = new Issue();
            issue.setId(UUID.randomUUID());
            issue.setBookId(bookProvider.getRandomBookId());
            issue.setReaderId(UUID.randomUUID());
            Date between = faker.date().between(startOfYear(), endOfYear());
            issue.setIssuedAt(between.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

            issues.add(issue);
        }
    }

    private Date startOfYear() {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR, 2024);
        instance.set(Calendar.MONTH, Calendar.JANUARY);
        instance.set(Calendar.DAY_OF_MONTH, 1);
        return instance.getTime();
    }

    private Date endOfYear() {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR, 2024);
        instance.set(Calendar.MONTH, Calendar.DECEMBER);
        instance.set(Calendar.DAY_OF_MONTH, 31);
        return instance.getTime();
    }

    @GetMapping()
    public List<Issue> getAll() {
        return issues;
    }

    @GetMapping("/refresh")
    public List<Issue> refresh() {
        refreshData();
        return issues;
    }
}
