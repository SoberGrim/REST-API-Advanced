package com.epam.esm.dao.creator.criteria.search;

/**
 * The type Full match search criteria.
 */
public class FullMatchSearchCriteria extends SearchCriteria {
    private static final String FULL_PART_MATCH_SEARCH_SIGN = "=";
    private static final String SPACE_SYMBOL = " ";
    private static final String QUOTE = "'";

    /**
     * Instantiates a new Full match search criteria.
     *
     * @param columnName the column name
     * @param value      the value
     */
    public FullMatchSearchCriteria(String columnName, String value) {
        super(columnName, value);
    }

    @Override
    public String acceptCriteria() {
        return getColumnName() + SPACE_SYMBOL + FULL_PART_MATCH_SEARCH_SIGN + SPACE_SYMBOL + QUOTE + getValue() + QUOTE;
    }
}
