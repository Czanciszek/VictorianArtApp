package com.VictorianApp.service;

import com.VictorianApp.dao.OrderDao;
import com.VictorianApp.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    @Qualifier("psqlOrder")
    private OrderDao orderDao;

    public List<Order> getAll() {
        return this.orderDao.getAll();
    }

    public Optional<Order> get(int id) {
        return this.orderDao.get(id);
    }

    public List<Order> getOrder(int id_zamowienia) {
        return this.orderDao.getOrder(id_zamowienia);
    }

    public Optional<Float> getSumPriceByOrder(int id_zamowienia) {
        return this.orderDao.getSumPriceByOrder(id_zamowienia);
    }

    public List<Order> getSumPrice() {
        return this.orderDao.getSumPrice();
    }

    public void save( Order order) {
        this.orderDao.save(order);
    }

    public void update(Order order) { this.orderDao.update(order);}

    public void delete(int id) { this.orderDao.delete(id);}
}