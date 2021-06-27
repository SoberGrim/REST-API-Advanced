package com.epam.esm.service.impl;

import com.epam.esm.dao.UserDao;
import com.epam.esm.dto.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type User service impl test.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserDao<User> dao;

    /**
     * Init.
     */
    @BeforeAll
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Find by id.
     */
    @Test
    public void findById() {
        User expected = new User(1, "Alice", "Green", "alice@gmail.com");
        Mockito.when(dao.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(expected));
        User actual = service.findById("11");
        assertEquals(expected, actual);
    }

    /**
     * Find all test.
     */
    @Test
    public void findAllTest() {
        List<User> expected = new ArrayList<>();
        Mockito.when(dao.findAll(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(expected);
        List<User> actual = service.findAll(1, 5);
        assertEquals(expected, actual);
    }
}
