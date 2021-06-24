package com.epam.esm.service;

import com.epam.esm.dto.GiftCertificate;

import java.util.List;

public interface GiftCertificateService<T extends GiftCertificate> {
    long insert(T t);

    boolean delete(String id);

    boolean update(String id, GiftCertificate giftCertificate);

    T findById(String id);

    List<GiftCertificate> findAll();

    List<GiftCertificate> findCertificatesWithTagsByCriteria(String tagName, String certificateName,
                                                             String certificateDescription, String sortByName,
                                                             String sortByDate);
}
