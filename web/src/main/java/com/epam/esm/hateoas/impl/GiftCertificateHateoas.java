package com.epam.esm.hateoas.impl;

import com.epam.esm.controller.GiftCertificateController;
import com.epam.esm.dto.GiftCertificate;
import com.epam.esm.dto.Tag;
import com.epam.esm.hateoas.Hateoas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * The type Gift certificate hateoas.
 */
@Component
public class GiftCertificateHateoas implements Hateoas<GiftCertificate> {
    private final Hateoas<Tag> tagHateoas;

    /**
     * Instantiates a new Gift certificate hateoas.
     *
     * @param tagHateoas the tag hateoas
     */
    @Autowired
    public GiftCertificateHateoas(Hateoas<Tag> tagHateoas) {
        this.tagHateoas = tagHateoas;
    }

    @Override
    public void createHateoas(GiftCertificate certificate) {
        if (certificate.getLinks().isEmpty()) {
            certificate.add(linkTo(methodOn(GiftCertificateController.class).findAllGiftCertificates(0, 0))
                    .withSelfRel());

            certificate.add(linkTo(methodOn(GiftCertificateController.class).findCertificateById(
                    String.valueOf(certificate.getId()))).withSelfRel());

            certificate.getTags().stream()
                    .filter(t -> t.getLinks().isEmpty())
                    .forEach(tagHateoas::createHateoas);
        }
    }
}
