package com.epam.esm.hateoas;

public interface Hateoas<T> {
    void createHateoas(T model);
}
