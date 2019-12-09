package com.VictorianApp.service;

import com.VictorianApp.dao.ProductDao;
import com.VictorianApp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    @Qualifier("psqlProduct")
    private ProductDao productDao;

    public List<Product> getAll() {
        return this.productDao.getAll();
    }

    public Optional<Product> get(int id_produktu) {
        return this.productDao.get(id_produktu);
    }

    public List<String> getProductCategories() { return this.productDao.getProductCategories(); }

    public List<String> getProductNames() { return this.productDao.getProductNames(); }

    public List<String> getProductNamesByCategory(String kategoria) {
        return this.productDao.getProductNamesByCategory(kategoria); }

    public void save( Product product) {
        this.productDao.save(product);
    }

    public void update(Product product) { this.productDao.update(product);}

    public void delete(int id_produktu) { this.productDao.delete(id_produktu);}

}