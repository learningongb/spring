package ru.gb.springdemo.model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Запись о факте выдачи книги (в БД)
 */
@Data
@Entity
@Table(name = "issue")
public class Issue {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(name = "bookid")
  private Long bookId;
  @Column(name = "readerid")
  private Long readerId;

  /**
   * Дата выдачи
   */
  @Column(name = "issuedat")
  private final LocalDateTime issuedAt;
  /**
   * Дата возврата
   */
  @Column(name = "returnedat")
  private LocalDateTime returnedAt;

  public Issue() {this.issuedAt = LocalDateTime.now();}

  public Issue(Long bookId, Long readerId) {
    this.bookId = bookId;
    this.readerId = readerId;
    this.issuedAt = LocalDateTime.now();
  }
}
