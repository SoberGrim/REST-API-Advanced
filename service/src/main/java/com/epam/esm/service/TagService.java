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
     * Insert boolean.
     *
     * @param tag the tag
     * @return the boolean
     */
    boolean insert(Tag tag);

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
     * Find all list.
     *
     * @return the list
     */
    List<T> findAll();

    /**
     * Find tags connected to certificate list.
     *
     * @param certificateId the certificate id
     * @return the list
     */
    List<T> findTagsConnectedToCertificate(String certificateId);

    /**
     * Delete boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean delete(String id);
}
