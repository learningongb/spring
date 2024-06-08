package ru.gb.springdemo.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.springdemo.model.Reader;

public interface IReaderRepository extends JpaRepository<Reader, Long>  {
}
