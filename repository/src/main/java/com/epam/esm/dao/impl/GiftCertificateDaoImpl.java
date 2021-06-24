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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class GiftCertificateDaoImpl implements GiftCertificateDao<GiftCertificate> {
    private final JdbcTemplate template;
    private final GiftCertificateMapper mapper;
    private final SqlQueryCreator queryCreator;
    private final EntityManagerFactory factory;

    @Autowired
    public GiftCertificateDaoImpl(DataSource dataSource, GiftCertificateMapper mapper, SqlQueryCreator queryCreator,
                                  EntityManagerFactory factory) {
        this.template = new JdbcTemplate(dataSource);
        this.mapper = mapper;
        this.queryCreator = queryCreator;
        this.factory = factory;
    }

    @Override
    public long insert(GiftCertificate giftCertificate) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(giftCertificate);
        em.getTransaction().commit();
        em.close();
        return giftCertificate.getId();
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
        EntityManager em = factory.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<GiftCertificate> criteria = builder.createQuery(GiftCertificate.class);
        Root<GiftCertificate> root = criteria.from(GiftCertificate.class);
        criteria.select(root);
        List<GiftCertificate> giftCertificates = em.createQuery(criteria).getResultList();
        em.close();
        return giftCertificates;
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
