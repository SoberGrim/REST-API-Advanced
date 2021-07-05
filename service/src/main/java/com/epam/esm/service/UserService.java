package com.epam.esm.service;

import com.epam.esm.dto.User;

import java.util.List;

/**
 * The interface User service.
 *
 * @param <T> the type parameter
 */
public interface UserService<T extends User> {
    /**
     * Find by id t.
     *
     * @param id the id
     * @return the t
     */
    T findById(String id);

    /**
     * Find all list.
     *
     * @param page     the page
     * @param elements the elements
     * @return the list
     */
    List<T> findAll(int page, int elements);
}
