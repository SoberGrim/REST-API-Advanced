package com.epam.esm.service.impl;

import com.epam.esm.dao.OrderDao;
import com.epam.esm.dao.constant.ErrorAttribute;
import com.epam.esm.dao.constant.Symbol;
import com.epam.esm.dto.GiftCertificate;
import com.epam.esm.dto.Order;
import com.epam.esm.dto.User;
import com.epam.esm.exception.DeleteCertificateInUseException;
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

/**
 * The type Order service.
 */
@Service
public class OrderServiceImpl implements OrderService<Order> {
    private final OrderDao<Order> dao;
    private final UserService<User> userService;
    private final GiftCertificateService<GiftCertificate> certificateService;

    /**
     * Instantiates a new Order service.
     *
     * @param dao                the dao
     * @param userService        the user service
     * @param certificateService the certificate service
     */
    @Autowired
    public OrderServiceImpl(OrderDao<Order> dao, UserService<User> userService,
                            GiftCertificateService<GiftCertificate> certificateService) {
        this.dao = dao;
        this.userService = userService;
        this.certificateService = certificateService;
    }

    @Override
    public List<Order> findByUserId(int page, int elements, String id) {
        return dao.findByUserId(page, elements, userService.findById(id));
    }

    @Override
    public long createOrder(String userId, String certificateId) {
        try {
            User user = userService.findById(userId);
            GiftCertificate certificate = certificateService.findById(certificateId);
            Order order = createOrder(certificate);
            order.setUser(user);
            return dao.insert(order);
        } catch (NumberFormatException e) {
            throw new InvalidFieldException(ErrorAttribute.USER_ERROR_CODE, ErrorAttribute.INVALID_ID, userId +
                    Symbol.COMMA + Symbol.SPACE + certificateId);
        }
    }

    @Override
    public Order findByUserIdAndOrderId(String userId, String orderId) {
        return dao.findByUserIdAndOrderId(userService.findById(userId).getId(), findById(orderId).getId()).orElseThrow(
                () -> new ResourceNotFoundException(ErrorAttribute.USER_ERROR_CODE,
                        ErrorAttribute.RESOURCE_NOT_FOUND_ERROR, userId + Symbol.COMMA + Symbol.SPACE + orderId));
    }

    @Override
    public Order findById(String id) {
        try {
            return dao.findById(Long.parseLong(id)).orElseThrow(() -> new ResourceNotFoundException(
                    ErrorAttribute.ORDER_ERROR_CODE, ErrorAttribute.RESOURCE_NOT_FOUND_ERROR, id));
        } catch (NumberFormatException e) {
            throw new InvalidFieldException(ErrorAttribute.ORDER_ERROR_CODE, ErrorAttribute.INVALID_ORDER_ID, id);
        }
    }

    @Override
    public boolean deleteByCertificateId(String certificateId) {
        GiftCertificate certificate = certificateService.findById(certificateId);
        if (LocalDateTime.now().isAfter(certificate.getCreateDate().plusDays(certificate.getDuration())) ||
                CollectionUtils.isEmpty(dao.findByCertificateId(certificate.getId()))) {
            return dao.deleteByCertificateId(certificate.getId());
        }
        throw new DeleteCertificateInUseException(ErrorAttribute.ORDER_ERROR_CODE,
                ErrorAttribute.CERTIFICATE_IN_USE_ERROR, certificateId);
    }

    private Order createOrder(GiftCertificate certificate) {
        Order order = new Order();
        order.setPrice(certificate.getPrice());
        order.setTimestamp(LocalDateTime.now());
        order.setGiftCertificate(certificate);
        return order;
    }
}