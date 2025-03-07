package com.example.jpajdbc.dao;

import com.example.jpajdbc.entities.Author;
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
class AuthorDaoIntegrationTest {
    @Autowired
    private AuthorDao authorDao;

    @Test
    void testFindById() {
        Author author = authorDao.findById(1L);
        assertThat(author).isNotNull();
    }

    @Test
    void testGetByName() {
        Author author = authorDao.findByName("Craig", "Walls");
        assertThat(author).isNotNull();
    }

    @Test
    void testSave() {
        Author author = new Author("Michael", "Jackson");
        Author savedAuthor = authorDao.save(author);
        assertThat(savedAuthor).isNotNull();
    }

    @Test
    void testUpdate() {
        Author author = new Author("Michael", "Jackson");
        Author savedAuthor = authorDao.save(author);
        savedAuthor.setLastName("Jordan");
        Author updatedAuthor = authorDao.update(savedAuthor);

        assertThat(updatedAuthor.getLastName()).isEqualTo("Jordan");
    }

    @Test
    void testDelete() {
        Author author = new Author("Michael", "Jackson");
        Author savedAuthor = authorDao.save(author);
        authorDao.delete(savedAuthor.getId());
        savedAuthor = authorDao.findById(savedAuthor.getId());
        assertThat(savedAuthor).isNull();
    }
}