package com.epam.esm.dao.creator.criteria.search;

import com.epam.esm.dao.creator.criteria.CertificateCriteria;
import com.epam.esm.dto.Tag;

import java.util.List;

public abstract class SearchCertificateCriteria implements CertificateCriteria {
    private String fieldName;
    private String value;
    private List<Tag> tags;

    public SearchCertificateCriteria(String fieldName, String value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    public SearchCertificateCriteria(List<Tag> tags) {
        this.tags = tags;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
