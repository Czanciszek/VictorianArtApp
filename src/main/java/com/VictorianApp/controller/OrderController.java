package com.VictorianApp.controller;

import com.VictorianApp.model.Order;
import com.VictorianApp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
@PreAuthorize("hasAnyAuthority('ROLE_ADMINISTRATOR', 'ROLE_SUPERUSER', 'ROLE_DRUKARZ', 'ROLE_PRACOWNIK')")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(path = "/orders")
    public List<Order> getAll() {
        return orderService.getAll();
    }

    @GetMapping(path = "/order/temp/{id}")
    public Optional<Order> get(@PathVariable Integer id) {
        return orderService.get(id);
    }

    @GetMapping(path = "/order/{id}")
    public List<Order> getOrder(@PathVariable Integer id) {
        return orderService.getOrder(id);
    }

    @GetMapping(path = "/order/sumPrice/{id_zamowienia}")
    public Optional<Float> getSumPriceByOrder(@PathVariable Integer id_zamowienia) {
        return orderService.getSumPriceByOrder(id_zamowienia);
    }

    @GetMapping(path = "/order/sumPrice")
    public List<Order> getSumPrice() {
        return orderService.getSumPrice();
    }

    @PostMapping(path = "/order", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Order> save(@Valid @RequestBody Order order) throws URISyntaxException {
        orderService.save(order);
        return ResponseEntity.created(new URI("/api/order" + order.getId_zamowienia() )).body(order);
    }

    @PutMapping(path = "/order")
    ResponseEntity<Order> update(@RequestBody Order order) {
        orderService.update(order);
        return ResponseEntity.ok().body(order);
    }

    @PutMapping(path = "/product/updateProductOrder")
    ResponseEntity<Order> updateProductOrder(@RequestBody Order order) {
        orderService.updateProductOrder(order);
        return ResponseEntity.ok().body(order);
    }

    @DeleteMapping(path = "/order/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERUSER', 'ROLE_ADMINISTRATOR')")
    ResponseEntity<Order> delete(@PathVariable Integer id) {
        orderService.delete(id);
        return ResponseEntity.ok().build();
    }

}
