package com.VictorianApp.dao;

import com.VictorianApp.model.OrderDetails;
import com.VictorianApp.model.OrderManageData;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Getter
@Setter
@Repository("psqlOrderDetails")
public class OrderDetailsImplDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static class OrderDetailsRowMapper implements RowMapper<OrderDetails> {
        @Override
        public OrderDetails mapRow(ResultSet result, int numRow) throws SQLException {
            OrderDetails orderDetails = new OrderDetails();

            orderDetails.setNick(result.getString("nick"));
            orderDetails.setAdres(result.getString("adres"));
            orderDetails.setEmail(result.getString("email"));
            orderDetails.setTelefon(result.getString("telefon"));
            orderDetails.setData_zamowienia(result.getString("data_zamowienia"));
            orderDetails.setGotowosc_zamowienia(result.getBoolean("gotowosc_zamowienia"));
            orderDetails.setOplata(result.getBoolean("oplata"));
            orderDetails.setData_wysylki(result.getString("data_wysylki"));

            orderDetails.setId_zamowienia(result.getInt("id_zamowienia"));
            orderDetails.setId_produktu(result.getInt("id_produktu"));
            orderDetails.setIlosc(result.getInt("ilosc"));
            orderDetails.setData_danych(result.getString("data_danych"));
            orderDetails.setData_projektu(result.getString("data_projektu"));
            orderDetails.setData_zatwierdzenia(result.getString("data_zatwierdzenia"));
            orderDetails.setData_wydrukowania(result.getString("data_wydrukowania"));
            orderDetails.setData_wykonania(result.getString("data_wykonania"));
            orderDetails.setGotowosc(result.getBoolean("gotowosc"));
            orderDetails.setCena_zamowienia(result.getFloat("cena_zamowienia"));
            orderDetails.setPersonalizacja(result.getBoolean("personalizacja"));

            orderDetails.setNazwa(result.getString("nazwa"));
            orderDetails.setKategoria(result.getString("kategoria"));
            orderDetails.setCena(result.getFloat("cena"));
            orderDetails.setTyp(result.getInt("typ"));
            orderDetails.setStan_magazynu(result.getInt("stan_magazynu"));

            orderDetails.setId_wysylki(result.getInt("id_wysylki"));
            orderDetails.setWysylka(result.getString("wysylka"));

            return orderDetails;
        }
    }

    public static class OrderDetailsManageRowMapper implements RowMapper<OrderDetails> {
        @Override
        public OrderDetails mapRow(ResultSet result, int numRow) throws SQLException {
            OrderDetails orderDetails = new OrderDetails();

            orderDetails.setId_zamowienia(result.getInt("id_zamowienia"));
            orderDetails.setId_produktu(result.getInt("id_produktu"));

            orderDetails.setNick(result.getString("nick"));
            orderDetails.setData_zamowienia(result.getString("data_zamowienia"));
            orderDetails.setData_wysylki(result.getString("data_wysylki"));

            orderDetails.setData_danych(result.getString("data_danych"));
            orderDetails.setData_projektu(result.getString("data_projektu"));
            orderDetails.setData_zatwierdzenia(result.getString("data_zatwierdzenia"));
            orderDetails.setData_wydrukowania(result.getString("data_wydrukowania"));
            orderDetails.setData_wykonania(result.getString("data_wykonania"));

            orderDetails.setNazwa(result.getString("nazwa"));
            orderDetails.setTyp(result.getInt("typ"));

            return orderDetails;
        }
    }

    public List<OrderDetails> getAll() {
        final String sqlSelectQuery = "SELECT * FROM procedura pr " +
                "JOIN zamowienie z ON z.id_zamowienia=pr.id_zamowienia " +
                "JOIN produkt p ON z.id_produktu=p.id_produktu " +
                "JOIN wysylka w ON pr.id_wysylki=w.id_wysylki";
        return jdbcTemplate.query(sqlSelectQuery, new OrderDetailsRowMapper());
    }

    public List<OrderDetails> getOrderDetails(int id_zamowienia) {
        final String sqlSelectByIdQuery = "SELECT * FROM zamowienie z " +
                "JOIN produkt p ON z.id_produktu=p.id_produktu " +
                "JOIN procedura pr ON z.id_zamowienia=pr.id_zamowienia " +
                "JOIN wysylka w ON pr.id_wysylki=w.id_wysylki " +
                "WHERE z.id_zamowienia = ?";
        return jdbcTemplate.query(sqlSelectByIdQuery, new OrderDetailsRowMapper(), id_zamowienia);
    }

    public List<OrderDetails> getMissingProducts() {
        final String sqlSelectMissingProducts = "SELECT * FROM zamowienie z " +
                "JOIN produkt p ON z.id_produktu=p.id_produktu " +
                "JOIN procedura pr ON z.id_zamowienia=pr.id_zamowienia " +
                "JOIN wysylka w ON pr.id_wysylki=w.id_wysylki " +
                "WHERE typ = 0 AND gotowosc = FALSE " +
                "ORDER BY data_zamowienia";

        return jdbcTemplate.query(sqlSelectMissingProducts, new OrderDetailsRowMapper());
    }

    public List<OrderDetails> getOrderManageProductAtStart(String data_typ_przed) {
        final String sqlSelectOrderManageData = "SELECT * " +
                "FROM procedura pr JOIN zamowienie z USING (id_zamowienia) JOIN produkt USING (id_produktu) " +
                "WHERE typ > 0 AND " + data_typ_przed + " IS NULL ORDER BY data_zamowienia";
        return jdbcTemplate.query(sqlSelectOrderManageData, new OrderDetailsManageRowMapper());
    }

    public List<OrderDetails> getOrderManageProductAtEnd() {
        final String sqlSelectOrderManageData = "SELECT * " +
                "FROM procedura pr JOIN zamowienie z USING (id_zamowienia) JOIN produkt USING (id_produktu) " +
                "WHERE typ > 0 AND data_wykonania IS NOT NULL AND data_wysylki IS NULL ORDER BY data_zamowienia";
        return jdbcTemplate.query(sqlSelectOrderManageData, new OrderDetailsManageRowMapper());
    }

    public List<OrderDetails> getOrderManageProduct(String data_typ_przed, String data_typ_po) {
        final String sqlSelectOrderManageData = "SELECT * " +
                "FROM procedura pr JOIN zamowienie z USING (id_zamowienia) JOIN produkt USING (id_produktu) " +
                "WHERE typ > 0 AND " + data_typ_przed + " IS NOT NULL AND " + data_typ_po + " IS NULL " +
                "ORDER BY data_zamowienia";
        return jdbcTemplate.query(sqlSelectOrderManageData, new OrderDetailsManageRowMapper());
    }

    public void updateOrderManageSetTypeOneDataCurrentDate(OrderManageData orderManageData) {
        final String data_typ = orderManageData.data_typ_przed;
        final String sqlUpdateOrderManageData = "UPDATE zamowienie SET " +
                data_typ + " = CURRENT_DATE, data_projektu = '---', data_zatwierdzenia = '---' " +
                "WHERE id_zamowienia = ? AND id_produktu = ?";

        final Integer id_zamowienia = orderManageData.id_zamowienia;
        final Integer id_produktu = orderManageData.id_produktu;

        jdbcTemplate.update(sqlUpdateOrderManageData, id_zamowienia, id_produktu);
    }

    public void updateOrderManageSetTypeOneDataNull(OrderManageData orderManageData) {
        final String sqlUpdateOrderManageData = "UPDATE zamowienie SET " +
                "data_zatwierdzenia = NULL, data_projektu = NULL, data_danych = NULL " +
                "WHERE id_zamowienia = ? AND id_produktu = ?";

        final Integer id_zamowienia = orderManageData.id_zamowienia;
        final Integer id_produktu = orderManageData.id_produktu;

        jdbcTemplate.update(sqlUpdateOrderManageData, id_zamowienia, id_produktu);
    }

    public void updateOrderManageSetTypeTwoDataCurrentDate(OrderManageData orderManageData) {
        final String data_typ = orderManageData.data_typ_przed;
        final String sqlUpdateOrderManageData = "UPDATE zamowienie SET " +
                data_typ + " = CURRENT_DATE WHERE id_zamowienia = ? AND id_produktu = ?";

        final Integer id_zamowienia = orderManageData.id_zamowienia;
        final Integer id_produktu = orderManageData.id_produktu;

        jdbcTemplate.update(sqlUpdateOrderManageData, id_zamowienia, id_produktu);
    }

    public void updateOrderManageSetTypeTwoDataNull(OrderManageData orderManageData) {
        final String data_typ = orderManageData.data_typ_przed;
        final String sqlUpdateOrderManageData = "UPDATE zamowienie SET " +
                data_typ + " = NULL WHERE id_zamowienia = ? AND id_produktu = ?";

        final Integer id_zamowienia = orderManageData.id_zamowienia;
        final Integer id_produktu = orderManageData.id_produktu;

        jdbcTemplate.update(sqlUpdateOrderManageData, id_zamowienia, id_produktu);
    }
}