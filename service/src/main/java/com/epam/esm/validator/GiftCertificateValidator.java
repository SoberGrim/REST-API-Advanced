package com.epam.esm.validator;

import com.epam.esm.dto.GiftCertificate;
import com.epam.esm.dto.Tag;

import java.math.BigDecimal;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * The type Gift certificate validator.
 */
public class GiftCertificateValidator {
    private static final Pattern NAME_PATTERN = Pattern.compile("[А-Яа-я\\w\\s\\p{Punct}]{1,256}");
    private static final Pattern DESCRIPTION_PATTERN = Pattern.compile("[А-Яа-я\\w\\s\\p{Punct}]{1,5000}");

    private GiftCertificateValidator() {
    }

    /**
     * Is gift certificate creation form valid boolean.
     *
     * @param giftCertificate the gift certificate
     * @return the boolean
     */
    public static boolean isGiftCertificateCreationFormValid(GiftCertificate giftCertificate) {
        return (isNameValid(giftCertificate.getName()) && isDescriptionValid(giftCertificate.getDescription()) &&
                isPriceValid(giftCertificate.getPrice()) && isDurationValid(giftCertificate.getDuration()) &&
                giftCertificate.getCreateDate() == null && giftCertificate.getLastUpdateDate() == null &&
                areGiftCertificateTagsValidForCreation(giftCertificate.getTags()));
    }

    /**
     * Is name valid boolean.
     *
     * @param name the name
     * @return the boolean
     */
    public static boolean isNameValid(String name) {
        return (name != null && NAME_PATTERN.matcher(name).matches());
    }

    /**
     * Is description valid boolean.
     *
     * @param description the description
     * @return the boolean
     */
    public static boolean isDescriptionValid(String description) {
        return (description != null && DESCRIPTION_PATTERN.matcher(description).matches());
    }

    /**
     * Is price valid boolean.
     *
     * @param price the price
     * @return the boolean
     */
    public static boolean isPriceValid(BigDecimal price) {
        return (price != null && price.compareTo(BigDecimal.ZERO) > 0);
    }

    /**
     * Is duration valid boolean.
     *
     * @param duration the duration
     * @return the boolean
     */
    public static boolean isDurationValid(int duration) {
        return duration > 0;
    }

    /**
     * Are gift certificate tags valid for creation boolean.
     *
     * @param tags the tags
     * @return the boolean
     */
    public static boolean areGiftCertificateTagsValidForCreation(Set<Tag> tags) {
        if (tags == null || tags.isEmpty()) {
            return true;
        }
        return tags.stream()
                .allMatch(t -> TagValidator.isNameValid(t.getName()));
    }

    /**
     * Are gift certificate tags valid boolean.
     *
     * @param tags the tags
     * @return the boolean
     */
    public static boolean areGiftCertificateTagsValid(Set<Tag> tags) {
        if (tags == null || tags.isEmpty()) {
            return false;
        }
        return tags.stream()
                .allMatch(t -> TagValidator.isNameValid(t.getName()));
    }
}
