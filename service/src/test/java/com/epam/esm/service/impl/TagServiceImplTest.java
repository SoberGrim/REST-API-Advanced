package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.Tag;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The type Tag service impl test.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TagServiceImplTest {
    @InjectMocks
    private TagServiceImpl service;

    @Mock
    private TagDao<Tag> dao;

    /**
     * Init.
     */
    @BeforeAll
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Insert test.
     */
    @Test
    public void insertTest() {
        long expected = 11;
        Tag tag = new Tag("#new");
        Mockito.when(dao.insert(tag)).thenReturn(11L);
        Mockito.when(dao.findByName(Mockito.anyString())).thenReturn(Optional.empty());
        long actual = service.insert(tag);
        assertEquals(expected, actual);
    }

    /**
     * Delete test.
     */
    @Test
    public void deleteTest() {
        Mockito.when(dao.delete(Mockito.anyLong())).thenReturn(true);
        boolean actual = service.delete("11");
        assertTrue(actual);
    }

    /**
     * Find by id test.
     */
    @Test
    public void findByIdTest() {
        Tag expected = new Tag("#cool");
        Mockito.when(dao.findById(Mockito.anyLong())).thenReturn(Optional.of(expected));
        Tag actual = service.findById("11");
        assertEquals(expected, actual);
    }
}
