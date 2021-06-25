package com.epam.esm.dao.impl;

import com.epam.esm.dao.UserDao;
import com.epam.esm.dao.constant.EntityFieldsName;
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

@Repository
public class UserDaoImpl implements UserDao<User> {
    private final EntityManagerFactory factory;

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
    public List<User> findWithGiftCertificates(int page, int elements) {
        EntityManager em = factory.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.where(builder.isNotEmpty(root.get(EntityFieldsName.GIFT_CERTIFICATES)));
        List<User> users = (page > 0 && elements > 0) ? em.createQuery(criteria).setMaxResults(elements)
                .setFirstResult(elements * (page - 1)).getResultList() : em.createQuery(criteria).getResultList();
        em.close();
        return users;
    }
}
