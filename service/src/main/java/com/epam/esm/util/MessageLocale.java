package com.epam.esm.util;

import java.util.Locale;

public final class MessageLocale {
    private static Locale current;

    static {
        current = new Locale(CustomLocale.EN.language, CustomLocale.EN.country);
    }

    private MessageLocale() {
    }

    public static Locale getCurrent() {
        return current;
    }

    public static void changeLocale() {
        current = current.getLanguage().equals(CustomLocale.EN.language) ? new Locale(CustomLocale.RU.language,
                CustomLocale.RU.country) : new Locale(CustomLocale.EN.language, CustomLocale.EN.country);
    }

    private enum CustomLocale {
        RU("ru", "RU"),
        EN("en", "US");

        private final String language;
        private final String country;

        CustomLocale(String language, String country) {
            this.language = language;
            this.country = country;
        }

        public String getLanguage() {
            return language;
        }

        public String getCountry() {
            return country;
        }
    }
}
