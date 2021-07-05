package com.epam.esm.dao.impl;

import com.epam.esm.config.EntityManagerFactoryConfiguration;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The type Tag dao impl test.
 */
@DirtiesContext
@ContextConfiguration(classes = {TagDaoImpl.class, EntityManagerFactoryConfiguration.class},
        loader = AnnotationConfigContextLoader.class)
@SpringBootTest
public class TagDaoImplTest {

    @Autowired
    private TagDao<Tag> dao;

    /**
     * Find by name test.
     */
    @Test
    public void findByNameTest() {
        Optional<Tag> expected = Optional.of(new Tag("#funny"));
        Optional<Tag> actual = dao.findByName("#funny");
        assertEquals(expected, actual);
    }

    /**
     * Find by id test.
     */
    @Test
    public void findByIdTest() {
        Optional<Tag> expected = Optional.empty();
        Optional<Tag> actual = dao.findById(12345);
        assertEquals(expected, actual);
    }

    /**
     * Insert test.
     */
    @Test
    public void insertTest() {
        long expected = 6;
        long actual = dao.insert(new Tag("#newtaginserttest"));
        assertEquals(expected, actual);
    }

    /**
     * Delete test.
     */
    @Test
    public void deleteTest() {
        boolean actual = dao.delete(6);
        assertTrue(actual);
    }
}
