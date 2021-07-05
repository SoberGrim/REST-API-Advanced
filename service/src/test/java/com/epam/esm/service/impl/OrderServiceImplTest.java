package com.epam.esm.service.impl;

import com.epam.esm.dao.OrderDao;
import com.epam.esm.dto.GiftCertificate;
import com.epam.esm.dto.Order;
import com.epam.esm.dto.Tag;
import com.epam.esm.dto.User;
import com.epam.esm.exception.DeleteCertificateInUseException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * The type Order service impl test.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderServiceImplTest {
    /**
     * The Order service.
     */
    @InjectMocks
    public OrderServiceImpl orderService;

    @Mock
    private OrderDao<Order> dao;

    @Mock
    private UserService<User> userService;

    @Mock
    private GiftCertificateService<GiftCertificate> certificateService;

    /**
     * Init.
     */
    @BeforeAll
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Create order test.
     */
    @Test
    public void createOrderTest() {
        long expected = 11;
        Mockito.when(dao.insert(Mockito.any()))
                .thenReturn(expected);

        Mockito.when(userService.findById(Mockito.anyString()))
                .thenReturn(new User(1, "Alice", "Green", "alice@gmail.com"));

        Mockito.when(certificateService.findById(Mockito.anyString()))
                .thenReturn(new GiftCertificate(2, "Sand", "Yellow sand", new BigDecimal("2"),
                        24, LocalDateTime.of(2020, 5, 5, 23, 42, 12,
                        112000000), null, new HashSet<>()));

        long actual = orderService.createOrder("1", "1");
        assertEquals(expected, actual);
    }

    /**
     * Find by id test.
     */
    @Test
    public void findByIdTest() {
        Order expected = new Order();
        expected.setPrice(BigDecimal.TEN);
        expected.setUser(new User(1, "Alice", "Green", "alice@gmail.com"));
        expected.setTimestamp(LocalDateTime.now());

        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag(3, "#warm"));

        expected.setGiftCertificate(new GiftCertificate(5, "Ferry", "Ferryman", BigDecimal.valueOf(0.99), 14,
                LocalDateTime.of(2019, 11, 19, 11, 10, 11, 111000000), null, tags));

        Mockito.when(dao.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(expected));

        Order actual = orderService.findById("11");
        assertEquals(expected, actual);
    }

    /**
     * Delete by certificate id test.
     */
    @Test
    public void deleteByCertificateIdTest() {
        Order order = new Order();
        order.setPrice(BigDecimal.TEN);
        order.setUser(new User(1, "Alice", "Green", "alice@gmail.com"));
        order.setTimestamp(LocalDateTime.now());

        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag(3, "#warm"));

        GiftCertificate certificate = new GiftCertificate(5, "Ferry", "Ferryman",
                BigDecimal.valueOf(0.99), 26, LocalDateTime.of(2021, 6, 26, 11,
                10, 11, 111000000), null, tags);
        order.setGiftCertificate(certificate);

        Mockito.when(certificateService.findById(Mockito.anyString()))
                .thenReturn(certificate);

        Mockito.when(dao.findByCertificateId(Mockito.anyLong()))
                .thenReturn(Collections.singletonList(order));

        assertThrows(DeleteCertificateInUseException.class, () -> orderService.deleteByCertificateId("1"));
    }
}
