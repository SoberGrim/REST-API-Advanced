package com.epam.esm.hateoas.impl;

import com.epam.esm.controller.GiftCertificateController;
import com.epam.esm.hateoas.Hateoas;
import com.epam.esm.response.OperationResponse;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * The type Order operation response hateoas.
 */
@Component
public class OrderOperationResponseHateoas implements Hateoas<OperationResponse> {
    @Override
    public void createHateoas(OperationResponse response) {
        response.add(linkTo(methodOn(GiftCertificateController.class).findAllGiftCertificates(0, 0))
                .withSelfRel());
    }
}
