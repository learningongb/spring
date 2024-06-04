package ru.gb.springdemo.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IssueResponse {

    private final long id;
    private final String book;
    private final String reader;
    private final String issuedAt;
    private final String returnedAt;

}
