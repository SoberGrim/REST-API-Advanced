package com.epam.esm.dao.creator.criteria.search;

import com.epam.esm.dao.constant.Symbol;
import com.epam.esm.dto.GiftCertificate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * The type Part match search certificate criteria.
 */
public class PartMatchSearchCertificateCriteria extends SearchCertificateCriteria {
    /**
     * Instantiates a new Part match search certificate criteria.
     *
     * @param columnName the column name
     * @param value      the value
     */
    public PartMatchSearchCertificateCriteria(String columnName, String value) {
        super(columnName, value);
    }

    @Override
    public void acceptCriteria(CriteriaQuery<GiftCertificate> criteriaQuery, CriteriaBuilder builder,
                               Root<GiftCertificate> root) {
        criteriaQuery.where(builder.like(root.get(getFieldName()), Symbol.PERCENTAGE + getValue() +
                Symbol.PERCENTAGE));
    }
}
