package com.epam.esm.hateoas.impl;

import com.epam.esm.controller.TagController;
import com.epam.esm.hateoas.ControllerHateoas;
import com.epam.esm.response.OperationResponse;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OperationResponseHateoas implements ControllerHateoas<OperationResponse> {
    @Override
    public void createHateoas(OperationResponse response) {
        response.add(linkTo(methodOn(TagController.class).findAllTags()).withSelfRel());
        if (response.getOperation().equalsIgnoreCase(OperationResponse.Operation.CREATION.toString())) {
            response.add(linkTo(methodOn(TagController.class).findTagById(findIdFromMessage(response.getMessage())))
                    .withSelfRel());
        }
    }

    private String findIdFromMessage(String message) {
        return message.replaceAll("[^0-9]+", "");
    }
}
