package com.epam.esm.dao.creator;

import com.epam.esm.dao.constant.SqlGiftCertificateColumnName;
import com.epam.esm.dao.creator.criteria.Criteria;
import com.epam.esm.dao.creator.criteria.search.PartMatchSearchCriteria;
import com.epam.esm.dao.creator.criteria.sort.FieldSortCriteria;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SqlGiftCertificateQueryCreatorTest {
    private final SqlQueryCreator queryCreator = new SqlGiftCertificateQueryCreator();
    private final List<Criteria> criteriaList = new ArrayList<>();

    @Test
    public void createQueryTest1() {
        String expected = "SELECT gift_certificate_id, certificate_name, description, price, duration, create_date," +
                " last_update_date, tag_id, tag_name FROM gift_certificates RIGHT JOIN gift_certificates_tags ON" +
                " gift_certificate_id = gift_certificate_id_fk LEFT JOIN tags ON tag_id = tag_id_fk WHERE" +
                " LOWER(description) LIKE '%value%';";
        criteriaList.add(new PartMatchSearchCriteria(SqlGiftCertificateColumnName.DESCRIPTION, "value"));
        String actual = queryCreator.createQuery(criteriaList);
        assertEquals(expected, actual);
    }

    @Test
    public void createQueryTest2() {
        String expected = "SELECT gift_certificate_id, certificate_name, description, price, duration, create_date," +
                " last_update_date, tag_id, tag_name FROM gift_certificates RIGHT JOIN gift_certificates_tags ON" +
                " gift_certificate_id = gift_certificate_id_fk LEFT JOIN tags ON tag_id = tag_id_fk WHERE" +
                " LOWER(description) LIKE '%value%' ORDER BY create_date asc;";
        criteriaList.add(new PartMatchSearchCriteria(SqlGiftCertificateColumnName.DESCRIPTION, "value"));
        criteriaList.add(new FieldSortCriteria(SqlGiftCertificateColumnName.CREATE_DATE, "asc"));
        String actual = queryCreator.createQuery(criteriaList);
        assertEquals(expected, actual);
    }
}
