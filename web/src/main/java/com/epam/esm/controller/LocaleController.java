package com.epam.esm.controller;

import com.epam.esm.attribute.OperationResponseAttribute;
import com.epam.esm.response.OperationResponse;
import com.epam.esm.util.MessageLocale;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locale")
public class LocaleController {
    @GetMapping
    public OperationResponse changeLocale() {
        MessageLocale.changeLocale();
        return new OperationResponse(OperationResponse.Operation.OTHER,
                OperationResponseAttribute.LOCALE_CHANGE_OPERATION, MessageLocale.getCurrent().toString());

    }
}
