package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.Tag;
import com.epam.esm.exception.InvalidFieldException;
import com.epam.esm.exception.ResourceDuplicateException;
import com.epam.esm.exception.ResourceNotFoundException;
import com.epam.esm.service.TagService;
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

    /**
     * Instantiates a new Tag service.
     *
     * @param dao the dao
     */
    @Autowired
    public TagServiceImpl(TagDao<Tag> dao) {
        this.dao = dao;
    }

    @Override
    public boolean insert(Tag tag) {
        if (!isNameValid(tag.getName())) {
            throw new InvalidFieldException("2", "Invalid tag name (name = " + tag.getName() + ")");
        }
        if (dao.findByName(tag.getName()).isPresent()) {
            throw new ResourceDuplicateException("2", "Tag already exists (name = " + tag.getName() + ")");
        }
        return dao.insert(tag);
    }

    @Override
    public Tag findById(String id) {
        try {
            return dao.findById(Long.parseLong(id)).orElseThrow(() -> new ResourceNotFoundException("2", "Requested" +
                    " resource not found (id = " + id + ")"));
        } catch (NumberFormatException e) {
            throw new InvalidFieldException("2", "Invalid tag id (id = " + id + ")");
        }
    }

    @Override
    public Tag findByName(String name) {
        if (isNameValid(name)) {
            return dao.findByName(name).orElseThrow(() -> new ResourceNotFoundException("2", "Requested" +
                    " resource not found (name = " + name + ")"));
        } else {
            throw new InvalidFieldException("2", "Invalid tag name (name = " + name + ")");
        }
    }

    @Override
    public List<Tag> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Tag> findTagsConnectedToCertificate(String certificateId) {
        try {
            return dao.findTagsConnectedToCertificate(Long.parseLong(certificateId));
        } catch (NumberFormatException e) {
            throw new InvalidFieldException("2", "Invalid certificate id (id = " + certificateId + ")");
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            return dao.delete(Long.parseLong(id));
        } catch (NumberFormatException e) {
            throw new InvalidFieldException("2", "Invalid tag id (id = " + id + ")");
        }
    }
}
