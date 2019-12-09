package com.VictorianApp.dao;

import com.VictorianApp.model.SendMethod;
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
@Repository("psqlSendMethod")
public class SendMethodDao implements Dao<SendMethod> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static class SendMethodRowMapper implements RowMapper <SendMethod> {
        @Override
        public SendMethod mapRow(ResultSet result, int numRow) throws SQLException {
            SendMethod sendMethod = new SendMethod();
            sendMethod.setId_wysylki(result.getInt("id_wysylki"));
            sendMethod.setWysylka(result.getString("wysylka"));
            sendMethod.setCena(result.getFloat("cena"));
            return sendMethod;
        }
    }

    @Override
    public List<SendMethod> getAll() {
        final String sqlSelectQuery = "SELECT * FROM wysylka";
        return jdbcTemplate.query(sqlSelectQuery, new SendMethodRowMapper());
    }

    @Override
    public Optional<SendMethod> get(int id_wysylki) {
        final String sqlSelectByIdQuery = "SELECT * FROM wysylka WHERE id_wysylki = ?";
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(sqlSelectByIdQuery, new SendMethodRowMapper(), id_wysylki)
        );
    }

    @Override
    public void save(SendMethod sendMethod) {
        final String sqlInsertQuery = "INSERT INTO wysylka (id_wysylki, wysylka, cena) " +
                "VALUES (?,?,?)";

        final Integer id_wysylki = sendMethod.getId_wysylki();
        final String wysylka = sendMethod.getWysylka();
        final Float cena = sendMethod.getCena();

        jdbcTemplate.update(sqlInsertQuery, id_wysylki, wysylka, cena);
    }

    @Override
    public void update(SendMethod sendMethod) {
        final String sqlUpdateQuery = "UPDATE wysylka set wysylka = ?, cena = ? " +
                "WHERE id_wysylki = ?";

        final Integer id_wysylki = sendMethod.getId_wysylki();
        final String wysylka = sendMethod.getWysylka();
        final Float cena = sendMethod.getCena();

        jdbcTemplate.update(sqlUpdateQuery, wysylka, cena, id_wysylki);
    }

    @Override
    public void delete(int id_wysylki) {
        final String sqlDeleteQuery = "DELETE FROM wysylka WHERE id_wysylki = ?";
        jdbcTemplate.update(sqlDeleteQuery, id_wysylki);
    }

}

