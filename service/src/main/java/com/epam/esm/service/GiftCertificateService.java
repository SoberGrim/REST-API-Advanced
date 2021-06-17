package com.epam.esm.service;

import com.epam.esm.dto.GiftCertificate;

import java.util.List;

/**
 * The interface Gift certificate service.
 *
 * @param <T> the type parameter
 */
public interface GiftCertificateService<T extends GiftCertificate> {
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
    boolean delete(String id);

    /**
     * Update boolean.
     *
     * @param id              the id
     * @param giftCertificate the gift certificate
     * @return the boolean
     */
    boolean update(String id, GiftCertificate giftCertificate);

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
     * @return the list
     */
    List<GiftCertificate> findAll();

    /**
     * Find certificates with tags by criteria list.
     *
     * @param tagName                the tag name
     * @param certificateName        the certificate name
     * @param certificateDescription the certificate description
     * @param sortByName             the sort by name
     * @param sortByDate             the sort by date
     * @return the list
     */
    List<GiftCertificate> findCertificatesWithTagsByCriteria(String tagName, String certificateName,
                                                             String certificateDescription, String sortByName,
                                                             String sortByDate);
}
