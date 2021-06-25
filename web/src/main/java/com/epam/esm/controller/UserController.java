package com.epam.esm.controller;

import com.epam.esm.dto.User;
import com.epam.esm.hateoas.Hateoas;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService<User> service;
    private final Hateoas<User> hateoas;
//fixme fix hateoas
    @Autowired
    public UserController(UserService<User> service, Hateoas<User> hateoas) {
        this.service = service;
        this.hateoas = hateoas;
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable String id) {
        User user = service.findById(id);
        hateoas.createHateoas(user);
        return user;
    }

    @GetMapping
    public List<User> findUsersWithCertificate(@RequestParam int page, @RequestParam int elements) {
        List<User> users = service.findWithGiftCertificates(page, elements);
        users.forEach(hateoas::createHateoas);
        return users;
    }

    @GetMapping("/all")
    public List<User> findAllUsers(@RequestParam int page, @RequestParam int elements) {
        List<User> users = service.findAll(page, elements);
        users.forEach(hateoas::createHateoas);
        return users;
    }
}
