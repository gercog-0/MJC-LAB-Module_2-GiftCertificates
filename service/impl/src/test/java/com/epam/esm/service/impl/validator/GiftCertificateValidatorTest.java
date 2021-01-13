package com.epam.esm.service.impl.validator;

import com.epam.esm.service.api.dto.GiftCertificateDto;
import com.epam.esm.service.api.exception.ServiceException;
import com.epam.esm.service.impl.validator.impl.GiftCertificateValidatorImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GiftCertificateValidatorTest {
    private static BaseValidator<GiftCertificateDto> giftCertificateValidator;

    @BeforeAll
    static void setUp() {
        giftCertificateValidator = new GiftCertificateValidatorImpl();
    }

    @AfterAll
    static void tierDown() {
        giftCertificateValidator = null;
    }

    public static Object[] createCorrectTags() {
        return new Object[]{
                new GiftCertificateDto(5L, "name correct", "5 symbols",
                        new BigDecimal("500"), 5, null, null, null),
                new GiftCertificateDto(5L, "testNAME", "description is correct",
                        new BigDecimal("1"), 365, null, null, null),
                new GiftCertificateDto(5L, "name correct", "DESCRIPTION",
                        new BigDecimal("900"), 5, null, null, null),
                new GiftCertificateDto(5L, "name correct", "5 symbols",
                        new BigDecimal("999999"), 5, null, null, null),

        };
    }

    public static Object[] createIncorrectTags() {
        return new Object[]{
                new GiftCertificateDto(5L, "name", "5",
                        new BigDecimal("0"), 5, null, null, null),
                new GiftCertificateDto(5L, "testNAME", "description is correct",
                        new BigDecimal("1"), -1, null, null, null),
                new GiftCertificateDto(5L, "name correct", "DESCRIPTION",
                        new BigDecimal("100000000"), 5, null, null, null),
                new GiftCertificateDto(5L, "name correct", "5 symbols",
                        new BigDecimal("999999"), 400, null, null, null),
        };
    }

    @ParameterizedTest
    @MethodSource("createCorrectTags")
    void validateCorrectDataShouldNotThrowException(GiftCertificateDto giftCertificateDto) {
        assertDoesNotThrow(() -> giftCertificateValidator.validate(giftCertificateDto));
    }

    @ParameterizedTest
    @MethodSource("createIncorrectTags")
    void validateIncorrectDataShouldThrowException(GiftCertificateDto giftCertificateDto) {
        assertThrows(ServiceException.class, () -> giftCertificateValidator.validate(giftCertificateDto));
    }
}