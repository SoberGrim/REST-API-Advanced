package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.constant.ErrorAttribute;
import com.epam.esm.dto.Tag;
import com.epam.esm.dto.User;
import com.epam.esm.exception.InvalidFieldException;
import com.epam.esm.exception.ResourceDuplicateException;
import com.epam.esm.exception.ResourceNotFoundException;
import com.epam.esm.service.TagService;
import com.epam.esm.service.UserService;
import com.epam.esm.validator.TagValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.epam.esm.validator.TagValidator.isNameValid;

/**
 * The type Tag service.
 */
@Service
public class TagServiceImpl implements TagService<Tag> {
    private final TagDao<Tag> dao;
    private final UserService<User> userService;

    /**
     * Instantiates a new Tag service.
     *
     * @param dao         the dao
     * @param userService the user service
     */
    @Autowired
    public TagServiceImpl(TagDao<Tag> dao, UserService<User> userService) {
        this.dao = dao;
        this.userService = userService;
    }

    @Override
    public long insert(Tag tag) {
        if (!isNameValid(tag.getName())) {
            throw new InvalidFieldException(ErrorAttribute.TAG_ERROR_CODE, ErrorAttribute.INVALID_TAG_ID_ERROR,
                    tag.getName());
        }
        if (dao.findByName(tag.getName()).isPresent()) {
            throw new ResourceDuplicateException(ErrorAttribute.TAG_ERROR_CODE, ErrorAttribute.TAG_DUPLICATE_ERROR,
                    tag.getName());
        }
        return dao.insert(tag);
    }

    @Override
    public Tag findById(String id) {
        try {
            return dao.findById(Long.parseLong(id)).orElseThrow(() -> new ResourceNotFoundException(
                    ErrorAttribute.TAG_ERROR_CODE, ErrorAttribute.RESOURCE_NOT_FOUND_ERROR, id));
        } catch (NumberFormatException e) {
            throw new InvalidFieldException(ErrorAttribute.TAG_ERROR_CODE, ErrorAttribute.INVALID_TAG_ID_ERROR, id);
        }
    }

    @Override
    public Tag findByName(String name) {
        if (TagValidator.isNameValid(name)) {
            return dao.findByName(name).orElseThrow(() -> new ResourceNotFoundException(ErrorAttribute.TAG_ERROR_CODE,
                    ErrorAttribute.RESOURCE_NOT_FOUND_ERROR, name));
        } else {
            throw new InvalidFieldException(ErrorAttribute.TAG_ERROR_CODE, ErrorAttribute.INVALID_TAG_NAME, name);
        }
    }

    @Override
    public Tag findMostUsedTagOfUserWithHighestCostOfAllOrders(String userId) {
        return dao.findMostUsedTagOfUserWithHighestCostOfAllOrders(userService.findById(userId).getId()).orElseThrow(
                () -> new ResourceNotFoundException(ErrorAttribute.TAG_ERROR_CODE,
                        ErrorAttribute.RESOURCE_NOT_FOUND_ERROR, userId));
    }

    @Override
    public List<Tag> findAll(int page, int elements) {
        return dao.findAll(page, elements);
    }

    @Override
    public boolean delete(String id) {
        try {
            if (!dao.delete(Long.parseLong(id))) {
                throw new ResourceNotFoundException(ErrorAttribute.TAG_ERROR_CODE,
                        ErrorAttribute.RESOURCE_NOT_FOUND_ERROR, id);
            }
            return true;
        } catch (NumberFormatException e) {
            throw new InvalidFieldException(ErrorAttribute.TAG_ERROR_CODE, ErrorAttribute.INVALID_TAG_ID_ERROR, id);
        }
    }
}
