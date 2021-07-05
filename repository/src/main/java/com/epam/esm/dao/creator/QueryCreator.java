package com.epam.esm.dao.creator;

import com.epam.esm.dao.creator.criteria.Criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * The interface Query creator.
 *
 * @param <T> the type parameter
 */
public interface QueryCreator<T> {
    /**
     * Create criteria.
     *
     * @param criteriaList  the criteria list
     * @param criteriaQuery the criteria query
     * @param builder       the builder
     * @param root          the root
     */
    void createCriteria(List<Criteria<T>> criteriaList, CriteriaQuery<T> criteriaQuery, CriteriaBuilder builder, Root<T>
            root);
}
