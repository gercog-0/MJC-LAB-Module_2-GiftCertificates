package com.epam.esm.service.impl.validator.impl;

import com.epam.esm.service.api.dto.TagDto;
import com.epam.esm.service.impl.exception.ServiceException;
import com.epam.esm.service.impl.validator.BaseValidator;
import org.springframework.stereotype.Component;

import static com.epam.esm.service.impl.exception.ErrorCode.TAG_NAME_INCORRECT;

@Component
public final class TagValidatorImpl implements BaseValidator<TagDto> {

    private static final String NAME_REGEX = "^[a-zA-Z0-9]{5,45}$";

    @Override
    public void validate(TagDto tagDto) {
        String name = tagDto.getName();
        if (name == null || !name.matches(NAME_REGEX)) {
            throw new ServiceException(TAG_NAME_INCORRECT, name);
        }
    }
}
