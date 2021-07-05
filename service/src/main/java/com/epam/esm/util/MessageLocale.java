package com.epam.esm.util;

import java.util.Locale;

/**
 * The type Message locale.
 */
public final class MessageLocale {
    private static Locale current;

    static {
        current = new Locale(CustomLocale.EN.language, CustomLocale.EN.country);
    }

    private MessageLocale() {
    }

    /**
     * Gets current.
     *
     * @return the current
     */
    public static Locale getCurrent() {
        return current;
    }

    /**
     * Change locale.
     */
    public static void changeLocale() {
        current = current.getLanguage().equals(CustomLocale.EN.language) ? new Locale(CustomLocale.RU.language,
                CustomLocale.RU.country) : new Locale(CustomLocale.EN.language, CustomLocale.EN.country);
    }

    private enum CustomLocale {
        /**
         * Ru custom locale.
         */
        RU("ru", "RU"),
        /**
         * En custom locale.
         */
        EN("en", "US");

        private final String language;
        private final String country;

        CustomLocale(String language, String country) {
            this.language = language;
            this.country = country;
        }

        /**
         * Gets language.
         *
         * @return the language
         */
        public String getLanguage() {
            return language;
        }

        /**
         * Gets country.
         *
         * @return the country
         */
        public String getCountry() {
            return country;
        }
    }
}
