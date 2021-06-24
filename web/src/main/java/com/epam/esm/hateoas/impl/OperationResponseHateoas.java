package com.epam.esm.hateoas.impl;

import com.epam.esm.attribute.ResponseAttribute;
import com.epam.esm.controller.TagController;
import com.epam.esm.hateoas.Hateoas;
import com.epam.esm.response.OperationResponse;
import com.epam.esm.util.MessageLocale;
import org.springframework.stereotype.Component;

import java.util.ResourceBundle;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OperationResponseHateoas implements Hateoas<OperationResponse> {
    @Override
    public void createHateoas(OperationResponse response) {
        response.add(linkTo(methodOn(TagController.class).findAllTags()).withSelfRel());
        if (response.getOperation().equalsIgnoreCase(ResourceBundle.getBundle(ResponseAttribute.PROPERTY_FILE_NAME,
                MessageLocale.getCurrent()).getString(ResponseAttribute.CREATION_OPERATION))) {
            response.add(linkTo(methodOn(TagController.class).findTagById(findIdFromMessage(response.getMessage())))
                    .withSelfRel());
        }
    }

    private String findIdFromMessage(String message) {
        return message.replaceAll("[^0-9]+", "");
    }
}
