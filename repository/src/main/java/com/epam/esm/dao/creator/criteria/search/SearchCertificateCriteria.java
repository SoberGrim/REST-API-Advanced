package com.epam.esm.dao.creator.criteria.search;

import com.epam.esm.dao.creator.criteria.CertificateCriteria;

public abstract class SearchCertificateCriteria implements CertificateCriteria {
    private String fieldName;
    private String value;

    public SearchCertificateCriteria(String fieldName, String value) {
        this.fieldName = fieldName;
        this.value = value.toLowerCase();
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
}
