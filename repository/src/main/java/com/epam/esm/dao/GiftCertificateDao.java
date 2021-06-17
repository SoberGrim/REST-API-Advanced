package com.epam.esm.dao;

import com.epam.esm.dao.creator.criteria.Criteria;
import com.epam.esm.dto.GiftCertificate;
import com.epam.esm.dto.Tag;

import java.util.List;
import java.util.Optional;

/**
 * The interface Gift certificate dao.
 *
 * @param <T> the type parameter
 */
public interface GiftCertificateDao<T extends GiftCertificate> {
    /**
     * Insert boolean.
     *
     * @param t the t
     * @return the boolean
     */
    boolean insert(T t);

    /**
     * Delete boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean delete(long id);

    /**
     * Disconnect all tags boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean disconnectAllTags(long id);

    /**
     * Update boolean.
     *
     * @param t the t
     * @return the boolean
     */
    boolean update(T t);

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    Optional<GiftCertificate> findById(long id);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<T> findAll();

    /**
     * Find with tags list.
     *
     * @param criteriaList the criteria list
     * @return the list
     */
    List<T> findWithTags(List<Criteria> criteriaList);

    /**
     * Connect tags boolean.
     *
     * @param tags          the tags
     * @param certificateId the certificate id
     * @return the boolean
     */
    boolean connectTags(List<Tag> tags, long certificateId);
}
