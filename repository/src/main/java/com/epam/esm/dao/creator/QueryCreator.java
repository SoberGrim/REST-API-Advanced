package com.epam.esm.dao.creator;

import com.epam.esm.dao.creator.criteria.CertificateCriteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public interface QueryCreator<T> {
    void createCriteria(List<CertificateCriteria> certificateCriteriaList, CriteriaQuery<T> criteriaQuery, CriteriaBuilder builder, Root<T>
            root);
}
