package com.epam.esm.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * The type Gift certificate.
 */
public class GiftCertificate {
    private long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int duration;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private List<Tag> tags;

    /**
     * Instantiates a new Gift certificate.
     */
    public GiftCertificate() {
    }

    /**
     * Instantiates a new Gift certificate.
     *
     * @param name        the name
     * @param description the description
     * @param price       the price
     * @param duration    the duration
     */
    public GiftCertificate(String name, String description, BigDecimal price, int duration) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
    }

    /**
     * Instantiates a new Gift certificate.
     *
     * @param name           the name
     * @param description    the description
     * @param price          the price
     * @param duration       the duration
     * @param createDate     the create date
     * @param lastUpdateDate the last update date
     * @param tags           the tags
     */
    public GiftCertificate(String name, String description, BigDecimal price, int duration, LocalDateTime createDate,
                           LocalDateTime lastUpdateDate, List<Tag> tags) {
        this(name, description, price, duration);
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.tags = tags;
    }

    /**
     * Instantiates a new Gift certificate.
     *
     * @param id             the id
     * @param name           the name
     * @param description    the description
     * @param price          the price
     * @param duration       the duration
     * @param createDate     the create date
     * @param lastUpdateDate the last update date
     * @param tags           the tags
     */
    public GiftCertificate(long id, String name, String description, BigDecimal price, int duration,
                           LocalDateTime createDate, LocalDateTime lastUpdateDate, List<Tag> tags) {
        this(name, description, price, duration, createDate, lastUpdateDate, tags);
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

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets duration.
     *
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets duration.
     *
     * @param duration the duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Gets create date.
     *
     * @return the create date
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Sets create date.
     *
     * @param createDate the create date
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets last update date.
     *
     * @return the last update date
     */
    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * Sets last update date.
     *
     * @param lastUpdateDate the last update date
     */
    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * Gets tags.
     *
     * @return the tags
     */
    public List<Tag> getTags() {
        return tags;
    }

    /**
     * Sets tags.
     *
     * @param tags the tags
     */
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        GiftCertificate that = (GiftCertificate) o;
        return (duration == that.duration && name.equals(that.name) && description.equals(that.description) &&
                price.equals(that.price) && createDate.equals(that.createDate) &&
                Objects.equals(lastUpdateDate, that.lastUpdateDate) && tags.equals(that.tags));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, price, duration, createDate, lastUpdateDate, tags);
    }

    @Override
    public String toString() {
        return ("id = " + id +
                "\nname = " + name +
                "\ndescription = " + description +
                "\nprice = " + price +
                "\nduration = " + duration +
                "\ncreateDate = " + createDate +
                "\nlastUpdateDate = " + lastUpdateDate +
                "\ntags = " + tags + "\n");
    }
}
