package com.epam.esm.service.impl.validator;

import com.epam.esm.dao.api.entity.GiftCertificate;
import com.epam.esm.service.impl.exception.ServiceException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.epam.esm.service.impl.exception.ErrorCode.GIFT_CERTIFICATE_ID_INCORRECT_ERROR;
import static com.epam.esm.service.impl.exception.ErrorCode.GIFT_CERTIFICATE_NAME_INCORRECT_ERROR;
import static com.epam.esm.service.impl.exception.ErrorCode.GIFT_CERTIFICATE_DURATION_INCORRECT_ERROR;
import static com.epam.esm.service.impl.exception.ErrorCode.GIFT_CERTIFICATE_DESCRIPTION_INCORRECT_ERROR;
import static com.epam.esm.service.impl.exception.ErrorCode.GIFT_CERTIFICATE_DATES_INCORRECT_ERROR;
import static com.epam.esm.service.impl.exception.ErrorCode.GIFT_CERTIFICATE_PRICE_INCORRECT_ERROR;


@Component
public final class GiftCertificateValidator {

    private static final String NAME_REGEX = "^[a-zA-Z0-9]{5,45}$";
    private static final String REGEX_DESCRIPTION = "^[a-zA-Z0-9]{5,200}$";
    private static final BigDecimal MIN_PRICE = new BigDecimal(1);
    private static final BigDecimal MAX_PRICE = new BigDecimal(999999);
    private static final int MIN_DURATION = 1;
    private static final int MAX_DURATION = 365;

    public void isGiftCertificateCorrect(GiftCertificate giftCertificate) {
        isIdCorrect(giftCertificate.getId());
        isNameCorrect(giftCertificate.getName());
        isDescriptionCorrect(giftCertificate.getDescription());
        isPriceCorrect(giftCertificate.getPrice());
        isDurationCorrect(giftCertificate.getDuration());
        isDatesCorrect(giftCertificate.getCreateDate(), giftCertificate.getLastUpdateDate());
    }

    public void isIdCorrect(Long id) {
        if (id == null || id < 0) {
            throw new ServiceException(GIFT_CERTIFICATE_ID_INCORRECT_ERROR);
        }
    }

    public void isNameCorrect(String name) {
        if (name == null || !name.matches(NAME_REGEX)) {
            throw new ServiceException(GIFT_CERTIFICATE_NAME_INCORRECT_ERROR);
        }
    }

    public void isDescriptionCorrect(String description) {
        if (description == null || !description.matches(REGEX_DESCRIPTION)) {
            throw new ServiceException(GIFT_CERTIFICATE_DESCRIPTION_INCORRECT_ERROR);
        }
    }

    public void isPriceCorrect(BigDecimal price) {
        if (price == null || price.compareTo(MIN_PRICE) < 0 || price.compareTo(MAX_PRICE) > 0) {
            throw new ServiceException(GIFT_CERTIFICATE_PRICE_INCORRECT_ERROR);
        }
    }

    public void isDurationCorrect(int duration) {
        if (duration < MIN_DURATION || duration > MAX_DURATION) {
            throw new ServiceException(GIFT_CERTIFICATE_DURATION_INCORRECT_ERROR);
        }
    }

    public void isDatesCorrect(LocalDateTime createDate, LocalDateTime updateDate) {
        if (createDate.isAfter(LocalDateTime.now()) || createDate.isAfter(updateDate)
                || updateDate.isAfter(LocalDateTime.now())) {
            throw new ServiceException(GIFT_CERTIFICATE_DATES_INCORRECT_ERROR);
        }
    }
}
