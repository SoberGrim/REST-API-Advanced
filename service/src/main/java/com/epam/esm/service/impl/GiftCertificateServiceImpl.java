package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.constant.EntityFieldsName;
import com.epam.esm.dao.constant.ErrorAttribute;
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
import org.apache.commons.collections4.SetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.epam.esm.validator.GiftCertificateValidator.areGiftCertificateTagsValid;
import static com.epam.esm.validator.GiftCertificateValidator.isDescriptionValid;
import static com.epam.esm.validator.GiftCertificateValidator.isDurationValid;
import static com.epam.esm.validator.GiftCertificateValidator.isGiftCertificateCreationFormValid;
import static com.epam.esm.validator.GiftCertificateValidator.isNameValid;
import static com.epam.esm.validator.GiftCertificateValidator.isPriceValid;

/**
 * The type Gift certificate service.
 */
@Service
public class GiftCertificateServiceImpl implements GiftCertificateService<GiftCertificate> {
    private static final String ASC_SORT_ORDERING = "ASC";
    private static final String DESC_SORT_ORDERING = "DESC";
    private final GiftCertificateDao<GiftCertificate> dao;
    private final TagService<Tag> tagService;

    /**
     * Instantiates a new Gift certificate service.
     *
     * @param dao        the dao
     * @param tagService the tag service
     */
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
                Set<Tag> existingTags = SetUtils.intersection(new HashSet<>(tagService.findAll(0, 0)),
                        giftCertificate.getTags());
                Set<Tag> newTags = new HashSet<>(CollectionUtils.removeAll(giftCertificate.getTags(), existingTags));
                giftCertificate.setTags(newTags);
                id = dao.insert(giftCertificate);
                GiftCertificate certificateWithAllTags = dao.findById(id).get();
                certificateWithAllTags.setTags(SetUtils.union(newTags, existingTags));
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
            GiftCertificate giftCertificate = dao.findById(Long.parseLong(id)).orElseThrow(() ->
                    new ResourceNotFoundException(ErrorAttribute.GIFT_CERTIFICATE_ERROR_CODE,
                            ErrorAttribute.RESOURCE_NOT_FOUND_ERROR, id));
            if (!CollectionUtils.isEmpty(giftCertificate.getTags())) {
                dao.disconnectAllTags(giftCertificate);
            }
            return dao.delete(giftCertificate.getId());
        } catch (NumberFormatException e) {
            throw new InvalidFieldException(ErrorAttribute.GIFT_CERTIFICATE_ERROR_CODE,
                    ErrorAttribute.INVALID_GIFT_CERTIFICATE_ID_ERROR, id);
        }
    }

    @Override
    public boolean update(String id, GiftCertificate newCertificate) {
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
    public void disconnectTagById(String tagId) {
        Tag tag = tagService.findById(tagId);
        List<GiftCertificate> certificatesWithSuchTag = findCertificatesWithTagsByCriteria(0, 0,
                Collections.singletonList(tag.getName()), null, null, null, null);
        if (!CollectionUtils.isEmpty(certificatesWithSuchTag)) {
            for (GiftCertificate certificate : certificatesWithSuchTag) {
                Set<Tag> updatedTags = certificate.getTags();
                updatedTags.remove(tag);
                certificate.setTags(updatedTags);
                dao.update(certificate);
            }
        }
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
        return dao.findAll(page, elements);
    }

    @Override
    public List<GiftCertificate> findCertificatesWithTagsByCriteria(int page, int elements, List<String> tagsNames,
                                                                    String certificateName,
                                                                    String certificateDescription, String sortByName,
                                                                    String sortByDate) {
        List<Criteria<GiftCertificate>> certificateCriteriaList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(tagsNames) && tagsNames.stream().allMatch(TagValidator::isNameValid)) {
            List<Tag> tags = new ArrayList<>();
            tagsNames.forEach(t -> tags.add(tagService.findByName(t)));
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
        return dao.findWithTags(page, elements, certificateCriteriaList);
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
            oldCertificate.setTags(SetUtils.union(oldCertificate.getTags(), newCertificate.getTags()));
            result = true;
        }
        return result;
    }
}
