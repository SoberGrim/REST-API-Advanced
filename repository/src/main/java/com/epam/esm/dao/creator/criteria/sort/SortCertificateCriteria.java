package com.epam.esm.dao.creator.criteria.sort;

import com.epam.esm.dao.creator.criteria.CertificateCriteria;

/**
 * The type Sort certificate criteria.
 */
public abstract class SortCertificateCriteria implements CertificateCriteria {
    private String fieldName;
    private String sortOrdering;

    /**
     * Instantiates a new Sort certificate criteria.
     *
     * @param fieldName    the field name
     * @param sortOrdering the sort ordering
     */
    public SortCertificateCriteria(String fieldName, String sortOrdering) {
        this.fieldName = fieldName;
        this.sortOrdering = sortOrdering;
    }

    /**
     * Gets field name.
     *
     * @return the field name
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Sets field name.
     *
     * @param fieldName the field name
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * Gets sort ordering.
     *
     * @return the sort ordering
     */
    public String getSortOrdering() {
        return sortOrdering;
    }

    /**
     * Sets sort ordering.
     *
     * @param sortOrdering the sort ordering
     */
    public void setSortOrdering(String sortOrdering) {
        this.sortOrdering = sortOrdering;
    }
}
