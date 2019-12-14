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

    @GetMapping(path = "/order/manageProductAtStart/{data_typ_przed}")
    public List<OrderDetails> getOrderManageProductAtStart(@PathVariable String data_typ_przed) {
        return orderDetailsService.getOrderManageProductAtStart(data_typ_przed);
    }

    @GetMapping(path = "/order/manageProduct/{data_typ_przed}/{data_typ_po}")
    public List<OrderDetails> getOrderManageProduct(@PathVariable("data_typ_przed") String data_typ_przed,
                                                    @PathVariable("data_typ_po") String data_typ_po) {
        return orderDetailsService.getOrderManageProduct(data_typ_przed, data_typ_po);
    }

    @PutMapping(path = "/order/manage/setDataCurrentDate")
    ResponseEntity<OrderManageData> updateOrderManageSetDataCurrentDate(@RequestBody OrderManageData orderManageData) {
        orderDetailsService.updateOrderManageSetDataCurrentDate(orderManageData);
        return ResponseEntity.ok().body(orderManageData);
    }

    @PutMapping(path = "/order/manage/setDataNull")
    ResponseEntity<OrderManageData> updateOrderManageSetDataNull(@RequestBody OrderManageData orderManageData) {
        orderDetailsService.updateOrderManageSetDataNull(orderManageData);
        return ResponseEntity.ok().body(orderManageData);
    }

}
