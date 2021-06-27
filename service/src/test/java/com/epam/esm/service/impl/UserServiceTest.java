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

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {
    @InjectMocks
    public UserServiceImpl service;

    @Mock
    private UserDao<User> dao;

    @BeforeAll
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findById() {
        User expected = new User(1, "Alice", "Green", "alice@gmail.com");
        Mockito.when(dao.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(expected));
        User actual = service.findById("11");
        assertEquals(expected, actual);
    }

    @Test
    public void findAllTest() {
        List<User> expected = new ArrayList<>();
        Mockito.when(dao.findAll(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(expected);
        List<User> actual = service.findAll(1, 5);
        assertEquals(expected, actual);
    }
}
