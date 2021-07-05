package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.constant.EntityFieldsName;
import com.epam.esm.dto.Order;
import com.epam.esm.dto.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

/**
 * The type Tag dao.
 */
@Repository
public class TagDaoImpl implements TagDao<Tag> {
    private final EntityManagerFactory factory;

    /**
     * Instantiates a new Tag dao.
     *
     * @param factory the factory
     */
    @Autowired
    public TagDaoImpl(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public long insert(Tag tag) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(tag);
        em.getTransaction().commit();
        em.close();
        return tag.getId();
    }

    @Override
    public Optional<Tag> findById(long id) {
        EntityManager em = factory.createEntityManager();
        Optional<Tag> tag = Optional.ofNullable(em.find(Tag.class, id));
        em.close();
        return tag;
    }

    @Override
    public Optional<Tag> findByName(String name) {
        EntityManager em = factory.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Tag> criteria = builder.createQuery(Tag.class);
        Root<Tag> root = criteria.from(Tag.class);
        criteria.where(builder.equal(root.get(EntityFieldsName.NAME), name));
        Optional<Tag> tag = em.createQuery(criteria).getResultStream().findAny();
        em.close();
        return tag;
    }

    @Override
    public Optional<Tag> findMostUsedTagOfUserWithHighestCostOfAllOrders(long userId) {
        EntityManager em = factory.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Tag> criteria = builder.createQuery(Tag.class);
        Root<Order> root = criteria.from(Order.class);

        Join<Tag, Order> tagOrderJoin = root.join(EntityFieldsName.GIFT_CERTIFICATE)
                .join(EntityFieldsName.TAGS);

        criteria.select(root.get(EntityFieldsName.GIFT_CERTIFICATE).get(EntityFieldsName.TAGS))
                .where(builder.equal(root.get(EntityFieldsName.USER).get(EntityFieldsName.ID), userId))
                .groupBy(tagOrderJoin.get(EntityFieldsName.NAME))
                .orderBy(builder.desc(builder.count(tagOrderJoin.get(EntityFieldsName.NAME))),
                        builder.desc(builder.sum(root.get(EntityFieldsName.PRICE))));

        Optional<Tag> tag = em.createQuery(criteria).getResultStream().findFirst();
        em.close();
        return tag;
    }

    @Override
    public List<Tag> findAll(int page, int elements) {
        EntityManager em = factory.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Tag> criteria = builder.createQuery(Tag.class);
        Root<Tag> root = criteria.from(Tag.class);
        criteria.select(root);
        List<Tag> tags = (page > 0 && elements > 0) ? em.createQuery(criteria).setMaxResults(elements)
                .setFirstResult(elements * (page - 1)).getResultList() : em.createQuery(criteria).getResultList();
        em.close();
        return tags;
    }

    @Override
    public boolean delete(long id) {
        EntityManager em = factory.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaDelete<Tag> criteria = builder.createCriteriaDelete(Tag.class);
        Root<Tag> root = criteria.from(Tag.class);
        criteria.where(builder.equal(root.get(EntityFieldsName.ID), id));
        em.getTransaction().begin();
        boolean result = em.createQuery(criteria).executeUpdate() == 1;
        em.getTransaction().commit();
        return result;
    }
}
