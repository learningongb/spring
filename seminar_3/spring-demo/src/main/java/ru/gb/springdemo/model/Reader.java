package ru.gb.springdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "reader")
@Schema(name = "Читатель")
public class Reader {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Schema(name = "Идентификатор")
  private long id;
  @Column(name = "name")
  @Schema(name = "Имя")
  private String name;

}
