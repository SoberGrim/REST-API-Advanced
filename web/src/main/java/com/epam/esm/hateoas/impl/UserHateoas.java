package com.epam.esm.hateoas.impl;

import com.epam.esm.controller.UserController;
import com.epam.esm.dto.GiftCertificate;
import com.epam.esm.dto.User;
import com.epam.esm.hateoas.Hateoas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserHateoas implements Hateoas<User> {
    private final Hateoas<GiftCertificate> certificateHateoas;

    @Autowired
    public UserHateoas(Hateoas<GiftCertificate> certificateHateoas) {
        this.certificateHateoas = certificateHateoas;
    }

    @Override
    public void createHateoas(User user) {
        user.add(linkTo(methodOn(UserController.class).findAllUsers(0, 0)).withSelfRel());
        user.add(linkTo(methodOn(UserController.class).findUserById(String.valueOf(user.getId()))).withSelfRel());
        user.getOrders().forEach(o -> certificateHateoas.createHateoas(o.getGiftCertificate()));
        // TODO: 6/26/2021 add orders hateoas
    }
}
