package com.epam.esm.dao.creator.criteria.sort;

/**
 * The type Field sort criteria.
 */
public class FieldSortCriteria extends SortCriteria {
    private static final String SPACE_SYMBOL = " ";

    /**
     * Instantiates a new Field sort criteria.
     *
     * @param columnName   the column name
     * @param sortOrdering the sort ordering
     */
    public FieldSortCriteria(String columnName, String sortOrdering) {
        super(columnName, sortOrdering);
    }

    @Override
    public String acceptCriteria() {
        return getColumnName() + SPACE_SYMBOL + getSortOrdering();
    }
}
