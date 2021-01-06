package com.epam.esm.service.impl.validator;

import com.epam.esm.service.impl.exception.ServiceException;
import org.springframework.stereotype.Component;

import static com.epam.esm.service.impl.exception.ErrorCode.TAG_ID_INCORRECT_ERROR;
import static com.epam.esm.service.impl.exception.ErrorCode.TAG_NAME_INCORRECT_ERROR;

@Component
public final class TagValidator {

    private static final String NAME_REGEX = "^[a-zA-Z0-9]{5,45}$";

    public void validateName(String name) {
        if (name == null || !name.matches(NAME_REGEX)) {
            throw new ServiceException(TAG_NAME_INCORRECT_ERROR);
        }
    }

    public void validateId(Long id) {
        if (id == null || id < 0) {
            throw new ServiceException(TAG_ID_INCORRECT_ERROR);
        }
    }

}
