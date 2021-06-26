package com.epam.esm.dao;

import com.epam.esm.dto.Tag;

import java.util.List;
import java.util.Optional;

public interface TagDao<T extends Tag> {
    long insert(T t);

    Optional<T> findById(long id);

    Optional<T> findByName(String name);

    Optional<T> findMostUsedTagOfUserWithHighestCostOfAllOrders(long userId);

    List<T> findAll(int page, int elements);

    boolean delete(long id);
}
