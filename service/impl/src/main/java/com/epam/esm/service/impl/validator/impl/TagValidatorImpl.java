package com.epam.esm.service.impl.validator.impl;

import com.epam.esm.service.impl.exception.ServiceException;
import com.epam.esm.service.impl.validator.TagValidator;
import org.springframework.stereotype.Component;

import static com.epam.esm.service.impl.exception.ErrorCode.TAG_ID_INCORRECT;
import static com.epam.esm.service.impl.exception.ErrorCode.TAG_NAME_INCORRECT;

@Component
public final class TagValidatorImpl implements TagValidator {

    private static final String NAME_REGEX = "^[a-zA-Z0-9]{5,45}$";

    public void validateName(String name) {
        if (name == null || !name.matches(NAME_REGEX)) {
            throw new ServiceException(TAG_NAME_INCORRECT);
        }
    }

    public void validateId(Long id) {
        if (id == null) {
            throw new ServiceException(TAG_ID_INCORRECT);
        }
    }

}
