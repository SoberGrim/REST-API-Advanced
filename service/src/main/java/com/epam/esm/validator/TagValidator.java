package com.epam.esm.validator;

import java.util.regex.Pattern;

/**
 * The type Tag validator.
 */
public final class TagValidator {
    private static final Pattern NAME_PATTERN = Pattern.compile("[А-Яа-я\\w\\s\\p{Punct}]{1,256}");

    private TagValidator() {
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
}
