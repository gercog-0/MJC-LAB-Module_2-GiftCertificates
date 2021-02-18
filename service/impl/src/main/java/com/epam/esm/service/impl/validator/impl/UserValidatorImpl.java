package com.epam.esm.service.impl.validator.impl;

import com.epam.esm.service.api.dto.FullUserDto;
import com.epam.esm.service.api.dto.UserDto;
import com.epam.esm.service.api.exception.ServiceException;
import com.epam.esm.service.impl.validator.BaseValidator;
import org.springframework.stereotype.Component;

import static com.epam.esm.service.api.exception.ErrorCode.USER_LOGIN_INCORRECT;
import static com.epam.esm.service.api.exception.ErrorCode.USER_NAME_INCORRECT;
import static com.epam.esm.service.api.exception.ErrorCode.USER_PASSWORD_INCORRECT;

@Component
public class UserValidatorImpl implements BaseValidator<FullUserDto> {

    private static final String NAME_REGEX = "^[a-zA-Z ]{2,45}$";
    private static final String LOGIN_REGEX = "^[a-zA-Z0-9_]{3,25}$";
    private static final String PASSWORD_REGEX = "^.{3,25}$";

    @Override
    public void validate(FullUserDto user) {
        validateName(user.getName());
        validateLogin(user.getLogin());
        validatePassword(user.getPassword());
    }

    private void validateName(String name) {
        if (name == null || !name.matches(NAME_REGEX)) {
            throw new ServiceException(USER_NAME_INCORRECT, name);
        }
    }

    private void validateLogin(String login) {
        if (login == null || !login.matches(LOGIN_REGEX)) {
            throw new ServiceException(USER_LOGIN_INCORRECT, login);
        }
    }

    private void validatePassword(String password) {
        if (password == null || !password.matches(PASSWORD_REGEX)) {
            throw new ServiceException(USER_PASSWORD_INCORRECT);
        }
    }
}
