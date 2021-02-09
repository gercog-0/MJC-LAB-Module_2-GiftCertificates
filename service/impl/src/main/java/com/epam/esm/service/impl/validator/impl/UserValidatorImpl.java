package com.epam.esm.service.impl.validator.impl;

import com.epam.esm.dao.api.entity.User;
import com.epam.esm.service.api.dto.UserDto;
import com.epam.esm.service.api.exception.ServiceException;
import com.epam.esm.service.impl.validator.BaseValidator;
import org.springframework.stereotype.Component;

import static com.epam.esm.service.api.exception.ErrorCode.USER_NAME_INCORRECT;

@Component
public class UserValidatorImpl implements BaseValidator<UserDto> {

    private static final String NAME_REGEX = "^[a-zA-Z ]{5,45}$";

    @Override
    public void validate(UserDto user) {
        String name = user.getName();
        if (name == null || !name.matches(NAME_REGEX)) {
            throw new ServiceException(USER_NAME_INCORRECT, name);
        }
    }
}
