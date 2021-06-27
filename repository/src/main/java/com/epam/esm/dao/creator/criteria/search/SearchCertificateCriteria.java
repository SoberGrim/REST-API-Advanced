package com.epam.esm.dao.creator.criteria.search;

import com.epam.esm.dao.creator.criteria.CertificateCriteria;
import com.epam.esm.dto.Tag;

import java.util.List;

/**
 * The type Search certificate criteria.
 */
public abstract class SearchCertificateCriteria implements CertificateCriteria {
    private String fieldName;
    private String value;
    private List<Tag> tags;

    /**
     * Instantiates a new Search certificate criteria.
     *
     * @param fieldName the field name
     * @param value     the value
     */
    public SearchCertificateCriteria(String fieldName, String value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    /**
     * Instantiates a new Search certificate criteria.
     *
     * @param tags the tags
     */
    public SearchCertificateCriteria(List<Tag> tags) {
        this.tags = tags;
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
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets tags.
     *
     * @return the tags
     */
    public List<Tag> getTags() {
        return tags;
    }

    /**
     * Sets tags.
     *
     * @param tags the tags
     */
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
