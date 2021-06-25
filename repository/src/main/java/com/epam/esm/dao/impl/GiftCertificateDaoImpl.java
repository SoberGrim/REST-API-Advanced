package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.constant.EntityFieldsName;
import com.epam.esm.dao.creator.QueryCreator;
import com.epam.esm.dao.creator.criteria.CertificateCriteria;
import com.epam.esm.dto.GiftCertificate;
import com.epam.esm.dao.mapper.GiftCertificateMapper;
import com.epam.esm.dto.Tag;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class GiftCertificateDaoImpl implements GiftCertificateDao<GiftCertificate> {
    private final JdbcTemplate template;
    private final GiftCertificateMapper mapper;
    private final QueryCreator<GiftCertificate> criteriaCreator;
    private final EntityManagerFactory factory;

    @Autowired
    public GiftCertificateDaoImpl(DataSource dataSource, GiftCertificateMapper mapper, QueryCreator<GiftCertificate> criteriaCreator,
                                  EntityManagerFactory factory) {
        this.template = new JdbcTemplate(dataSource);
        this.mapper = mapper;
        this.criteriaCreator = criteriaCreator;
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
        EntityManager em = factory.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaDelete<GiftCertificate> criteria = builder.createCriteriaDelete(GiftCertificate.class);
        Root<GiftCertificate> root = criteria.from(GiftCertificate.class);
        criteria.where(builder.equal(root.get(EntityFieldsName.ID), id));
        em.getTransaction().begin();
        boolean result = em.createQuery(criteria).executeUpdate() == 1;
        em.getTransaction().commit();
        em.close();
        return result;
    }

    @Override
    public boolean disconnectAllTags(GiftCertificate giftCertificate) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        giftCertificate.setTags(null);
        em.merge(giftCertificate);
        em.getTransaction().commit();
        em.close();
        return CollectionUtils.isEmpty(giftCertificate.getTags());
    }

    @Override
    public boolean update(GiftCertificate giftCertificate) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.merge(giftCertificate);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    @Override
    public Optional<GiftCertificate> findById(long id) {
        EntityManager em = factory.createEntityManager();
        Optional<GiftCertificate> giftCertificate = Optional.ofNullable(em.find(GiftCertificate.class, id));
        em.close();
        return giftCertificate;
    }

    @Override
    public List<GiftCertificate> findAll(int page, int elements) {
        EntityManager em = factory.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<GiftCertificate> criteria = builder.createQuery(GiftCertificate.class);
        Root<GiftCertificate> root = criteria.from(GiftCertificate.class);
        criteria.select(root);

        List<GiftCertificate> giftCertificates = (page > 0 && elements > 0) ? em.createQuery(criteria)
                .setMaxResults(elements).setFirstResult(elements * (page - 1)).getResultList() :
                em.createQuery(criteria).getResultList();

        em.close();
        return giftCertificates;
    }

    @Override
    public List<GiftCertificate> findWithTags(int page, int elements, List<CertificateCriteria> certificateCriteriaList) {
        EntityManager em = factory.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<GiftCertificate> criteria = builder.createQuery(GiftCertificate.class);
        Root<GiftCertificate> root = criteria.from(GiftCertificate.class);

        criteriaCreator.createCriteria(certificateCriteriaList, criteria, builder, root);

        //criteria.orderBy(builder.asc(root.get(EntityFieldsName.CREATE_DATE))); work
        // criteria.where(builder.like(root.get(EntityFieldsName.DESCRIPTION), "%car%")); work

        List<GiftCertificate> giftCertificates = (page > 0 && elements > 0) ? em.createQuery(criteria)
                .setMaxResults(elements).setFirstResult(elements * (page - 1)).getResultList() :
                em.createQuery(criteria).getResultList();

        em.close();
        return giftCertificates;
        //return template.query(queryCreator.createQuery(certificateCriteriaList), mapper);//fixme
    }
}
