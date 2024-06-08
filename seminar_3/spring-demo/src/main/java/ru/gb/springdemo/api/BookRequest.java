package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "Запрос создания книги")
@Data
public class BookRequest {
    @Schema(name = "Название")
    private String name;
}
