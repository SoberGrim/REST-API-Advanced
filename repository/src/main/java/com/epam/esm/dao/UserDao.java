package com.epam.esm.dao;

import com.epam.esm.dto.User;

import java.util.List;
import java.util.Optional;

public interface UserDao<T extends User> {
    Optional<T> findById(long id);

    List<T> findAll(int page, int elements);

    boolean update(T t);
}
