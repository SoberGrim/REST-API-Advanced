package com.epam.esm.dao;

import com.epam.esm.dao.creator.criteria.Criteria;
import com.epam.esm.dto.GiftCertificate;

import java.util.List;
import java.util.Optional;

/**
 * The interface Gift certificate dao.
 *
 * @param <T> the type parameter
 */
public interface GiftCertificateDao<T extends GiftCertificate> {
    /**
     * Insert long.
     *
     * @param t the t
     * @return the long
     */
    long insert(T t);

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
     * @param giftCertificate the gift certificate
     * @return the boolean
     */
    boolean disconnectAllTags(GiftCertificate giftCertificate);

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
     * @param page     the page
     * @param elements the elements
     * @return the list
     */
    List<T> findAll(int page, int elements);

    /**
     * Find with tags list.
     *
     * @param page                    the page
     * @param elements                the elements
     * @param certificateCriteriaList the certificate criteria list
     * @return the list
     */
    List<T> findWithTags(int page, int elements, List<Criteria<GiftCertificate>> certificateCriteriaList);
}
