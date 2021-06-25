package com.epam.esm.dao.creator.criteria.sort;

import com.epam.esm.dao.creator.criteria.CertificateCriteria;

public abstract class SortCertificateCriteria implements CertificateCriteria {
    private String fieldName;
    private String sortOrdering;

    public SortCertificateCriteria(String fieldName, String sortOrdering) {
        this.fieldName = fieldName;
        this.sortOrdering = sortOrdering;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getSortOrdering() {
        return sortOrdering;
    }

    public void setSortOrdering(String sortOrdering) {
        this.sortOrdering = sortOrdering;
    }
}
