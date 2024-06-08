package ru.gb.springdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Запись о факте выдачи книги (в БД)
 */
@Data
@Entity
@Table(name = "issue")
@Schema(name = "Выдача книги")
public class Issue {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Schema(name = "Идентификатор")
  private Long id;
  @Column(name = "bookid")
  @Schema(name = "Идентификатор книги")
  private Long bookId;
  @Column(name = "readerid")
  @Schema(name = "Идентификатор читателя")
  private Long readerId;

  /**
   * Дата выдачи
   */
  @Column(name = "issuedat")
  @Schema(name = "Дата выдачи")
  private final LocalDateTime issuedAt;
  /**
   * Дата возврата
   */
  @Column(name = "returnedat")
  @Schema(name = "Дата возврата")
  private LocalDateTime returnedAt;

  public Issue() {this.issuedAt = LocalDateTime.now();}

  public Issue(Long bookId, Long readerId) {
    this.bookId = bookId;
    this.readerId = readerId;
    this.issuedAt = LocalDateTime.now();
  }
}
