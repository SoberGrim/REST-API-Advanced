package com.epam.esm.hateoas;

public interface ControllerHateoas<T> {
    void createHateoas(T model);
}
