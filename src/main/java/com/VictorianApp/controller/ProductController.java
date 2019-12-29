package com.VictorianApp.controller;

import com.VictorianApp.model.Product;
import com.VictorianApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
@PreAuthorize("hasAnyAuthority('ROLE_ADMINISTRATOR', 'ROLE_SUPERUSER', 'ROLE_DRUKARZ', 'ROLE_PRACOWNIK')")
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

    @GetMapping(path = "/product/{category}/{value}")
    public Optional<Product> getProductByDetail(@PathVariable("category") String category, @PathVariable("value") String value) {
        return productService.getProductByDetail(category, value);
    }

    @GetMapping(path = "/product/categories")
    public List<String> getProductCategories() {
        return productService.getProductCategories();
    }

    @GetMapping(path = "/product/inventory/{id_produktu}")
    public Integer getProductInventoryById(@PathVariable Integer id_produktu) { return productService.getProductInventoryById(id_produktu); }

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

    @PutMapping(path = "/product/inventory")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMINISTRATOR', 'ROLE_SUPERUSER', 'ROLE_DRUKARZ')")
    ResponseEntity<Product> updateProductInventory(@RequestBody Product product) {
        productService.updateProductInventory(product);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping(path = "/product/{id_produktu}")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERUSER', 'ROLE_ADMINISTRATOR')")
    ResponseEntity<Product> delete(@PathVariable Integer id_produktu) {
        productService.delete(id_produktu);
        return ResponseEntity.ok().build();
    }

}

