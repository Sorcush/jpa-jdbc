package com.example.jpajdbc.repositories;

import com.example.jpajdbc.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
