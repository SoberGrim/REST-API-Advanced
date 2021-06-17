package com.epam.esm.dao.creator;

import com.epam.esm.dao.constant.SqlGiftCertificateQuery;
import com.epam.esm.dao.creator.criteria.Criteria;
import com.epam.esm.dao.creator.criteria.search.SearchCriteria;
import com.epam.esm.dao.creator.criteria.sort.SortCriteria;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The type Sql gift certificate query creator.
 */
@Component
public class SqlGiftCertificateQueryCreator implements SqlQueryCreator {
    private static final String SELECT_QUERY_WITH_OUT_CRITERIA = "SELECT gift_certificate_id, certificate_name," +
            " description, price, duration, create_date, last_update_date, tag_id, tag_name FROM gift_certificates" +
            " RIGHT JOIN gift_certificates_tags ON gift_certificate_id = gift_certificate_id_fk" +
            " LEFT JOIN tags ON tag_id = tag_id_fk";
    private static final String SPACE_SYMBOL = " ";
    private static final String COMMA_SYMBOL = ",";
    private static final String SEMICOLON = ";";
    private static final String WHERE_STATEMENT = "WHERE";
    private static final String SORT_STATEMENT = "ORDER BY";
    private static final String AND_STATEMENT = "AND";

    @Override
    public String createQuery(List<Criteria> criteriaList) {
        StringBuilder sb = new StringBuilder(SELECT_QUERY_WITH_OUT_CRITERIA);
        if (criteriaList == null || criteriaList.isEmpty()) {
            return SqlGiftCertificateQuery.SQL_SELECT_CERTIFICATES_WITH_TAGS;
        }

        boolean isFirstCriteria = true;
        for (Criteria criteria : criteriaList) {
            if (criteria instanceof SearchCriteria) {
                sb.append(isFirstCriteria ? SPACE_SYMBOL + WHERE_STATEMENT + SPACE_SYMBOL + criteria.acceptCriteria() :
                        SPACE_SYMBOL + AND_STATEMENT + SPACE_SYMBOL + criteria.acceptCriteria());
                isFirstCriteria = false;
            }
        }

        isFirstCriteria = true;
        for (Criteria criteria : criteriaList) {
            if (criteria instanceof SortCriteria) {
                sb.append(isFirstCriteria ? SPACE_SYMBOL + SORT_STATEMENT + SPACE_SYMBOL + criteria.acceptCriteria() :
                        COMMA_SYMBOL + SPACE_SYMBOL + criteria.acceptCriteria());
                isFirstCriteria = false;
            }
        }
        sb.append(SEMICOLON);
        return sb.toString();
    }
}
