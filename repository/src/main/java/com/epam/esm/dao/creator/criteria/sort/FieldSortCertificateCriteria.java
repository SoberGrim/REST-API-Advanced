package com.epam.esm.dao.creator.criteria.sort;

import com.epam.esm.dto.GiftCertificate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

public class FieldSortCertificateCriteria extends SortCertificateCriteria {
    private static final String SORT_ASC = "ASC";

    public FieldSortCertificateCriteria(String fieldName, String sortOrdering) {
        super(fieldName, sortOrdering);
    }

    @Override
    public void acceptCriteria(CriteriaQuery<GiftCertificate> criteriaQuery, CriteriaBuilder builder,
                               Root<GiftCertificate> root) {
        Order order = getSortOrdering().equalsIgnoreCase(SORT_ASC) ? builder.asc(root.get(getFieldName())) :
                builder.desc(root.get(getFieldName()));
        criteriaQuery.orderBy(order);
    }
}
