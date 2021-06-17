package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.constant.SqlTagQuery;
import com.epam.esm.dao.mapper.TagMapper;
import com.epam.esm.dto.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

/**
 * The type Tag dao.
 */
@Repository
public class TagDaoImpl implements TagDao<Tag> {
    private final JdbcTemplate template;
    private final TagMapper mapper;

    /**
     * Instantiates a new Tag dao.
     *
     * @param dataSource the data source
     * @param mapper     the mapper
     */
    @Autowired
    public TagDaoImpl(DataSource dataSource, TagMapper mapper) {
        this.template = new JdbcTemplate(dataSource);
        this.mapper = mapper;
    }

    @Override
    public boolean insert(Tag tag) {
        return template.update(SqlTagQuery.SQL_INSERT_TAG, tag.getName()) == 1;
    }

    @Override
    public Optional<Tag> findById(long id) {
        return template.query(SqlTagQuery.SQL_SELECT_TAG_BY_ID, mapper, new Object[]{id}).stream()
                .findAny();
    }

    @Override
    public Optional<Tag> findByName(String name) {
        return template.query(SqlTagQuery.SQL_SELECT_TAG_BY_NAME, mapper, new Object[]{name}).stream().findAny();
    }

    @Override
    public List<Tag> findAll() {
        return template.query(SqlTagQuery.SQL_SELECT_ALL_TAGS, mapper);
    }

    @Override
    public List<Tag> findTagsConnectedToCertificate(long id) {
        return template.query(SqlTagQuery.SQL_SELECT_TAGS_CONNECTED_WITH_CERTIFICATE, mapper, id);
    }

    @Override
    public boolean delete(long id) {
        return template.update(SqlTagQuery.SQL_DELETE_TAG_BY_ID, id) == 1;
    }
}
