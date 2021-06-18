package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.constant.SqlTagQuery;
import com.epam.esm.dao.mapper.TagMapper;
import com.epam.esm.dto.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class TagDaoImpl implements TagDao<Tag> {
    private final EntityManager em;

    @Autowired
    public TagDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public boolean insert(Tag tag) {
        return true;
    }

    @Override
    public Optional<Tag> findById(long id) {
        return Optional.ofNullable(em.find(Tag.class, id));
    }

    @Override
    public Optional<Tag> findByName(String name) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Tag> tagCriteria = criteriaBuilder.createQuery(Tag.class);
        Root<Tag> tagRoot = tagCriteria.from(Tag.class);
        tagCriteria.where(criteriaBuilder.equal(tagRoot.get("name"), name));
        return Optional.ofNullable(em.createQuery(tagCriteria).getSingleResult());
    }

    @Override
    public List<Tag> findAll() {
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
        return true;
    }
}
