package com.epam.esm.hateoas.impl;

import com.epam.esm.controller.UserController;
import com.epam.esm.dto.GiftCertificate;
import com.epam.esm.dto.Order;
import com.epam.esm.dto.User;
import com.epam.esm.hateoas.Hateoas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * The type Order hateoas.
 */
@Component
public class OrderHateoas implements Hateoas<Order> {
    private final Hateoas<GiftCertificate> certificateHateoas;
    private final Hateoas<User> userHateoas;

    /**
     * Instantiates a new Order hateoas.
     *
     * @param certificateHateoas the certificate hateoas
     * @param userHateoas        the user hateoas
     */
    @Autowired
    public OrderHateoas(Hateoas<GiftCertificate> certificateHateoas, Hateoas<User> userHateoas) {
        this.certificateHateoas = certificateHateoas;
        this.userHateoas = userHateoas;
    }

    @Override
    public void createHateoas(Order order) {
        if (order.getLinks().isEmpty()) {
            order.add(linkTo(methodOn(UserController.class).findUserOrders(String.valueOf(order.getUser().getId()),
                    0, 0)).withSelfRel());

            order.add(linkTo(methodOn(UserController.class).findUserOrder(String.valueOf(order.getUser().getId()),
                    String.valueOf(order.getId()))).withSelfRel());
        }

        if (order.getGiftCertificate().getLinks().isEmpty()) {
            certificateHateoas.createHateoas(order.getGiftCertificate());
        }

        if (order.getUser().getLinks().isEmpty()) {
            userHateoas.createHateoas(order.getUser());
        }
    }
}
