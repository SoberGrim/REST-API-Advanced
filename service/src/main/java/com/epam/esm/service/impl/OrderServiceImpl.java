package com.epam.esm.service.impl;

import com.epam.esm.dao.OrderDao;
import com.epam.esm.dto.Order;
import com.epam.esm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService<Order> {
    private final OrderDao<Order> dao;

    @Autowired
    public OrderServiceImpl(OrderDao<Order> dao) {
        this.dao = dao;
    }

    @Override
    public long insert(Order order) {
        return dao.insert(order);
    }
}
