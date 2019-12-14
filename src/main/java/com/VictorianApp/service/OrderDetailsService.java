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

    public List<OrderDetails> getOrderManageProductAtStart(String data_typ_przed) {
        return this. orderDetailsImplDao.getOrderManageProductAtStart(data_typ_przed);
    }

    public List<OrderDetails> getOrderManageProduct(String data_typ_przed, String data_typ_po) {
        return this. orderDetailsImplDao.getOrderManageProduct(data_typ_przed, data_typ_po);
    }

    public void updateOrderManageSetDataCurrentDate(OrderManageData orderManageData) {
        this.orderDetailsImplDao.updateOrderManageSetDataCurrentDate(orderManageData);
    }

    public void updateOrderManageSetDataNull(OrderManageData orderManageData) {
        this.orderDetailsImplDao.updateOrderManageSetDataNull(orderManageData);
    }


}
