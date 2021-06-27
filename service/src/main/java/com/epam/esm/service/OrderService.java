package com.epam.esm.service;

import com.epam.esm.dto.Order;

import java.util.List;

public interface OrderService<T extends Order> {
    List<T> findByUserId(int page, int elements, String id);

    long createOrder(String userId, String certificateId);

    T findByUserIdAndOrderId(String userId, String orderId);

    T findById(String id);

    boolean deleteByCertificateId(String certificateId);
}
