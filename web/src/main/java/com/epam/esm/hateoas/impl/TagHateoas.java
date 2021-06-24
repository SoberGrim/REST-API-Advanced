package com.epam.esm.hateoas.impl;

import com.epam.esm.controller.TagController;
import com.epam.esm.dto.Tag;
import com.epam.esm.hateoas.Hateoas;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TagHateoas implements Hateoas<Tag> {
    @Override
    public void createHateoas(Tag tag) {
        tag.add(linkTo(methodOn(TagController.class).findAllTags()).withSelfRel());
        tag.add(linkTo(methodOn(TagController.class).findTagById(String.valueOf(tag.getId()))).withSelfRel());
    }
}
