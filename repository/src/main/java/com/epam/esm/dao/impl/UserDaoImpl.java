package com.epam.esm.dao.impl;

import com.epam.esm.dao.UserDao;
import com.epam.esm.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

/**
 * The type User dao.
 */
@Repository
public class UserDaoImpl implements UserDao<User> {
    private final EntityManagerFactory factory;

    /**
     * Instantiates a new User dao.
     *
     * @param factory the factory
     */
    @Autowired
    public UserDaoImpl(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public Optional<User> findById(long id) {
        EntityManager em = factory.createEntityManager();
        Optional<User> user = Optional.ofNullable(em.find(User.class, id));
        em.close();
        return user;
    }

    @Override
    public List<User> findAll(int page, int elements) {
        EntityManager em = factory.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root);
        List<User> users = (page > 0 && elements > 0) ? em.createQuery(criteria).setMaxResults(elements)
                .setFirstResult(elements * (page - 1)).getResultList() : em.createQuery(criteria).getResultList();
        em.close();
        return users;
    }

    @Override
    public boolean update(User user) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        em.close();
        return true;
    }
}
