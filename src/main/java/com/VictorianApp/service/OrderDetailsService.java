package com.VictorianApp.service;

import com.VictorianApp.dao.OrderDetailsImplDao;
import com.VictorianApp.model.OrderDetails;
import com.VictorianApp.model.OrderManageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsService {

    @Autowired
    @Qualifier("psqlOrderDetails")
    private OrderDetailsImplDao orderDetailsImplDao;

    public List<OrderDetails> getAll() {
        return this.orderDetailsImplDao.getAll();
    }

    public List<OrderDetails> getOrderDetails(int id_zamowienia) {
        return this.orderDetailsImplDao.getOrderDetails(id_zamowienia);
    }

    public List<OrderDetails> getMissingProducts() {
        return this.orderDetailsImplDao.getMissingProducts();
    }

    public List<OrderDetails> getOrderManageProductAtStart(String data_typ_przed) {
        return this. orderDetailsImplDao.getOrderManageProductAtStart(data_typ_przed);
    }

    public List<OrderDetails> getOrderManageProduct(String data_typ_przed, String data_typ_po) {
        return this. orderDetailsImplDao.getOrderManageProduct(data_typ_przed, data_typ_po);
    }

    public List<OrderDetails> getOrderManageProductAtEnd() {
        return this.orderDetailsImplDao.getOrderManageProductAtEnd();
    }

    public void updateOrderManageSetTypeOneDataCurrentDate(OrderManageData orderManageData) {
        this.orderDetailsImplDao.updateOrderManageSetTypeOneDataCurrentDate(orderManageData);
    }

    public void updateOrderManageSetTypeOneDataNull(OrderManageData orderManageData) {
        this.orderDetailsImplDao.updateOrderManageSetTypeOneDataNull(orderManageData);
    }

    public void updateOrderManageSetTypeTwoDataCurrentDate(OrderManageData orderManageData) {
        this.orderDetailsImplDao.updateOrderManageSetTypeTwoDataCurrentDate(orderManageData);
    }

    public void updateOrderManageSetTypeTwoDataNull(OrderManageData orderManageData) {
        this.orderDetailsImplDao.updateOrderManageSetTypeTwoDataNull(orderManageData);
    }


}
