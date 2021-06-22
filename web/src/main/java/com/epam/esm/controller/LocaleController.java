package com.epam.esm.controller;

import com.epam.esm.util.ExceptionMessageLocale;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locale")
public class LocaleController {
    @GetMapping
    public ResponseEntity<String> changeLocale() {
        ExceptionMessageLocale.changeLocale();
        return ResponseEntity.status(HttpStatus.OK).body("Locale changed successful. Current locale: " +
                ExceptionMessageLocale.getCurrent());
    }
}
