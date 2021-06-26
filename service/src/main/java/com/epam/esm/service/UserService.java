package com.epam.esm.service;

import com.epam.esm.dto.User;

import java.util.List;

public interface UserService<T extends User> {
    T findById(String id);

    List<T> findAll(int page, int elements);
}
