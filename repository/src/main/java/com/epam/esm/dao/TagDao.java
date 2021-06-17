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
     * Insert boolean.
     *
     * @param t the t
     * @return the boolean
     */
    boolean insert(T t);

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
     * Find all list.
     *
     * @return the list
     */
    List<T> findAll();

    /**
     * Find tags connected to certificate list.
     *
     * @param id the id
     * @return the list
     */
    List<T> findTagsConnectedToCertificate(long id);

    /**
     * Delete boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean delete(long id);
}
