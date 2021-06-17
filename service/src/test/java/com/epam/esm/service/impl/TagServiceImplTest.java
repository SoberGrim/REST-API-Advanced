package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.Tag;
import com.epam.esm.service.TagService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TagServiceImplTest {
    private TagService<Tag> service;
    @Mock
    private TagDao<Tag> dao;


    @BeforeAll
    public void init() {
        MockitoAnnotations.initMocks(this);
        service = new TagServiceImpl(dao);
    }

    @Test
    public void insertTest() {
        Tag tag = new Tag("#new");
        Mockito.when(dao.insert(tag)).thenReturn(true);
        Mockito.when(dao.findByName(Mockito.anyString())).thenReturn(Optional.empty());
        boolean actual = service.insert(tag);
        assertTrue(actual);
    }

    @Test
    public void deleteTest() {
        Mockito.when(dao.delete(Mockito.anyLong())).thenReturn(true);
        boolean actual = service.delete("5");
        assertTrue(actual);
    }

    @Test
    public void findByIdTest() {
        Tag expected = new Tag("#cool");
        Mockito.when(dao.findById(Mockito.anyLong())).thenReturn(Optional.of(expected));
        Tag actual = service.findById("12");
        assertEquals(expected, actual);
    }
}
