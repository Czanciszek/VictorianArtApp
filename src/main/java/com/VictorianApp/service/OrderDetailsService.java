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

    public List<OrderDetails> getOrderManageData() {
        return this. orderDetailsImplDao.getOrderManageData();
    }

    public void  updateOrderManageSetData(OrderManageData orderManageData) {
        this.orderDetailsImplDao.updateOrderManageSetData(orderManageData);
    }

    public List<OrderDetails> getOrderManageProjects() {
        return this. orderDetailsImplDao.getOrderManageProjects();
    }
}
