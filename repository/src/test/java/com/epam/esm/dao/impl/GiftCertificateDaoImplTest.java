package com.epam.esm.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GiftCertificateDaoImplTest {
    /*private static final GiftCertificateDao<GiftCertificate> dao = new GiftCertificateDaoImpl
            (DataSourceConfig.dataSource, new GiftCertificateMapper(), new GiftCertificateQueryCreator());

    @Test
    public void findWithTagsTest() {
        List<GiftCertificate> expected = new ArrayList<>();
        List<CertificateCriteria> criteriaList = new ArrayList<>();
        criteriaList.add(new FullMatchSearchCertificateCriteria(SqlTagColumnName.TAG_NAME, "#longverylongtagname"));
        List<GiftCertificate> actual = dao.findWithTags(criteriaList);
        assertEquals(expected, actual);
    }

    @Test
    public void deleteTest() {
        boolean actual = dao.delete(12345);
        assertFalse(actual);
    }

    @Test
    public void findByIdTest() {
        Optional<GiftCertificate> expected = Optional.of(new GiftCertificate(2, "Sand", "Yellow sand", new BigDecimal("2"), 24,
                LocalDateTime.of(2020, 5, 5, 23, 42, 12, 112000000),
                null, new ArrayList<>()));
        Optional<GiftCertificate> actual = dao.findById(2);
        assertEquals(expected, actual);
    }

    @Test
    public void disconnectAllTagsTest() {
        boolean actual = dao.disconnectAllTags(12345);
        assertFalse(actual);
    }*/
}
