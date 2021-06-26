package com.epam.esm.service.impl;

import com.epam.esm.dao.UserDao;
import com.epam.esm.dao.constant.ErrorAttribute;
import com.epam.esm.dao.constant.Symbol;
import com.epam.esm.dto.GiftCertificate;
import com.epam.esm.dto.Order;
import com.epam.esm.dto.User;
import com.epam.esm.exception.InvalidFieldException;
import com.epam.esm.exception.ResourceNotFoundException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.OrderService;
import com.epam.esm.service.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService<User> {
    private final UserDao<User> dao;
    private final GiftCertificateService<GiftCertificate> certificateService;
    private final OrderService<Order> orderService;

    @Autowired
    public UserServiceImpl(UserDao<User> dao, GiftCertificateService<GiftCertificate> certificateService,
                           OrderService<Order> orderService) {
        this.dao = dao;
        this.certificateService = certificateService;
        this.orderService = orderService;
    }

    @Override
    public User findById(String id) {
        try {
            return dao.findById(Long.parseLong(id)).orElseThrow(() -> new ResourceNotFoundException(
                    ErrorAttribute.USER_ERROR_CODE, ErrorAttribute.RESOURCE_NOT_FOUND_ERROR, id));
        } catch (NumberFormatException e) {
            throw new InvalidFieldException(ErrorAttribute.USER_ERROR_CODE, ErrorAttribute.INVALID_USER_ID_ERROR, id);
        }
    }

    @Override
    public List<User> findAll(int page, int elements) {
        List<User> users = dao.findAll(page, elements);
        if (CollectionUtils.isEmpty(users)) {
            throw new ResourceNotFoundException(ErrorAttribute.USER_ERROR_CODE, ErrorAttribute.RESOURCE_NOT_FOUND_ERROR,
                    page + Symbol.COMMA + Symbol.SPACE + elements);
        }
        return users;
    }

    @Override
    public List<User> findWithGiftCertificates(int page, int elements) {
        List<User> users = dao.findWithGiftCertificates(page, elements);
        if (CollectionUtils.isEmpty(users)) {
            throw new ResourceNotFoundException(ErrorAttribute.USER_ERROR_CODE, ErrorAttribute.RESOURCE_NOT_FOUND_ERROR,
                    page + Symbol.COMMA + Symbol.SPACE + elements);
        }
        return users;
    }

    @Override
    public long createOrder(String userId, String certificateId) {
        try {
            User user = findById(userId);
            GiftCertificate certificate = certificateService.findById(certificateId);
            Order order = createOrder(certificate);
            order.setUser(user);
            return orderService.insert(order);
        } catch (NumberFormatException e) {
            throw new InvalidFieldException(ErrorAttribute.USER_ERROR_CODE, ErrorAttribute.INVALID_ID, userId +
                    Symbol.COMMA + Symbol.SPACE + certificateId);
        }
    }

    private Order createOrder(GiftCertificate certificate) {
        Order order = new Order();
        order.setPrice(certificate.getPrice());
        order.setTimestamp(LocalDateTime.now());
        order.setGiftCertificate(certificate);
        return order;
    }
}
