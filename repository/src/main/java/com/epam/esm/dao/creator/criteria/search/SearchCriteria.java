package com.epam.esm.dao.creator.criteria.search;

import com.epam.esm.dao.creator.criteria.Criteria;

/**
 * The type Search criteria.
 */
public abstract class SearchCriteria implements Criteria {
    private String columnName;
    private String value;

    /**
     * Instantiates a new Search criteria.
     *
     * @param columnName the column name
     * @param value      the value
     */
    public SearchCriteria(String columnName, String value) {
        this.columnName = columnName;
        this.value = value.toLowerCase();
    }

    /**
     * Gets column name.
     *
     * @return the column name
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * Sets column name.
     *
     * @param columnName the column name
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
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
}
