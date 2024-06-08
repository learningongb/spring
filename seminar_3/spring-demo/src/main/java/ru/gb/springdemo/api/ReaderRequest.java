package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Запрос создания читателя")
public class ReaderRequest {
    @Schema(name = "Имя")
    private String name;
}
