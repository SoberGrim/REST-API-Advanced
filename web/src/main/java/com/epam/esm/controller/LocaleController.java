package com.epam.esm.controller;

import com.epam.esm.attribute.ResponseAttribute;
import com.epam.esm.response.OperationResponse;
import com.epam.esm.util.MessageLocale;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Locale controller.
 */
@RestController
@RequestMapping("/locale")
public class LocaleController {
    /**
     * Change locale operation response.
     *
     * @return the operation response
     */
    @GetMapping
    public OperationResponse changeLocale() {
        MessageLocale.changeLocale();
        return new OperationResponse(OperationResponse.Operation.OTHER,
                ResponseAttribute.LOCALE_CHANGE_OPERATION, MessageLocale.getCurrent().toString());

    }
}
