package com.example.jpajdbc.dao;

import com.example.jpajdbc.entities.Author;

public interface AuthorDao {
    Author getById(Long id);
}
