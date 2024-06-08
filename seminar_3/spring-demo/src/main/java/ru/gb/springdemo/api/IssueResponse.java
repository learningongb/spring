package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "Информация о выдаче книги")
public class IssueResponse {

    @Schema(name = "Идентификатор выдачи")
    private final long id;
    @Schema(name = "Название книги")
    private final String book;
    @Schema(name = "Имя читателя")
    private final String reader;
    @Schema(name = "Дата выдачи")
    private final String issuedAt;
    @Schema(name = "Дата возврата")
    private final String returnedAt;

}
