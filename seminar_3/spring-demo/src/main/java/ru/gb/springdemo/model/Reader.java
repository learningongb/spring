package ru.gb.springdemo.model;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Getter
public class Reader {

  public static long sequence = 1L;

  private final long id;
  private final String name;

  public Reader(String name) {
    this(sequence++, name);
  }

}
