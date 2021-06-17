package com.epam.esm.controller;

import com.epam.esm.dto.GiftCertificate;
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

/**
 * The type Gift certificate controller.
 */
@RestController
@RequestMapping("/certificates")
public class GiftCertificateController {
    private final GiftCertificateService<GiftCertificate> service;

    /**
     * Instantiates a new Gift certificate controller.
     *
     * @param service the service
     */
    @Autowired
    public GiftCertificateController(GiftCertificateService<GiftCertificate> service) {
        this.service = service;
    }

    /**
     * Find all gift certificates list.
     *
     * @return the list
     */
    @GetMapping("/all")
    public List<GiftCertificate> findAllGiftCertificates() {
        return service.findAll();
    }

    /**
     * Find certificates with tags list.
     *
     * @param tagName                the tag name
     * @param certificateName        the certificate name
     * @param certificateDescription the certificate description
     * @param sortByName             the sort by name
     * @param sortByDate             the sort by date
     * @return the list
     */
    @GetMapping
    public List<GiftCertificate> findCertificatesWithTags(@RequestParam(required = false) String tagName,
                                                          @RequestParam(required = false) String certificateName,
                                                          @RequestParam(required = false) String certificateDescription,
                                                          @RequestParam(required = false) String sortByName,
                                                          @RequestParam(required = false) String sortByDate) {
        return service.findCertificatesWithTagsByCriteria(tagName, certificateName, certificateDescription, sortByName,
                sortByDate);
    }

    /**
     * Create gift certificate response entity.
     *
     * @param giftCertificate the gift certificate
     * @return the response entity
     */
    @PostMapping("/new")
    public ResponseEntity<String> createGiftCertificate(@RequestBody GiftCertificate giftCertificate) {
        service.insert(giftCertificate);
        return ResponseEntity.status(HttpStatus.CREATED).body("Certificate created successfully");
    }

    /**
     * Find certificate by id gift certificate.
     *
     * @param id the id
     * @return the gift certificate
     */
    @GetMapping("/{id}")
    public GiftCertificate findCertificateById(@PathVariable String id) {
        return service.findById(id);
    }

    /**
     * Delete gift certificate response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGiftCertificate(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Gift certificate deleted successfully" +
                " (id = " + id + ")");
    }

    /**
     * Update gift certificate response entity.
     *
     * @param id              the id
     * @param giftCertificate the gift certificate
     * @return the response entity
     */
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateGiftCertificate(@PathVariable String id,
                                                        @RequestBody GiftCertificate giftCertificate) {
        service.update(id, giftCertificate);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Gift certificate updated successfully" +
                " (id = " + id + ")");
    }
}
