package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Запрос на выдачу
 */
@Data
@Schema(name = "Запрос выдачи книги")
public class IssueRequest {

  /**
   * Идентификатор читателя
   */
  @Schema(name = "Идентификатор читателя")
  private long readerId;

  /**
   * Идентификатор книги
   */
  @Schema(name = "Идентификатор книги")
  private long bookId;

}
