package com.epam.esm.hateoas;

/**
 * The interface Hateoas.
 *
 * @param <T> the type parameter
 */
public interface Hateoas<T> {
    /**
     * Create hateoas.
     *
     * @param model the model
     */
    void createHateoas(T model);
}
