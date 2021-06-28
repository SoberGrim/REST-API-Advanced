package com.epam.esm.dao;

import com.epam.esm.dto.Tag;

import java.util.List;
import java.util.Optional;

/**
 * The interface Tag dao.
 *
 * @param <T> the type parameter
 */
public interface TagDao<T extends Tag> {
    /**
     * Insert long.
     *
     * @param t the t
     * @return the long
     */
    long insert(T t);

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    Optional<T> findById(long id);

    /**
     * Find by name optional.
     *
     * @param name the name
     * @return the optional
     */
    Optional<T> findByName(String name);

    /**
     * Find most used tag of user with highest cost of all orders optional.
     *
     * @param userId the user id
     * @return the optional
     */
    Optional<T> findMostUsedTagOfUserWithHighestCostOfAllOrders(long userId);

    /**
     * Find all list.
     *
     * @param page     the page
     * @param elements the elements
     * @return the list
     */
    List<T> findAll(int page, int elements);

    /**
     * Delete boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean delete(long id);
}
