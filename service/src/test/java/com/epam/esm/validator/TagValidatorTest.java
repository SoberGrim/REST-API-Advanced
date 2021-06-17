package com.epam.esm.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TagValidatorTest {
    @Test
    public void isNameValidTest1() {
        boolean actual = TagValidator.isNameValid("#validName");
        assertTrue(actual);
    }

    @Test
    public void isNameValidTest2() {
        boolean actual = TagValidator.isNameValid("");
        assertFalse(actual);
    }
}
