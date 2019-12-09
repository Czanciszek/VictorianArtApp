package com.VictorianApp.dao;

import com.VictorianApp.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Repository("psqlUser")
public class UserDao implements Dao<User> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static class UserRowMapper implements RowMapper <User> {
        @Override
        public User mapRow(ResultSet result, int numRow) throws SQLException {
            User user = new User();
            user.setId(result.getInt("id"));
            user.setLogin(result.getString("login"));
            user.setHaslo(result.getString("haslo"));
            user.setImie(result.getString("imie"));
            user.setNazwisko(result.getString("nazwisko"));
            user.setRola(result.getString("rola"));
            return user;
        }
    }

    @Override
    public List<User> getAll() {
        final String sqlSelectQuery = "SELECT * FROM konto";
        return jdbcTemplate.query(sqlSelectQuery, new UserRowMapper());
    }

    @Override
    public Optional<User> get(int id) {
        final String sqlSelectByIdQuery = "SELECT * FROM konto WHERE id = ?";
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(sqlSelectByIdQuery, new UserRowMapper(), id)
        );
    }

    public Optional<User> getUserByLogin(String login) {
        final String sqlSelectByLoginQuery = "SELECT * FROM KONTO WHERE login = ?";
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(sqlSelectByLoginQuery, new UserRowMapper(), login)
        );
    }

    @Override
    public void save(User user) {
        final String sqlInsertQuery = "INSERT INTO konto (login, haslo, imie, nazwisko, rola) " +
                "VALUES (?,?,?,?,?)";

        final String login = user.getLogin();
        final String haslo = user.getHaslo();
        final String imie = user.getImie();
        final String nazwisko = user.getNazwisko();
        final String rola = user.getRola();
        jdbcTemplate.update(sqlInsertQuery, login, haslo, imie, nazwisko, rola );
    }

    @Override
    public void update(User user) {
        final String sqlUpdateQuery = "UPDATE konto set login = ?, haslo = ?, imie = ?, nazwisko = ?, rola = ? " +
                "WHERE id = ?";

        final Integer id = user.getId();
        final String login = user.getLogin();
        final String haslo = user.getHaslo();
        final String imie = user.getImie();
        final String nazwisko = user.getNazwisko();
        final String rola = user.getRola();

        jdbcTemplate.update(sqlUpdateQuery, new Object[]{login, haslo, imie, nazwisko, rola, id});
    }

    @Override
    public void delete(int id) {
        final String sqlDeleteQuery = "DELETE FROM konto WHERE id = ?";
        jdbcTemplate.update(sqlDeleteQuery, id);
    }

}

