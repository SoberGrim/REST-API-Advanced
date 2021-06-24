package com.epam.esm.controller;

import com.epam.esm.attribute.ResponseAttribute;
import com.epam.esm.dto.GiftCertificate;
import com.epam.esm.response.OperationResponse;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/certificates")
public class GiftCertificateController {
    private final GiftCertificateService<GiftCertificate> service;

    @Autowired
    public GiftCertificateController(GiftCertificateService<GiftCertificate> service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<GiftCertificate> findAllGiftCertificates() {
        return service.findAll();
    }

    @GetMapping
    public List<GiftCertificate> findCertificatesWithTags(@RequestParam(required = false) String tagName,
                                                          @RequestParam(required = false) String certificateName,
                                                          @RequestParam(required = false) String certificateDescription,
                                                          @RequestParam(required = false) String sortByName,
                                                          @RequestParam(required = false) String sortByDate) {
        return service.findCertificatesWithTagsByCriteria(tagName, certificateName, certificateDescription, sortByName,
                sortByDate);
    }

    @PostMapping("/new")
    public OperationResponse createGiftCertificate(@RequestBody GiftCertificate giftCertificate) {
        OperationResponse response = new OperationResponse(OperationResponse.Operation.CREATION,
                ResponseAttribute.CERTIFICATE_CREATE_OPERATION, String.valueOf(service.insert(giftCertificate)));

        return response;
    }

    @GetMapping("/{id}")
    public GiftCertificate findCertificateById(@PathVariable String id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGiftCertificate(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Gift certificate deleted successfully" +
                " (id = " + id + ")");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateGiftCertificate(@PathVariable String id,
                                                        @RequestBody GiftCertificate giftCertificate) {
        service.update(id, giftCertificate);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Gift certificate updated successfully" +
                " (id = " + id + ")");
    }
}
