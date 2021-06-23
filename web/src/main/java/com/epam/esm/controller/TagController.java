package com.epam.esm.controller;

import com.epam.esm.attribute.OperationResponseAttribute;
import com.epam.esm.dto.Tag;
import com.epam.esm.hateoas.ControllerHateoas;
import com.epam.esm.response.OperationResponse;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagService<Tag> service;
    private final ControllerHateoas<Tag> hateoas;

    @Autowired
    public TagController(TagService<Tag> service, ControllerHateoas<Tag> hateoas) {
        this.service = service;
        this.hateoas = hateoas;
    }

    @GetMapping
    public List<Tag> findAllTags() {
        List<Tag> tags = service.findAll();
        tags.forEach(hateoas::createHateoas);
        return tags;
    }

    @GetMapping("/{id}")
    public Tag findTagById(@PathVariable String id) {
        Tag tag = service.findById(id);
        hateoas.createHateoas(tag);
        return tag;
    }

    @DeleteMapping("/{id}")
    public OperationResponse deleteTag(@PathVariable String id) {
        service.delete(id);
        return new OperationResponse(OperationResponse.Operation.DELETION,
                OperationResponseAttribute.TAG_DELETE_OPERATION, id);
    }

    @PostMapping("/new")
    public OperationResponse createTag(@RequestBody Tag tag) {
        service.insert(tag);
        hateoas.createHateoas(tag);
        return new OperationResponse(OperationResponse.Operation.CREATION,
                OperationResponseAttribute.TAG_CREATE_OPERATION, tag.toString());
    }// TODO: 6/23/2021 fix

  /*  @PostMapping("/new")
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {
        service.insert(tag);
        hateoas.createHateoas(tag);
        return ResponseEntity.status(HttpStatus.CREATED).body(tag);
    }*/
}
