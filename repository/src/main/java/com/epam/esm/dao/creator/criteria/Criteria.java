package com.epam.esm.dao.creator.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * The interface Criteria.
 *
 * @param <T> the type parameter
 */
public interface Criteria<T> {
    /**
     * Accept criteria.
     *
     * @param criteriaQuery the criteria query
     * @param builder       the builder
     * @param root          the root
     */
    void acceptCriteria(CriteriaQuery<T> criteriaQuery, CriteriaBuilder builder, Root<T> root);
}
