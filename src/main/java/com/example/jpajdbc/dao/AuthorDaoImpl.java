package com.example.jpajdbc.dao;

import com.example.jpajdbc.entities.Author;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
@AllArgsConstructor
@Slf4j
public class AuthorDaoImpl implements AuthorDao {
    private final DataSource dataSource;

    @Override
    public Author getById(Long id) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM author WHERE id = ?")
        ) {
            preparedStatement.setLong(1, id);

            return getAuthor(preparedStatement);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM author WHERE first_name = ? AND last_name = ?")
        ) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);

            return getAuthor(preparedStatement);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    private Author getAuthor(PreparedStatement preparedStatement) throws SQLException {
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getLong("id"));
                author.setFirstName(resultSet.getString("first_name"));
                author.setLastName(resultSet.getString("last_name"));

                return author;
            }
        }
        return null;
    }
}
