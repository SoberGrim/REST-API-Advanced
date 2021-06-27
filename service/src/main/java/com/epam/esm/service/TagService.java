package com.epam.esm.service;

import com.epam.esm.dto.Tag;

import java.util.List;

/**
 * The interface Tag service.
 *
 * @param <T> the type parameter
 */
public interface TagService<T extends Tag> {
    /**
     * Insert long.
     *
     * @param tag the tag
     * @return the long
     */
    long insert(Tag tag);

    /**
     * Find by id t.
     *
     * @param id the id
     * @return the t
     */
    T findById(String id);

    /**
     * Find by name t.
     *
     * @param name the name
     * @return the t
     */
    T findByName(String name);

    /**
     * Find most used tag of user with highest cost of all orders t.
     *
     * @param userId the user id
     * @return the t
     */
    T findMostUsedTagOfUserWithHighestCostOfAllOrders(String userId);

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
    boolean delete(String id);
}
