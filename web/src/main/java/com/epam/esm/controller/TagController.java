package com.epam.esm.controller;

import com.epam.esm.dto.Tag;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type Tag controller.
 */
@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagService<Tag> service;

    /**
     * Instantiates a new Tag controller.
     *
     * @param service the service
     */
    @Autowired
    public TagController(TagService<Tag> service) {
        this.service = service;
    }

    /**
     * Find all tags list.
     *
     * @return the list
     */
    @GetMapping
    public List<Tag> findAllTags() {
        return service.findAll();
    }

    /**
     * Find tag by id tag.
     *
     * @param id the id
     * @return the tag
     */
    @GetMapping("/{id}")
    public Tag findTagById(@PathVariable String id) {
        return service.findById(id);
    }

    /**
     * Delete tag response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTag(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Tag deleted successfully" +
                " (id = " + id + ")");
    }

    /**
     * Create tag response entity.
     *
     * @param tag the tag
     * @return the response entity
     */
    @PostMapping("/new")
    public ResponseEntity<String> createTag(@RequestBody Tag tag) {
        service.insert(tag);
        return ResponseEntity.status(HttpStatus.CREATED).body("Tag created" +
                " successfully");
    }
}
