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

@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagService<Tag> service;

    @Autowired
    public TagController(TagService<Tag> service) {
        this.service = service;
    }

    @GetMapping
    public List<Tag> findAllTags() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Tag findTagById(@PathVariable String id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTag(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Tag deleted successfully" +
                " (id = " + id + ")");
    }

    @PostMapping("/new")
    public ResponseEntity<String> createTag(@RequestBody Tag tag) {
        service.insert(tag);
        return ResponseEntity.status(HttpStatus.CREATED).body("Tag created" +
                " successfully");
    }
}
