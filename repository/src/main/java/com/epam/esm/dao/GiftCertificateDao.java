package com.epam.esm.dao;

import com.epam.esm.dao.creator.criteria.Criteria;
import com.epam.esm.dto.GiftCertificate;

import java.util.List;
import java.util.Optional;

public interface GiftCertificateDao<T extends GiftCertificate> {
    long insert(T t);

    boolean delete(long id);

    boolean disconnectAllTags(GiftCertificate giftCertificate);

    boolean update(T t);

    Optional<GiftCertificate> findById(long id);

    List<T> findAll();

    List<T> findWithTags(List<Criteria> criteriaList);
}
