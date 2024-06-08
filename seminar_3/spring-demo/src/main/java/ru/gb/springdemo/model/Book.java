package ru.gb.springdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
@Table(name = "Book")
@Getter
@Schema(name = "Книга")
public class Book {

//  public static long sequence = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Schema(name = "Идентификатор")
  private long id;
  @Column(name = "name")
  @Schema(name = "Наименование")
  private String name;

}
