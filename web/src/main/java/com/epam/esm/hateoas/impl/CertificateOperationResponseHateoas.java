package com.epam.esm.hateoas.impl;

import com.epam.esm.controller.GiftCertificateController;
import com.epam.esm.hateoas.Hateoas;
import com.epam.esm.response.OperationResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * The type Certificate operation response hateoas.
 */
@Component
public class CertificateOperationResponseHateoas implements Hateoas<OperationResponse> {
    private static final List<OperationResponse.Operation> operations = new ArrayList<>();

    static {
        operations.add(OperationResponse.Operation.CREATION);
        operations.add(OperationResponse.Operation.UPDATE);
    }

    @Override
    public void createHateoas(OperationResponse response) {
        response.add(linkTo(methodOn(GiftCertificateController.class).findAllGiftCertificates(0, 0))
                .withSelfRel());

        if (operations.stream().anyMatch(o -> o.getLocalizedOperationName().equalsIgnoreCase(response.getOperation()))) {
            response.add(linkTo(methodOn(GiftCertificateController.class).findCertificateById(findIdFromMessage(response
                    .getMessage()))).withSelfRel());
        }
    }

    private String findIdFromMessage(String message) {
        return message.replaceAll("[^0-9]+", "");
    }
}
