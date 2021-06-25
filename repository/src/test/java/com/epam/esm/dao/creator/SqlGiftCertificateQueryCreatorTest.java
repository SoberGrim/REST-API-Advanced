package com.epam.esm.dao.creator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SqlGiftCertificateQueryCreatorTest {
    private final QueryCreator queryCreator = new GiftCertificateQueryCreator();
// FIXME: 6/25/2021 fix test
   /* @Test
    public void createQueryTest1() {
        String expected = "SELECT gift_certificate_id, certificate_name, description, price, duration, create_date," +
                " last_update_date, tag_id, tag_name FROM gift_certificates RIGHT JOIN gift_certificates_tags ON" +
                " gift_certificate_id = gift_certificate_id_fk LEFT JOIN tags ON tag_id = tag_id_fk WHERE" +
                " LOWER(description) LIKE '%value%';";
        List<CertificateCriteria> certificateCriteriaList = new ArrayList<>();
        certificateCriteriaList.add(new PartMatchSearchCertificateCriteria(SqlGiftCertificateColumnName.DESCRIPTION, "value"));
        String actual = queryCreator.createQuery(certificateCriteriaList);
        assertEquals(expected, actual);
    }

    @Test
    public void createQueryTest2() {
        String expected = "SELECT gift_certificate_id, certificate_name, description, price, duration, create_date," +
                " last_update_date, tag_id, tag_name FROM gift_certificates RIGHT JOIN gift_certificates_tags ON" +
                " gift_certificate_id = gift_certificate_id_fk LEFT JOIN tags ON tag_id = tag_id_fk WHERE" +
                " LOWER(description) LIKE '%value%' ORDER BY create_date asc;";
        List<CertificateCriteria> certificateCriteriaList = new ArrayList<>();
        certificateCriteriaList.add(new PartMatchSearchCertificateCriteria(SqlGiftCertificateColumnName.DESCRIPTION, "value"));
        certificateCriteriaList.add(new FieldSortCertificateCriteria(SqlGiftCertificateColumnName.CREATE_DATE, "asc"));
        String actual = queryCreator.createQuery(certificateCriteriaList);
        assertEquals(expected, actual);
    }*/
}
