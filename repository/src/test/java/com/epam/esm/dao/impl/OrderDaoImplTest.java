package com.epam.esm.dao.impl;

import com.epam.esm.config.EntityManagerFactoryConfiguration;
import com.epam.esm.dao.OrderDao;
import com.epam.esm.dao.creator.GiftCertificateQueryCreator;
import com.epam.esm.dto.GiftCertificate;
import com.epam.esm.dto.Order;
import com.epam.esm.dto.Tag;
import com.epam.esm.dto.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DirtiesContext
@ContextConfiguration(classes = {OrderDaoImpl.class, GiftCertificateQueryCreator.class,
        EntityManagerFactoryConfiguration.class}, loader = AnnotationConfigContextLoader.class)
@SpringBootTest
public class OrderDaoImplTest {

    @Autowired
    private OrderDao<Order> dao;

    @Test
    public void insertTest() {
        Order order = new Order();
        order.setPrice(BigDecimal.TEN);
        order.setUser(new User(1, "Alice", "Green", "alice@gmail.com"));
        order.setTimestamp(LocalDateTime.now());

        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag(3, "#warm"));

        order.setGiftCertificate(new GiftCertificate(5, "Ferry", "Ferryman", BigDecimal.valueOf(0.99), 14,
                LocalDateTime.of(2019, 11, 19, 11, 10, 11, 111000000), null, tags));

        long expected = 6;
        long actual = dao.insert(order);
        assertEquals(expected, actual);
    }

    @Test
    public void findByIdTest() {
        Optional<Order> expected = Optional.empty();
        Optional<Order> actual = dao.findById(12345);
        assertEquals(expected, actual);
    }

    @Test
    public void deleteByCertificateIdTest() {
        boolean actual = dao.deleteByCertificateId(1);
        assertTrue(actual);
    }
}
