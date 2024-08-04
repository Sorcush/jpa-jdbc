package com.example.jpajdbc.dao;

import com.example.jpajdbc.entities.Author;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
@AllArgsConstructor
@Slf4j
public class AuthorDaoImpl implements AuthorDao {
    private final DataSource dataSource;

    @Override
    public Author getById(Long id) {
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM author WHERE id = " + id)
        ) {
            if (resultSet.next()) {
                Author author = new Author();
                author.setId(id);
                author.setFirstName(resultSet.getString("first_name"));
                author.setLastName(resultSet.getString("last_name"));

                return author;
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
