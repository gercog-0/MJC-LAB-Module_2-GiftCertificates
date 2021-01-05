package com.epam.esm.validator;

import com.epam.esm.daoapi.entity.GiftCertificate;
import com.epam.esm.exception.ServiceErrorInformation;
import com.epam.esm.exception.ServiceException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class GiftCertificateValidator {

    private static final String ID_REGEX = "^\\d{1,19}$";
    private static final String NAME_REGEX = "^[a-zA-Z0-9]{5,45}$";
    private static final String REGEX_DESCRIPTION = "^[a-zA-Z0-9]{5,200}$";
    private static final BigDecimal MIN_PRICE = new BigDecimal(1);
    private static final BigDecimal MAX_PRICE = new BigDecimal(999999);
    private static final int MIN_DURATION = 1;
    private static final int MAX_DURATION = 365;

    private GiftCertificateValidator() {
    }

    public static boolean isGiftCertificateCorrect(GiftCertificate giftCertificate){
        ServiceErrorInformation serviceErrorInformation = null;
        if (giftCertificate.getId() < 0) {
            serviceErrorInformation = ServiceErrorInformation.GIFT_CERTIFICATE_ID_INCORRECT;
        } else if (!GiftCertificateValidator.isNameCorrect(giftCertificate.getName())) {
            serviceErrorInformation = ServiceErrorInformation.GIFT_CERTIFICATE_NAME_INCORRECT;
        } else if (!GiftCertificateValidator.isDescriptionCorrect(giftCertificate.getDescription())) {
            serviceErrorInformation = ServiceErrorInformation.GIFT_CERTIFICATE_DESCRIPTION_INCORRECT;
        } else if (!GiftCertificateValidator.isPriceCorrect(giftCertificate.getPrice())) {
            serviceErrorInformation = ServiceErrorInformation.GIFT_CERTIFICATE_PRICE_INCORRECT;
        } else if (!GiftCertificateValidator.isDurationCorrect(giftCertificate.getDuration())) {
            serviceErrorInformation = ServiceErrorInformation.GIFT_CERTIFICATE_DURATION_INCORRECT;
        } else if (!GiftCertificateValidator.isDatesCorrect(giftCertificate.getCreateDate(),
                giftCertificate.getLastUpdateDate())) {
            serviceErrorInformation = ServiceErrorInformation.GIFT_CERTIFICATE_DATES_INCORRECT;
        }
        if (serviceErrorInformation != null) {
            throw new ServiceException(serviceErrorInformation);
        }
        return true;
    }

    public static boolean isIdCorrect(String id) {
        return id != null && id.matches(ID_REGEX);
    }

    public static boolean isNameCorrect(String name) {
        return name != null && name.matches(NAME_REGEX);
    }

    public static boolean isDescriptionCorrect(String description) {
        return description != null && description.matches(REGEX_DESCRIPTION);
    }

    public static boolean isPriceCorrect(BigDecimal price) {
        return price != null && price.compareTo(MIN_PRICE) > 0 && price.compareTo(MAX_PRICE) < 0;
    }

    public static boolean isDurationCorrect(int duration) {
        return duration > MIN_DURATION && duration < MAX_DURATION;
    }

    public static boolean isDatesCorrect(LocalDateTime createDate, LocalDateTime updateDate) {
        return createDate.isBefore(LocalDateTime.now()) && createDate.isBefore(updateDate)
                && updateDate.isBefore(LocalDateTime.now());
    }
}
