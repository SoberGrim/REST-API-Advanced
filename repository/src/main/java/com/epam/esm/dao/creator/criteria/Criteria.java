package com.epam.esm.dao.creator.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public interface Criteria<T> {
    void acceptCriteria(CriteriaQuery<T> criteriaQuery, CriteriaBuilder builder, Root<T> root);
}
