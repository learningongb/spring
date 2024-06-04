package ru.gb.springdemo.model;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Getter
public class Book {

  public static long sequence = 1L;

  private final long id;
  private final String name;

  public Book(String name) {
    this(sequence++, name);
  }

}
