package com.epam.esm.hateoas.impl;

import com.epam.esm.controller.GiftCertificateController;
import com.epam.esm.dto.GiftCertificate;
import com.epam.esm.hateoas.Hateoas;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GiftCertificateHateoas implements Hateoas<GiftCertificate> {
    @Override
    public void createHateoas(GiftCertificate model) {
        model.add(linkTo(methodOn(GiftCertificateController.class).findAllGiftCertificates(0, 0))
                .withSelfRel());

        model.add(linkTo(methodOn(GiftCertificateController.class).findCertificateById(String.valueOf(model.getId())))
                .withSelfRel());
    }
}
