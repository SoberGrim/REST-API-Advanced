package com.epam.esm.controller;

import com.epam.esm.attribute.ResponseAttribute;
import com.epam.esm.dto.GiftCertificate;
import com.epam.esm.dto.Order;
import com.epam.esm.hateoas.Hateoas;
import com.epam.esm.response.OperationResponse;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private final GiftCertificateService<GiftCertificate> certificateService;
    private final OrderService<Order> orderService;
    private final Hateoas<GiftCertificate> certificateHateoas;
    private final Hateoas<OperationResponse> responseHateoas;

    /**
     * Instantiates a new Gift certificate controller.
     *
     * @param certificateService the certificate service
     * @param orderService       the order service
     * @param certificateHateoas the certificate hateoas
     * @param responseHateoas    the response hateoas
     */
    @Autowired
    public GiftCertificateController(GiftCertificateService<GiftCertificate> certificateService, OrderService<Order>
            orderService, Hateoas<GiftCertificate> certificateHateoas, @Qualifier("certificateOperationResponseHateoas")
                                             Hateoas<OperationResponse> responseHateoas) {
        this.certificateService = certificateService;
        this.orderService = orderService;
        this.certificateHateoas = certificateHateoas;
        this.responseHateoas = responseHateoas;
    }

    /**
     * Find all gift certificates list.
     *
     * @param page     the page
     * @param elements the elements
     * @return the list
     */
    @GetMapping("/all")
    public List<GiftCertificate> findAllGiftCertificates(@RequestParam int page, @RequestParam int elements) {
        List<GiftCertificate> giftCertificates = certificateService.findAll(page, elements);
        giftCertificates.forEach(certificateHateoas::createHateoas);
        return giftCertificates;
    }

    /**
     * Find certificates with tags list.
     *
     * @param page                   the page
     * @param elements               the elements
     * @param tagsNames              the tags names
     * @param certificateName        the certificate name
     * @param certificateDescription the certificate description
     * @param sortByName             the sort by name
     * @param sortByDate             the sort by date
     * @return the list
     */
    @GetMapping
    public List<GiftCertificate> findCertificatesWithTags(@RequestParam int page, @RequestParam int elements,
                                                          @RequestParam(required = false) List<String> tagsNames,
                                                          @RequestParam(required = false) String certificateName,
                                                          @RequestParam(required = false) String certificateDescription,
                                                          @RequestParam(required = false) String sortByName,
                                                          @RequestParam(required = false) String sortByDate) {
        List<GiftCertificate> giftCertificates = certificateService.findCertificatesWithTagsByCriteria(page, elements, tagsNames,
                certificateName, certificateDescription, sortByName, sortByDate);
        giftCertificates.forEach(certificateHateoas::createHateoas);
        return giftCertificates;
    }

    /**
     * Create gift certificate operation response.
     *
     * @param giftCertificate the gift certificate
     * @return the operation response
     */
    @PostMapping("/new")
    public OperationResponse createGiftCertificate(@RequestBody GiftCertificate giftCertificate) {
        OperationResponse response = new OperationResponse(OperationResponse.Operation.CREATION,
                ResponseAttribute.CERTIFICATE_CREATE_OPERATION, String.valueOf(certificateService.insert(giftCertificate)));
        responseHateoas.createHateoas(response);
        return response;
    }

    /**
     * Find certificate by id gift certificate.
     *
     * @param id the id
     * @return the gift certificate
     */
    @GetMapping("/{id}")
    public GiftCertificate findCertificateById(@PathVariable String id) {
        GiftCertificate giftCertificate = certificateService.findById(id);
        certificateHateoas.createHateoas(giftCertificate);
        return giftCertificate;
    }

    /**
     * Delete gift certificate operation response.
     *
     * @param id the id
     * @return the operation response
     */
    @DeleteMapping("/{id}")
    public OperationResponse deleteGiftCertificate(@PathVariable String id) {
        orderService.deleteByCertificateId(id);
        certificateService.delete(id);
        OperationResponse response = new OperationResponse(OperationResponse.Operation.DELETION,
                ResponseAttribute.CERTIFICATE_DELETE_OPERATION, id);
        responseHateoas.createHateoas(response);
        return response;
    }

    /**
     * Update gift certificate operation response.
     *
     * @param id              the id
     * @param giftCertificate the gift certificate
     * @return the operation response
     */
    @PatchMapping("/{id}")
    public OperationResponse updateGiftCertificate(@PathVariable String id,
                                                   @RequestBody GiftCertificate giftCertificate) {
        certificateService.update(id, giftCertificate);
        OperationResponse response = new OperationResponse(OperationResponse.Operation.UPDATE,
                ResponseAttribute.CERTIFICATE_UPDATE_OPERATION, id);
        responseHateoas.createHateoas(response);
        return response;
    }
}
