package com.epam.esm.service;

import com.epam.esm.dto.GiftCertificate;

import java.util.List;

public interface GiftCertificateService<T extends GiftCertificate> {
    long insert(T t);

    boolean delete(String id);

    boolean update(String id, GiftCertificate giftCertificate);

    boolean disconnectTags(String id);

    void disconnectTagById(String tagId);

    T findById(String id);

    List<GiftCertificate> findAll(int page, int elements);

    List<GiftCertificate> findCertificatesWithTagsByCriteria(int page, int elements, List<String> tagsNames,
                                                             String certificateName, String certificateDescription,
                                                             String sortByName, String sortByDate);
}
