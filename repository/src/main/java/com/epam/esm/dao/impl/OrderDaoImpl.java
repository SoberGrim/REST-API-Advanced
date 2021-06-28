package com.epam.esm.dao.impl;

import com.epam.esm.dao.OrderDao;
import com.epam.esm.dao.constant.EntityFieldsName;
import com.epam.esm.dto.Order;
import com.epam.esm.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

/**
 * The type Order dao.
 */
@Repository
public class OrderDaoImpl implements OrderDao<Order> {
    private final EntityManagerFactory factory;

    /**
     * Instantiates a new Order dao.
     *
     * @param factory the factory
     */
    @Autowired
    public OrderDaoImpl(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public long insert(Order order) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
        em.close();
        return order.getId();
    }

    @Override
    public List<Order> findByUserId(int page, int elements, User user) {
        EntityManager em = factory.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Order> criteria = builder.createQuery(Order.class);
        Root<Order> root = criteria.from(Order.class);
        criteria.where(builder.equal(root.get(EntityFieldsName.USER), user));
        List<Order> orders = (page > 0 && elements > 0) ? em.createQuery(criteria).setMaxResults(elements)
                .setFirstResult(elements * (page - 1)).getResultList() : em.createQuery(criteria).getResultList();
        em.close();
        return orders;
    }

    @Override
    public boolean deleteByCertificateId(long certificateId) {
        EntityManager em = factory.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaDelete<Order> criteria = builder.createCriteriaDelete(Order.class);
        Root<Order> root = criteria.from(Order.class);
        criteria.where(builder.equal(root.get(EntityFieldsName.GIFT_CERTIFICATE).get(EntityFieldsName.ID),
                certificateId));
        em.getTransaction().begin();
        boolean result = em.createQuery(criteria).executeUpdate() > 0;
        em.getTransaction().commit();
        em.close();
        return result;
    }

    @Override
    public List<Order> findByCertificateId(long certificateId) {
        EntityManager em = factory.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Order> criteria = builder.createQuery(Order.class);
        Root<Order> root = criteria.from(Order.class);
        criteria.where(builder.equal(root.get(EntityFieldsName.GIFT_CERTIFICATE).get(EntityFieldsName.ID),
                certificateId));
        List<Order> orders = em.createQuery(criteria).getResultList();
        em.close();
        return orders;
    }

    @Override
    public Optional<Order> findById(long id) {
        EntityManager em = factory.createEntityManager();
        Optional<Order> order = Optional.ofNullable(em.find(Order.class, id));
        em.close();
        return order;
    }

    @Override
    public Optional<Order> findByUserIdAndOrderId(long certificateId, long orderId) {
        EntityManager em = factory.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Order> criteria = builder.createQuery(Order.class);
        Root<Order> root = criteria.from(Order.class);
        Predicate userPredicate = builder.equal(root.get(EntityFieldsName.USER).get(EntityFieldsName.ID), certificateId);
        Predicate orderPredicate = builder.equal(root.get(EntityFieldsName.ID), orderId);
        criteria.where(userPredicate, orderPredicate);
        Optional<Order> result = em.createQuery(criteria).getResultStream().findAny();
        em.close();
        return result;
    }
}
