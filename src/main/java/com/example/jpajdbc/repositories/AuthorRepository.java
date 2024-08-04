package com.example.jpajdbc.repositories;

import com.example.jpajdbc.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
