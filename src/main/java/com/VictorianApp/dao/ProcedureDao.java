package com.VictorianApp.dao;

import com.VictorianApp.model.Procedure;
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
@Repository("psqlProcedure")
public class ProcedureDao implements Dao<Procedure> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static class ProcedureRowMapper implements RowMapper <Procedure> {
        @Override
        public Procedure mapRow(ResultSet result, int numRow) throws SQLException {
            Procedure procedure = new Procedure();
            procedure.setId_zamowienia(result.getInt("id_zamowienia"));
            procedure.setNick(result.getString("nick"));
            procedure.setAdres(result.getString("adres"));
            procedure.setEmail(result.getString("email"));
            procedure.setTelefon(result.getString("telefon"));
            procedure.setData_zamowienia(result.getString("data_zamowienia"));
            procedure.setGotowosc_zamowienia(result.getBoolean("gotowosc_zamowienia"));
            procedure.setId_wysylki(result.getInt("id_wysylki"));
            procedure.setWysylka(result.getString("wysylka"));
            procedure.setOplata(result.getBoolean("oplata"));
            procedure.setData_wysylki(result.getString("data_wysylki"));
            procedure.setCena_zamowienia(result.getFloat("cena_zamowienia"));
            procedure.setZamowienie_express(result.getBoolean("zamowienie_express"));

            return procedure;
        }
    }

    @Override
    public List<Procedure> getAll() {
        final String sqlSelectQuery = "SELECT * FROM procedura p JOIN wysylka w ON p.id_wysylki = w.id_wysylki " +
                "ORDER BY zamowienie_express DESC, data_zamowienia ASC";
        return jdbcTemplate.query(sqlSelectQuery, new ProcedureRowMapper());
    }

    @Override
    public Optional<Procedure> get(int id_zamowienia) {
        final String sqlSelectByIdQuery = "SELECT * FROM procedura p " +
                "JOIN wysylka w ON p.id_wysylki=w.id_wysylki WHERE id_zamowienia = ?";
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(sqlSelectByIdQuery, new ProcedureRowMapper(), id_zamowienia)
        );
    }

    public List<String> getProcedureNicks() {
        final String sqlSelectCategories = "SELECT nick FROM procedura GROUP BY nick";
        return jdbcTemplate.queryForList(sqlSelectCategories, String.class);
    }

    public Optional<Integer> getLatestProcedureId() {
        final String sqlSelectLatestProcedureId = "SELECT id_zamowienia FROM procedura ORDER BY id_zamowienia DESC LIMIT 1";
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(sqlSelectLatestProcedureId, Integer.class));
    }

    public List<Procedure> getProceduresReadyToSend() {
        final String sqlgetProceduresReadyToSend = "SELECT * FROM procedura p " +
                "JOIN wysylka w USING (id_wysylki) " +
                "WHERE gotowosc_zamowienia = TRUE AND data_wysylki IS NULL";

        return jdbcTemplate.query(sqlgetProceduresReadyToSend, new ProcedureRowMapper());
    }

    public List<Procedure> getProceduresAlreadySent() {
        final String sqlgetProceduresAlreadySent = "SELECT * FROM procedura p " +
                "JOIN wysylka w USING (id_wysylki) " +
                "WHERE gotowosc_zamowienia = TRUE AND data_wysylki = CAST(CURRENT_DATE AS VARCHAR)";

        return jdbcTemplate.query(sqlgetProceduresAlreadySent, new ProcedureRowMapper());
    }

    @Override
    public void save(Procedure procedure) {
        final String sqlInsertQuery = "INSERT INTO procedura (nick, adres, email, telefon, data_zamowienia," +
                "gotowosc_zamowienia, id_wysylki, oplata, data_wysylki, zamowienie_express) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?)";

        final String nick = procedure.getNick();
        final String adres = procedure.getAdres();
        final String email = procedure.getEmail();
        final String telefon = procedure.getTelefon();
        final String data_zamowienia = procedure.getData_zamowienia();
        final Boolean gotowosc_zamowienia = procedure.getGotowosc_zamowienia();
        final Integer id_wysylki = procedure.getId_wysylki();
        final Boolean oplata = procedure.getOplata();
        final String data_wysylki = procedure.getData_wysylki();
        final Boolean zamowienie_express = procedure.getZamowienie_express();

        jdbcTemplate.update(sqlInsertQuery, nick, adres, email, telefon, data_zamowienia,
                gotowosc_zamowienia, id_wysylki, oplata, data_wysylki, zamowienie_express);
    }

    @Override
    public void update(Procedure procedure) {
        final String sqlUpdateQuery = "UPDATE procedura set nick = ?, adres = ?, email = ?, telefon = ?," +
                "data_zamowienia = ?, gotowosc_zamowienia = ?, id_wysylki = ?, oplata = ?, data_wysylki = ?, zamowienie_express = ?" +
                "WHERE id_zamowienia = ?";

        final Integer id_zamowienia = procedure.getId_zamowienia();
        final String nick = procedure.getNick();
        final String adres = procedure.getAdres();
        final String email = procedure.getEmail();
        final String telefon = procedure.getTelefon();
        final String data_zamowienia = procedure.getData_zamowienia();
        final Boolean gotowosc_zamowienia = procedure.getGotowosc_zamowienia();
        final Integer id_wysylki = procedure.getId_wysylki();
        final Boolean oplata = procedure.getOplata();
        final String data_wysylki = procedure.getData_wysylki();
        final Boolean zamowienie_express = procedure.getZamowienie_express();

        jdbcTemplate.update(sqlUpdateQuery, nick, adres, email, telefon, data_zamowienia,
                gotowosc_zamowienia, id_wysylki, oplata, data_wysylki, zamowienie_express, id_zamowienia);
    }

    public void updateSendDate(Procedure procedure) {
        final String sqlUpdateQuery = "UPDATE procedura SET data_wysylki = ? " +
                "WHERE id_zamowienia = ?";

        final Integer id_zamowienia = procedure.getId_zamowienia();
        final String data_wysylki = procedure.getData_wysylki();
        jdbcTemplate.update(sqlUpdateQuery, data_wysylki, id_zamowienia);
    }

    @Override
    public void delete(int id_zamowienia) {
        final String sqlDeleteQuery = "DELETE FROM procedura WHERE id_zamowienia = ?";
        jdbcTemplate.update(sqlDeleteQuery, id_zamowienia);
    }

}