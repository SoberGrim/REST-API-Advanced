package com.epam.esm.service.impl;

import com.epam.esm.dao.UserDao;
import com.epam.esm.dao.constant.ErrorAttribute;
import com.epam.esm.dao.constant.Symbol;
import com.epam.esm.dto.User;
import com.epam.esm.exception.InvalidFieldException;
import com.epam.esm.exception.ResourceNotFoundException;
import com.epam.esm.service.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService<User> {
    private final UserDao<User> dao;

    @Autowired
    public UserServiceImpl(UserDao<User> dao) {
        this.dao = dao;
    }

    @Override
    public User findById(String id) {
        try {
            return dao.findById(Long.parseLong(id)).orElseThrow(() -> new ResourceNotFoundException(
                    ErrorAttribute.USER_ERROR_CODE, ErrorAttribute.RESOURCE_NOT_FOUND_ERROR, id));
        } catch (NumberFormatException e) {
            throw new InvalidFieldException(ErrorAttribute.USER_ERROR_CODE, ErrorAttribute.INVALID_USER_ID_ERROR, id);
        }
    }

    @Override
    public List<User> findAll(int page, int elements) {
        List<User> users = dao.findAll(page, elements);
        if (CollectionUtils.isEmpty(users)) {
            throw new ResourceNotFoundException(ErrorAttribute.USER_ERROR_CODE, ErrorAttribute.RESOURCE_NOT_FOUND_ERROR,
                    page + Symbol.COMMA + Symbol.SPACE + elements);
        }
        return users;
    }

    @Override
    public List<User> findWithGiftCertificates(int page, int elements) {
        List<User> users = dao.findWithGiftCertificates(page, elements);
        if (CollectionUtils.isEmpty(users)) {
            throw new ResourceNotFoundException(ErrorAttribute.USER_ERROR_CODE, ErrorAttribute.RESOURCE_NOT_FOUND_ERROR,
                    page + Symbol.COMMA + Symbol.SPACE + elements);
        }
        return users;
    }
}
