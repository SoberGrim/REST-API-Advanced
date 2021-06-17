package com.epam.esm.dao.creator.criteria.sort;

import com.epam.esm.dao.creator.criteria.Criteria;

/**
 * The type Sort criteria.
 */
public abstract class SortCriteria implements Criteria {
    private String columnName;
    private String sortOrdering;

    /**
     * Instantiates a new Sort criteria.
     *
     * @param columnName   the column name
     * @param sortOrdering the sort ordering
     */
    public SortCriteria(String columnName, String sortOrdering) {
        this.columnName = columnName;
        this.sortOrdering = sortOrdering;
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
