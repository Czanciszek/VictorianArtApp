package com.VictorianApp.dao;

import com.VictorianApp.model.Order;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Getter
@Setter
@Repository("psqlOrder")
public class OrderDao implements Dao<Order> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static class OrderRowMapper implements RowMapper <Order> {
        @Override
        public Order mapRow(ResultSet result, int numRow) throws SQLException {
            Order order = new Order();
            order.setId_zamowienia(result.getInt("id_zamowienia"));
            order.setId_produktu(result.getInt("id_produktu"));
            order.setIlosc(result.getInt("ilosc"));
            order.setData_danych(result.getString("data_danych"));
            order.setData_projektu(result.getString("data_projektu"));
            order.setData_zatwierdzenia(result.getString("data_zatwierdzenia"));
            order.setData_wydrukowania(result.getString("data_wydrukowania"));
            order.setData_wykonania(result.getString("data_wykonania"));
            order.setGotowosc(result.getBoolean("gotowosc"));

            return order;
        }
    }

    public static class OrderSumRowMapper implements RowMapper <Order> {
        @Override
        public Order mapRow(ResultSet result, int numRow) throws SQLException {
            Order order = new Order();
            order.setId_zamowienia(result.getInt("id_zamowienia"));
            order.setIlosc_produktow(result.getInt("ilosc_produktow"));
            order.setCena_zamowienia(result.getFloat("cena_zamowienia"));

            return order;
        }
    }

    @Override
    public List<Order> getAll() {
        final String sqlSelectQuery = "SELECT * FROM zamowienie";
        return jdbcTemplate.query(sqlSelectQuery, new OrderRowMapper());
    }

    @Override
    public Optional<Order> get(int id) {
        final String sqlSelectByIdQuery = "SELECT * FROM zamowienie WHERE id_zamowienia = ?";
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(sqlSelectByIdQuery, new OrderRowMapper(), id)
        );
    }

    public List<Order> getOrder(int id) {
        final String sqlSelectByIdQuery = "SELECT * FROM zamowienie WHERE id_zamowienia = ?";
        return jdbcTemplate.query(sqlSelectByIdQuery, new OrderRowMapper(), id);
    }

    public Optional<Float> getSumPriceByOrder(int id_zamowienia) {
        final String sqlSelectSumPriceByOrder = "SELECT SUM(cena*ilosc) FROM zamowienie z" +
                " JOIN produkt p ON z.id_produktu=p.id_produktu WHERE id_zamowienia = ?";
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(sqlSelectSumPriceByOrder, Float.class, id_zamowienia)
        );
    }

    public List<Order> getSumPrice() {
        final String sqlSelectSumPrice = "SELECT z.id_zamowienia, COUNT(z.id_produktu) AS ilosc_produktow, " +
                "SUM(cena*ilosc) AS cena_zamowienia FROM zamowienie z " +
                "JOIN produkt p ON z.id_produktu=p.id_produktu " +
                "JOIN procedura pr ON z.id_zamowienia=pr.id_zamowienia " +
                "GROUP BY z.id_zamowienia, data_zamowienia " +
                "ORDER BY data_zamowienia, z.id_zamowienia LIMIT 25";
        return jdbcTemplate.query(sqlSelectSumPrice, new OrderSumRowMapper());
    }

    @Override
    public void save(Order order) {
        final String sqlInsertQuery = "INSERT INTO zamowienie (id_zamowienia, id_produktu, ilosc," +
                "data_danych, data_projektu, data_zatwierdzenia, data_wydrukowania, data_wykonania, gotowosc) " +
                "VALUES (?,?,?,?,?,?,?,?,?)";

        final Integer id_zamowienia = order.getId_zamowienia();
        final Integer id_produktu = order.getId_produktu();
        final Integer ilosc = order.getIlosc();
        final String data_danych = order.getData_danych();
        final String data_projektu = order.getData_projektu();
        final String data_zatwierdzenia = order.getData_zatwierdzenia();
        final String data_wydrukowania = order.getData_wydrukowania();
        final String data_wykonania = order.getData_wykonania();
        final Boolean gotowosc = order.getGotowosc();

        jdbcTemplate.update(sqlInsertQuery, id_zamowienia, id_produktu, ilosc, data_danych, data_projektu,
                data_zatwierdzenia, data_wydrukowania, data_wykonania, gotowosc);
    }

    @Override
    public void update(Order order) {
        final String sqlUpdateQuery = "UPDATE zamowienie set id_produktu = ?, ilosc = ?, data_danych = ?, data_projektu = ?, " +
                "data_ zatwierdzenia = ?, data_wydrukowania = ?, data_wykonania = ?, gotowosc = ? WHERE id_zamowienia = ?";

        final Integer id_zamowienia = order.getId_zamowienia();
        final Integer id_produktu = order.getId_produktu();
        final Integer ilosc = order.getIlosc();
        final String data_danych = order.getData_danych();
        final String data_projektu = order.getData_projektu();
        final String data_zatwierdzenia = order.getData_zatwierdzenia();
        final String data_wydrukowania = order.getData_wydrukowania();
        final String data_wykonania = order.getData_wykonania();
        final Boolean gotowosc = order.getGotowosc();

        jdbcTemplate.update(sqlUpdateQuery, id_produktu, ilosc, data_danych, data_projektu,
                data_zatwierdzenia, data_wydrukowania, data_wykonania, gotowosc, id_zamowienia);
    }

    @Override
    public void delete(int id_zamowienia) {
        final String sqlDeleteQuery = "DELETE FROM zamowienie WHERE id_zamowienia = ?";
        jdbcTemplate.update(sqlDeleteQuery, id_zamowienia);
    }

}

