package com.epam.esm.dao;

import com.epam.esm.dto.User;

import java.util.List;
import java.util.Optional;

/**
 * The interface User dao.
 *
 * @param <T> the type parameter
 */
public interface UserDao<T extends User> {
    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    Optional<T> findById(long id);

    /**
     * Find all list.
     *
     * @param page     the page
     * @param elements the elements
     * @return the list
     */
    List<T> findAll(int page, int elements);

    /**
     * Update boolean.
     *
     * @param t the t
     * @return the boolean
     */
    boolean update(T t);
}
