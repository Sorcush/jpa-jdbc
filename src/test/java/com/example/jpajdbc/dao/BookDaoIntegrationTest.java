package com.example.jpajdbc.dao;

import com.example.jpajdbc.entities.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"com.example.jpajdbc.dao"})
class BookDaoIntegrationTest {

    @Autowired
    private BookDao bookDao;

    @Test
    void testFindById() {
        Book book = bookDao.findById(1L);
        assertThat(book).isNotNull();
    }

    @Test
    void testGetByTitle() {
        Book book = bookDao.findByTitle("Clean Code");
        assertThat(book).isNotNull();
    }
    @Test
    void testSave() {
        Book book = new Book("Hobbit", "123445", "Publisher", 1L);
        Book savedBook = bookDao.save(book);
        assertThat(savedBook).isNotNull();
    }

    @Test
    void testUpdate() {
        Book book = new Book("Hobbit", "123445", "Publisher", 1L);
        Book savedBook = bookDao.save(book);
        savedBook.setIsbn("666666");
        Book updatedBook = bookDao.update(savedBook);

        assertThat(updatedBook.getIsbn()).isEqualTo("666666");
    }

    @Test
    void testDelete() {
        Book book = new Book("Hobbit", "123445", "Publisher", 1L);
        Book savedBook = bookDao.save(book);
        bookDao.delete(savedBook.getId());
        savedBook = bookDao.findById(savedBook.getId());
        assertThat(savedBook).isNull();
    }

}