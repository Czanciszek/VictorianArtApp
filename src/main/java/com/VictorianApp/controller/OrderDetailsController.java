package com.VictorianApp.controller;

import com.VictorianApp.model.OrderDetails;
import com.VictorianApp.model.OrderManageData;
import com.VictorianApp.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class OrderDetailsController {

    @Autowired
    private OrderDetailsService orderDetailsService;

    @GetMapping(path = "/orderdetails")
    public List<OrderDetails> getAll() {
        return orderDetailsService.getAll();
    }

    @GetMapping(path = "/orderdetails/{id_zamowienia}")
    public List<OrderDetails> getOrderDetails(@PathVariable Integer id_zamowienia) {
        return orderDetailsService.getOrderDetails(id_zamowienia);
    }

    @GetMapping(path = "/order/manage/data")
    public List<OrderDetails> getOrderManageData() {
        return orderDetailsService.getOrderManageData();
    }

    @PutMapping(path = "/order/manage/setData")
    ResponseEntity<OrderManageData> updateOrderManageSetData(@RequestBody OrderManageData orderManageData) {
        orderDetailsService.updateOrderManageSetData(orderManageData);
        return ResponseEntity.ok().body(orderManageData);
    }

    @GetMapping(path = "/order/manage/projects")
    public List<OrderDetails> getOrderManageProjects() {
        return orderDetailsService.getOrderManageProjects();
    }

}
