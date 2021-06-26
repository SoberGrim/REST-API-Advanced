package com.epam.esm.dao.impl;

import com.epam.esm.dao.OrderDao;
import com.epam.esm.dto.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
public class OrderDaoImpl implements OrderDao<Order> {
    private final EntityManagerFactory factory;

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
}
