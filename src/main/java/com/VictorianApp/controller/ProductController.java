package com.VictorianApp.controller;

import com.VictorianApp.model.Product;
import com.VictorianApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(path = "/products")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping(path = "/product/{id_produktu}")
    public Optional<Product> getProduct(@PathVariable Integer id_produktu) {
        return productService.get(id_produktu);
    }

    @GetMapping(path = "/product/categories")
    public List<String> getProductCategories() {
        return productService.getProductCategories();
    }

    @GetMapping(path = "/product/names")
    public List<String> getProductNames() {
        return productService.getProductNames();
    }

    @GetMapping(path = "/product/{kategoria}/names")
    public List<String> getProductNamesByCategory(@PathVariable String kategoria) {
        return productService.getProductNamesByCategory(kategoria);
    }

    @PostMapping(path = "/product", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Product> save(@Valid @RequestBody Product product) throws URISyntaxException {
        productService.save(product);
        return ResponseEntity.created(new URI("/api/product" + product.getId_produktu() )).body(product);
    }

    @PutMapping(path = "/product")
    ResponseEntity<Product> update(@RequestBody Product product) {
        productService.update(product);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping(path = "/product/{id_produktu}")
    ResponseEntity<Product> delete(@PathVariable Integer id_produktu) {
        productService.delete(id_produktu);
        return ResponseEntity.ok().build();
    }

}
