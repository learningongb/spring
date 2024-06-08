package ru.gb.springdemo.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Book;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {



}
