package com.epam.esm.service.impl.validator;

import com.epam.esm.dao.api.entity.GiftCertificate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface GiftCertificateValidator extends BaseValidator {

    void isCorrect(GiftCertificate giftCertificate);

    void validateDescription(String description);

    void validatePrice(BigDecimal price);

    void validateDuration(int duration);

    void validatesDates(LocalDateTime createDate, LocalDateTime updateDate);
}
