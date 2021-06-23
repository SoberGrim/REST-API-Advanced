package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.Tag;
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
public class TagDaoImpl implements TagDao<Tag> {
    private final EntityManagerFactory factory;

    @Autowired
    public TagDaoImpl(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public boolean insert(Tag tag) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(tag);
        em.getTransaction().commit();
        return true;
    }

    @Override
    public Optional<Tag> findById(long id) {
        return Optional.ofNullable(factory.createEntityManager().find(Tag.class, id));
    }

    @Override
    public Optional<Tag> findByName(String name) {
        EntityManager em = factory.createEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Tag> tagCriteria = criteriaBuilder.createQuery(Tag.class);
        Root<Tag> tagRoot = tagCriteria.from(Tag.class);
        tagCriteria.where(criteriaBuilder.equal(tagRoot.get("name"), name));
        return em.createQuery(tagCriteria).getResultStream().findAny();
    }

    @Override
    public List<Tag> findAll() {
        EntityManager em = factory.createEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Tag> tagCriteria = criteriaBuilder.createQuery(Tag.class);
        Root<Tag> tagRoot = tagCriteria.from(Tag.class);
        tagCriteria.select(tagRoot);
        return em.createQuery(tagCriteria).getResultList();
    }

    @Override
    public List<Tag> findTagsConnectedToCertificate(long id) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Tag tag = findById(id).get();
        em.remove(em.contains(tag)? tag : em.merge(tag));
        em.getTransaction().commit();
        return true;
    }
}
