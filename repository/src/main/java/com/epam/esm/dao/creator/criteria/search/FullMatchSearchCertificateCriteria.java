package com.epam.esm.dao.creator.criteria.search;

import com.epam.esm.dto.GiftCertificate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class FullMatchSearchCertificateCriteria extends SearchCertificateCriteria {

    public FullMatchSearchCertificateCriteria(String columnName, String value) {
        super(columnName, value);
    }

    @Override
    public void acceptCriteria(CriteriaQuery<GiftCertificate> criteriaQuery, CriteriaBuilder builder,
                               Root<GiftCertificate> root) {
        criteriaQuery.where(builder.equal(root.get(getFieldName()), getValue()));
    }
}
