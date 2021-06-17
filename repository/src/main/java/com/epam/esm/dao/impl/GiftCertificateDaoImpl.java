package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.constant.SqlGiftCertificateQuery;
import com.epam.esm.dao.creator.SqlQueryCreator;
import com.epam.esm.dao.creator.criteria.Criteria;
import com.epam.esm.dto.GiftCertificate;
import com.epam.esm.dao.mapper.GiftCertificateMapper;
import com.epam.esm.dto.Tag;
import com.epam.esm.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * The type Gift certificate dao.
 */
@Repository
public class GiftCertificateDaoImpl implements GiftCertificateDao<GiftCertificate> {
    private final JdbcTemplate template;
    private final GiftCertificateMapper mapper;
    private final SqlQueryCreator queryCreator;

    /**
     * Instantiates a new Gift certificate dao.
     *
     * @param dataSource   the data source
     * @param mapper       the mapper
     * @param queryCreator the query creator
     */
    @Autowired
    public GiftCertificateDaoImpl(DataSource dataSource, GiftCertificateMapper mapper, SqlQueryCreator queryCreator) {
        this.template = new JdbcTemplate(dataSource);
        this.mapper = mapper;
        this.queryCreator = queryCreator;
    }

    @Override
    public boolean insert(GiftCertificate giftCertificate) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        boolean isQuerySuccess = template.update(con -> {
            PreparedStatement ps = con.prepareStatement(SqlGiftCertificateQuery.SQL_INSERT_CERTIFICATE,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, giftCertificate.getName());
            ps.setString(2, giftCertificate.getDescription());
            ps.setBigDecimal(3, giftCertificate.getPrice());
            ps.setInt(4, giftCertificate.getDuration());
            ps.setTimestamp(5, Timestamp.valueOf(giftCertificate.getCreateDate()));
            return ps;
        }, keyHolder) == 1;
        if (isQuerySuccess && keyHolder.getKey() != null) {
            return (giftCertificate.getTags() == null || giftCertificate.getTags().isEmpty() ||
                    connectTags(giftCertificate.getTags(), keyHolder.getKey().longValue()));
        } else {
            throw new DaoException("1", "Generated key is null for gift certificate: " + giftCertificate);
        }
    }

    @Override
    public boolean delete(long id) {
        return template.update(SqlGiftCertificateQuery.SQL_DELETE_CERTIFICATE_BY_ID, id) == 1;
    }

    @Override
    public boolean disconnectAllTags(long id) {
        return template.update(SqlGiftCertificateQuery.SQL_DELETE_TAGS_FROM_CERTIFICATE_BY_CERTIFICATE_ID, id) == 1;
    }

    @Override
    public boolean update(GiftCertificate giftCertificate) {
        return template.update(SqlGiftCertificateQuery.SQL_UPDATE_CERTIFICATE_BY_ID, giftCertificate.getName(),
                giftCertificate.getDescription(), giftCertificate.getPrice(), giftCertificate.getDuration(),
                giftCertificate.getLastUpdateDate(), giftCertificate.getId()) == 1;
    }

    @Override
    public Optional<GiftCertificate> findById(long id) {
        List<GiftCertificate> giftCertificates = template.query(SqlGiftCertificateQuery.SQL_SELECT_CERTIFICATE_BY_ID,
                mapper, id);
        Optional<GiftCertificate> giftCertificate = Optional.empty();
        if (giftCertificates != null && !giftCertificates.isEmpty()) {
            giftCertificate = Optional.of(giftCertificates.get(0));
        }
        return giftCertificate;
    }

    @Override
    public List<GiftCertificate> findAll() {
        return template.query(SqlGiftCertificateQuery.SQL_SELECT_ALL_CERTIFICATES, mapper);
    }

    @Override
    public List<GiftCertificate> findWithTags(List<Criteria> criteriaList) {
        return template.query(queryCreator.createQuery(criteriaList), mapper);
    }

    @Override
    public boolean connectTags(List<Tag> tags, long certificateId) {
        return tags.stream()
                .allMatch(t -> template.update(SqlGiftCertificateQuery.SQL_UPDATE_CERTIFICATE_TAG,
                        certificateId, t.getId()) == 1);
    }
}
