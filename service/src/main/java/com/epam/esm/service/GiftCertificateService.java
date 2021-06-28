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
     * Disconnect tag by id.
     *
     * @param tagId the tag id
     */
    void disconnectTagById(String tagId);

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
    List<GiftCertificate> findAll(int page, int elements);

    /**
     * Find certificates with tags by criteria list.
     *
     * @param page                   the page
     * @param elements               the elements
     * @param tagsNames              the tags names
     * @param certificateName        the certificate name
     * @param certificateDescription the certificate description
     * @param sortByName             the sort by name
     * @param sortByDate             the sort by date
     * @return the list
     */
    List<GiftCertificate> findCertificatesWithTagsByCriteria(int page, int elements, List<String> tagsNames,
                                                             String certificateName, String certificateDescription,
                                                             String sortByName, String sortByDate);
}
