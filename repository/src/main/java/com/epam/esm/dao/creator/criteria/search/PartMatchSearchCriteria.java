package com.epam.esm.dao.creator.criteria.search;

/**
 * The type Part match search criteria.
 */
public class PartMatchSearchCriteria extends SearchCriteria {
    private static final String PART_MATCH_SEARCH_STATEMENT = "LIKE";
    private static final String SPACE_SYMBOL = " ";
    private static final String PERCENT_SIGN = "%";
    private static final String QUOTE = "'";
    private static final String LOWER_STATEMENT = "LOWER";
    private static final String OPEN_BRACKET = "(";
    private static final String CLOSE_BRACKET = ")";


    /**
     * Instantiates a new Part match search criteria.
     *
     * @param columnName the column name
     * @param value      the value
     */
    public PartMatchSearchCriteria(String columnName, String value) {
        super(columnName, value);
    }

    @Override
    public String acceptCriteria() {
        return LOWER_STATEMENT + OPEN_BRACKET + getColumnName() + CLOSE_BRACKET + SPACE_SYMBOL
                + PART_MATCH_SEARCH_STATEMENT + SPACE_SYMBOL + QUOTE + PERCENT_SIGN + getValue() + PERCENT_SIGN + QUOTE;
    }
}
