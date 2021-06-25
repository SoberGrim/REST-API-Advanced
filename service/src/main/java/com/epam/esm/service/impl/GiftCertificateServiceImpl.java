package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.constant.EntityFieldsName;
import com.epam.esm.dao.constant.ErrorAttribute;
import com.epam.esm.dao.constant.Symbol;
import com.epam.esm.dao.creator.criteria.Criteria;
import com.epam.esm.dao.creator.criteria.search.FullMatchSearchCertificateCriteria;
import com.epam.esm.dao.creator.criteria.search.PartMatchSearchCertificateCriteria;
import com.epam.esm.dao.creator.criteria.sort.FieldSortCertificateCriteria;
import com.epam.esm.dto.GiftCertificate;
import com.epam.esm.dto.Tag;
import com.epam.esm.exception.InvalidFieldException;
import com.epam.esm.exception.ResourceNotFoundException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import com.epam.esm.validator.TagValidator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.epam.esm.validator.GiftCertificateValidator.areGiftCertificateTagsValid;
import static com.epam.esm.validator.GiftCertificateValidator.isDescriptionValid;
import static com.epam.esm.validator.GiftCertificateValidator.isDurationValid;
import static com.epam.esm.validator.GiftCertificateValidator.isGiftCertificateCreationFormValid;
import static com.epam.esm.validator.GiftCertificateValidator.isNameValid;
import static com.epam.esm.validator.GiftCertificateValidator.isPriceValid;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService<GiftCertificate> {
    private static final String ASC_SORT_ORDERING = "ASC";
    private static final String DESC_SORT_ORDERING = "DESC";
    private final GiftCertificateDao<GiftCertificate> dao;
    private final TagService<Tag> tagService;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDao<GiftCertificate> dao, TagService<Tag> tagService) {
        this.dao = dao;
        this.tagService = tagService;
    }

    @Override
    public long insert(GiftCertificate giftCertificate) {
        long id;
        if (isGiftCertificateCreationFormValid(giftCertificate)) {
            giftCertificate.setCreateDate(LocalDateTime.now());
            if (!CollectionUtils.isEmpty(giftCertificate.getTags())) {
                List<Tag> existingTags = (List<Tag>) CollectionUtils.intersection(tagService.findAll(0, 0),
                        giftCertificate.getTags());
                List<Tag> newTags = (List<Tag>) CollectionUtils.removeAll(giftCertificate.getTags(), existingTags);
                giftCertificate.setTags(newTags);
                id = dao.insert(giftCertificate);
                GiftCertificate certificateWithAllTags = dao.findById(id).get();
                certificateWithAllTags.setTags(ListUtils.union(newTags, existingTags));
                dao.update(certificateWithAllTags);
            } else {
                id = dao.insert(giftCertificate);
            }
        } else {
            throw new InvalidFieldException(ErrorAttribute.GIFT_CERTIFICATE_ERROR_CODE,
                    ErrorAttribute.INVALID_GIFT_CERTIFICATE_ERROR, giftCertificate.toString());
        }
        return id;
    }

    @Override
    public boolean delete(String id) {
        try {
            Optional<GiftCertificate> giftCertificateOptional = dao.findById(Long.parseLong(id));
            if (giftCertificateOptional.isPresent()) { // TODO: 6/24/2021 check if the certificate has been ordered by users
                GiftCertificate giftCertificate = giftCertificateOptional.get();
                if (!CollectionUtils.isEmpty(giftCertificate.getTags())) {
                    dao.disconnectAllTags(giftCertificate);
                }
                return dao.delete(giftCertificate.getId());
            } else {
                throw new ResourceNotFoundException(ErrorAttribute.GIFT_CERTIFICATE_ERROR_CODE,
                        ErrorAttribute.RESOURCE_NOT_FOUND_ERROR, id);
            }
        } catch (NumberFormatException e) {
            throw new InvalidFieldException(ErrorAttribute.GIFT_CERTIFICATE_ERROR_CODE,
                    ErrorAttribute.INVALID_GIFT_CERTIFICATE_ID_ERROR, id);
        }
    }

    @Override
    public boolean update(String id, GiftCertificate newCertificate) { // TODO: 6/24/2021 Maybe change function returning value (gift certificate)
        try {
            GiftCertificate oldCertificate = dao.findById(Long.parseLong(id)).orElseThrow(() ->
                    new ResourceNotFoundException(ErrorAttribute.GIFT_CERTIFICATE_ERROR_CODE,
                            ErrorAttribute.RESOURCE_NOT_FOUND_ERROR, id));

            if (updateCertificateFields(oldCertificate, newCertificate)) {
                oldCertificate.setLastUpdateDate(LocalDateTime.now());
                if (!CollectionUtils.isEmpty(oldCertificate.getTags())) {
                    List<Tag> newTags = (List<Tag>) CollectionUtils.removeAll(oldCertificate.getTags(),
                            CollectionUtils.intersection(tagService.findAll(0, 0), oldCertificate.getTags()));
                    newTags.forEach(tagService::insert);
                }
                dao.update(oldCertificate);
            } else {
                throw new InvalidFieldException(ErrorAttribute.GIFT_CERTIFICATE_ERROR_CODE,
                        ErrorAttribute.INVALID_GIFT_CERTIFICATE_ERROR, newCertificate.toString());
            }
        } catch (NumberFormatException e) {
            throw new InvalidFieldException(ErrorAttribute.GIFT_CERTIFICATE_ERROR_CODE,
                    ErrorAttribute.INVALID_GIFT_CERTIFICATE_ID_ERROR, id);
        }
        return true;
    }

    @Override
    public GiftCertificate findById(String id) {
        try {
            return dao.findById(Long.parseLong(id)).orElseThrow(() -> new ResourceNotFoundException(
                    ErrorAttribute.GIFT_CERTIFICATE_ERROR_CODE, ErrorAttribute.RESOURCE_NOT_FOUND_ERROR, id));
        } catch (NumberFormatException e) {
            throw new InvalidFieldException(ErrorAttribute.GIFT_CERTIFICATE_ERROR_CODE,
                    ErrorAttribute.INVALID_GIFT_CERTIFICATE_ID_ERROR, id);
        }
    }

    @Override
    public List<GiftCertificate> findAll(int page, int elements) {
        List<GiftCertificate> giftCertificates = dao.findAll(page, elements);
        if (CollectionUtils.isEmpty(giftCertificates)) {
            throw new ResourceNotFoundException(ErrorAttribute.GIFT_CERTIFICATE_ERROR_CODE,
                    ErrorAttribute.RESOURCE_NOT_FOUND_ERROR, page + Symbol.COMMA + Symbol.SPACE + elements);
        }
        return giftCertificates;
    }

    @Override
    public List<GiftCertificate> findCertificatesWithTagsByCriteria(int page, int elements, String tagName,
                                                                    String certificateName,
                                                                    String certificateDescription, String sortByName,
                                                                    String sortByDate) {
        List<Criteria<GiftCertificate>> certificateCriteriaList = new ArrayList<>(); //fixme
        if (TagValidator.isNameValid(tagName)) {
            List<Tag> tags = new ArrayList<>();
            tags.add(tagService.findByName(tagName));
            certificateCriteriaList.add(new FullMatchSearchCertificateCriteria(tags));
        }
        if (isNameValid(certificateName)) {
            certificateCriteriaList.add(new PartMatchSearchCertificateCriteria(EntityFieldsName.NAME, certificateName));
        }
        if (isDescriptionValid(certificateDescription)) {
            certificateCriteriaList.add(new PartMatchSearchCertificateCriteria(EntityFieldsName.DESCRIPTION, certificateDescription));
        }
        if (sortByName != null && !sortByName.isEmpty()) {
            String sortOrdering = sortByName.equalsIgnoreCase(ASC_SORT_ORDERING) ? ASC_SORT_ORDERING
                    : DESC_SORT_ORDERING;
            certificateCriteriaList.add(new FieldSortCertificateCriteria(EntityFieldsName.NAME, sortOrdering));
        }
        if (sortByDate != null && !sortByDate.isEmpty()) {
            String sortOrdering = sortByDate.equalsIgnoreCase(ASC_SORT_ORDERING) ? ASC_SORT_ORDERING
                    : DESC_SORT_ORDERING;
            certificateCriteriaList.add(new FieldSortCertificateCriteria(EntityFieldsName.CREATE_DATE, sortOrdering));
        }

        List<GiftCertificate> giftCertificates = dao.findWithTags(page, elements, certificateCriteriaList);
        if (CollectionUtils.isEmpty(giftCertificates)) {
            throw new ResourceNotFoundException(ErrorAttribute.GIFT_CERTIFICATE_ERROR_CODE,
                    ErrorAttribute.RESOURCE_NOT_FOUND_ERROR, page + Symbol.COMMA + Symbol.SPACE + elements);
        }
        return giftCertificates;
    }

    private boolean updateCertificateFields(GiftCertificate oldCertificate, GiftCertificate newCertificate) {
        boolean result = false;
        if (isNameValid(newCertificate.getName())) {
            oldCertificate.setName(newCertificate.getName());
            result = true;
        }
        if (isDescriptionValid(newCertificate.getDescription())) {
            oldCertificate.setDescription(newCertificate.getDescription());
            result = true;
        }
        if (isPriceValid(newCertificate.getPrice())) {
            oldCertificate.setPrice(newCertificate.getPrice());
            result = true;
        }
        if (isDurationValid(newCertificate.getDuration())) {
            oldCertificate.setDuration(newCertificate.getDuration());
            result = true;
        }
        if (areGiftCertificateTagsValid(newCertificate.getTags())) {
            List<Tag> tags = new ArrayList<>(oldCertificate.getTags());
            newCertificate.getTags().stream()
                    .filter(t -> !tags.contains(t))
                    .forEach(tags::add);
            oldCertificate.setTags(tags);
            result = true;
        }
        return result;
    }
}
