package com.epam.esm.service;

import com.epam.esm.dto.Tag;

import java.util.List;

public interface TagService<T extends Tag> {
    long insert(Tag tag);

    T findById(String id);

    List<T> findAll(int page, int elements);

    boolean delete(String id);
}
