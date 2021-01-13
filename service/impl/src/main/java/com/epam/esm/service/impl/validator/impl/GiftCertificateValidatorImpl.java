package com.epam.esm.service.impl.validator.impl;

import com.epam.esm.service.api.dto.GiftCertificateDto;
import com.epam.esm.service.api.exception.ServiceException;
import com.epam.esm.service.impl.validator.BaseValidator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.epam.esm.service.api.exception.ErrorCode.GIFT_CERTIFICATE_NAME_INCORRECT;
import static com.epam.esm.service.api.exception.ErrorCode.GIFT_CERTIFICATE_DURATION_INCORRECT;
import static com.epam.esm.service.api.exception.ErrorCode.GIFT_CERTIFICATE_DESCRIPTION_INCORRECT;
import static com.epam.esm.service.api.exception.ErrorCode.GIFT_CERTIFICATE_PRICE_INCORRECT;

@Component
public class GiftCertificateValidatorImpl implements BaseValidator<GiftCertificateDto> {

    private static final String NAME_REGEX = "^[a-zA-Z ]{5,45}$";
    private static final String REGEX_DESCRIPTION = "^[a-zA-Z0-9 ]{5,200}$";
    private static final BigDecimal MIN_PRICE = new BigDecimal(1);
    private static final BigDecimal MAX_PRICE = new BigDecimal(999999);
    private static final int MIN_DURATION = 1;
    private static final int MAX_DURATION = 365;

    @Override
    public void validate(GiftCertificateDto giftCertificateDto) {
        validateName(giftCertificateDto.getName());
        validateDescription(giftCertificateDto.getDescription());
        validatePrice(giftCertificateDto.getPrice());
        validateDuration(giftCertificateDto.getDuration());
    }

    private void validateName(String name) {
        if (name == null || !name.matches(NAME_REGEX)) {
            throw new ServiceException(GIFT_CERTIFICATE_NAME_INCORRECT, name);
        }
    }

    private void validateDescription(String description) {
        if (description == null || !description.matches(REGEX_DESCRIPTION)) {
            throw new ServiceException(GIFT_CERTIFICATE_DESCRIPTION_INCORRECT, description);
        }
    }

    private void validatePrice(BigDecimal price) {
        if (price == null || price.compareTo(MIN_PRICE) < 0 || price.compareTo(MAX_PRICE) > 0) {
            throw new ServiceException(GIFT_CERTIFICATE_PRICE_INCORRECT, String.valueOf(price));
        }
    }

    private void validateDuration(int duration) {
        if (duration < MIN_DURATION || duration > MAX_DURATION) {
            throw new ServiceException(GIFT_CERTIFICATE_DURATION_INCORRECT, String.valueOf(duration));
        }
    }
}
