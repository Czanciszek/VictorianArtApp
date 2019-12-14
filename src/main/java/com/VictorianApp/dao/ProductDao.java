package com.VictorianApp.dao;

import com.VictorianApp.model.Product;
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
@Repository("psqlProduct")
public class ProductDao implements Dao<Product> {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet result, int numRow) throws SQLException {
            Product product = new Product();
            product.setId_produktu(result.getInt("id_produktu"));
            product.setNazwa(result.getString("nazwa"));
            product.setKategoria(result.getString("kategoria"));
            product.setTyp(result.getInt("typ"));
            product.setPodatek_vat(result.getInt("podatek_vat"));
            product.setRyczalt(result.getFloat("ryczalt"));
            product.setStan_magazynu(result.getInt("stan_magazynu"));
            product.setCena(result.getFloat("cena"));

            return product;
        }
    }

    @Override
    public List<Product> getAll() {
        final String sqlSelectQuery = "SELECT * FROM produkt ORDER BY stan_magazynu";
        return jdbcTemplate.query(sqlSelectQuery, new ProductRowMapper());
    }

    @Override
    public Optional<Product> get(int id_produktu) {
        final String sqlSelectByIdQuery = "SELECT * FROM produkt WHERE id_produktu = ?";
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(sqlSelectByIdQuery, new ProductRowMapper(), id_produktu)
        );
    }

    public List<String> getProductCategories() {
        final String sqlSelectCategories = "SELECT kategoria FROM produkt GROUP BY kategoria";
        return jdbcTemplate.queryForList(sqlSelectCategories, String.class);
    }

    public Integer getProductInventoryById(Integer id_produktu) {
        final String sqlSelectProductInventoryById = "SELECT stan_magazynu FROM produkt WHERE id_produktu = ?";
        return jdbcTemplate.queryForObject(sqlSelectProductInventoryById, Integer.class, id_produktu);
    }

    public List<String> getProductNames() {
        final String sqlSelectCategories = "SELECT nazwa FROM produkt GROUP BY nazwa";
        return jdbcTemplate.queryForList(sqlSelectCategories, String.class);
    }

    public List<String> getProductNamesByCategory(String kategoria) {
        final String sqlSelectCategories = "SELECT nazwa FROM produkt WHERE kategoria = ? GROUP BY nazwa";
        return jdbcTemplate.queryForList(sqlSelectCategories, String.class, kategoria);
    }

    @Override
    public void save(Product product) {
        final String sqlInsertQuery = "INSERT INTO produkt" +
                "(nazwa, kategoria, typ, podatek_vat, ryczalt, stan_magazynu, cena) " +
                "VALUES (?,?,?,?,?,?,?)";

        final String nazwa = product.getNazwa();
        final String kategoria = product.getKategoria();
        final Integer typ = product.getTyp();
        final Integer podatek_vat = product.getPodatek_vat();
        final Float ryczalt = product.getRyczalt();
        final Integer stan_magazynu = product.getStan_magazynu();
        final Float cena = product.getCena();
        jdbcTemplate.update(sqlInsertQuery, nazwa, kategoria, typ, podatek_vat, ryczalt, stan_magazynu, cena);
    }

    @Override
    public void update(Product product) {
        final String sqlUpdateQuery = "UPDATE produkt SET nazwa = ?, kategoria = ?, typ = ?," +
                "podatek_vat = ?, ryczalt = ?, stan_magazynu = ?, cena = ? " +
                "WHERE id_produktu = ?";

        final Integer id_produktu = product.getId_produktu();
        final String nazwa = product.getNazwa();
        final String kategoria = product.getKategoria();
        final Integer typ = product.getTyp();
        final Integer podatek_vat = product.getPodatek_vat();
        final Float ryczalt = product.getRyczalt();
        final Integer stan_magazynu = product.getStan_magazynu();
        final Float cena = product.getCena();

        jdbcTemplate.update(sqlUpdateQuery, nazwa, kategoria, typ, podatek_vat, ryczalt, stan_magazynu, cena, id_produktu);
    }

    public void updateProductInventory(Product product) {

        final String sqlUpdateProductInventory = "UPDATE produkt SET stan_magazynu = ? " +
                "WHERE id_produktu = ?";

        final Integer id_produktu = product.getId_produktu();
        final Integer stan_magazynu = product.getStan_magazynu();

        jdbcTemplate.update(sqlUpdateProductInventory, stan_magazynu, id_produktu);
    }

    @Override
    public void delete(int id_produktu) {
        final String sqlDeleteQuery = "DELETE FROM produkt WHERE id_produktu = ?";
        jdbcTemplate.update(sqlDeleteQuery, id_produktu);
    }
}
