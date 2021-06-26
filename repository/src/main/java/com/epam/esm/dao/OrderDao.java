package com.epam.esm.dao;

import com.epam.esm.dto.Order;
import com.epam.esm.dto.User;

import java.util.List;
import java.util.Optional;

public interface OrderDao<T extends Order> {
    long insert(T order);

    List<T> findByUserId(int page, int elements, User user);

    Optional<T> findById(long id);

    Optional<T> findByUserIdAndOrderId(long certificateId, long orderId);
}
