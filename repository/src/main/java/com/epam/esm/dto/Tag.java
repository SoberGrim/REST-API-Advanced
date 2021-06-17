package com.epam.esm.dto;

import java.util.Objects;

/**
 * The type Tag.
 */
public class Tag {
    private long id;
    private String name;

    /**
     * Instantiates a new Tag.
     */
    public Tag() {
    }

    /**
     * Instantiates a new Tag.
     *
     * @param name the name
     */
    public Tag(String name) {
        this.name = name;
    }

    /**
     * Instantiates a new Tag.
     *
     * @param id   the id
     * @param name the name
     */
    public Tag(long id, String name) {
        this(name);
        this.id = id;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Tag tag = (Tag) o;
        return name.equals(tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
