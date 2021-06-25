package com.epam.esm.validator;

import java.util.regex.Pattern;

public class UserValidator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("((\\w)([-.](\\w))?){1,64}@((\\w)([-.](\\w))?){1,251}.[a-zA-Z]{2,4}");

    public static boolean isEmailValid(String email) {
        if (email == null) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
