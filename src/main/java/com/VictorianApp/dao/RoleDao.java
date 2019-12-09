package com.VictorianApp.dao;

import com.VictorianApp.model.Role;
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
@Repository("psqlRole")
public class RoleDao implements Dao<Role> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static class RoleRowMapper implements RowMapper <Role> {
        @Override
        public Role mapRow(ResultSet result, int numRow) throws SQLException {
            Role role = new Role();
            role.setId(result.getInt("id"));
            role.setRola(result.getString("rola"));
            return role;
        }
    }

    @Override
    public List<Role> getAll() {
        final String sqlSelectQuery = "SELECT * FROM rola";
        return jdbcTemplate.query(sqlSelectQuery, new RoleRowMapper());
    }

    @Override
    public Optional<Role> get(int id) {
        final String sqlSelectByIdQuery = "SELECT * FROM rola WHERE id = ?";
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(sqlSelectByIdQuery, new RoleRowMapper(), id)
        );
    }

    public List<String> getRoles() {
        final String sqlSelectRoles = "SELECT rola FROM rola";
        return jdbcTemplate.queryForList(sqlSelectRoles, String.class);
    }

    @Override
    public void save(Role role) {
        final String sqlInsertQuery = "INSERT INTO rola (rola) VALUES (?)";

        final String rola = role.getRola();

        jdbcTemplate.update(sqlInsertQuery, rola );
    }

    @Override
    public void update(Role role) {
        final String sqlUpdateQuery = "UPDATE rola set rola = ? WHERE id = ?";

        final Integer id = role.getId();
        final String rola = role.getRola();

        jdbcTemplate.update(sqlUpdateQuery, rola, id );
    }

    @Override
    public void delete(int id) {
        final String sqlDeleteQuery = "DELETE FROM rola WHERE id = ?";
        jdbcTemplate.update(sqlDeleteQuery, id);
    }

}

