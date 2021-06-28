package com.epam.esm.hateoas.impl;

import com.epam.esm.controller.UserController;
import com.epam.esm.dto.User;
import com.epam.esm.hateoas.Hateoas;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * The type User hateoas.
 */
@Component
public class UserHateoas implements Hateoas<User> {

    @Override
    public void createHateoas(User user) {
        if (user.getLinks().isEmpty()) {
            user.add(linkTo(methodOn(UserController.class).findAllUsers(0, 0)).withSelfRel());
            user.add(linkTo(methodOn(UserController.class).findUserById(String.valueOf(user.getId()))).withSelfRel());
        }
    }
}
