package com.epam.esm.dao;

import com.epam.esm.dto.Order;

public interface OrderDao<T extends Order> {
    long insert(Order order);
}
