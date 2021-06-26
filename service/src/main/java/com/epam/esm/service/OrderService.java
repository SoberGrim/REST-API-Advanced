package com.epam.esm.service;

import com.epam.esm.dto.Order;

public interface OrderService <T extends Order> {
    long insert(Order order);


}
