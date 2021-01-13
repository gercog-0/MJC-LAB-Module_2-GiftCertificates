package com.epam.esm.service.impl.validator;

import com.epam.esm.service.api.dto.TagDto;
import com.epam.esm.service.api.exception.ServiceException;
import com.epam.esm.service.impl.validator.impl.TagValidatorImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


class TagValidatorTest {

    private static BaseValidator<TagDto> tagValidator;

    @BeforeAll
    static void setUp() {
        tagValidator = new TagValidatorImpl();
    }

    @AfterAll
    static void tierDown() {
        tagValidator = null;
    }

    public static Object[] createCorrectTags() {
        return new Object[]{
                new TagDto(5L, "name correct"),
                new TagDto(6L, "name with space"),
                new TagDto(7L, "NAMETEST"),
                new TagDto(8L, "naME testing"),
        };
    }

    public static Object[] createIncorrectTags() {
        return new Object[]{
                new TagDto(5L, "name1"),
                new TagDto(6L, "12345"),
                new TagDto(7L, null),
                new TagDto(8L, "na 1me"),
                new TagDto(9L, "na"),
        };
    }

    @ParameterizedTest
    @MethodSource("createCorrectTags")
    void validateCorrectDataShouldNotThrowException(TagDto tagDto) {
        assertDoesNotThrow(() -> tagValidator.validate(tagDto));
    }

    @ParameterizedTest
    @MethodSource("createIncorrectTags")
    void validateIncorrectDataShouldThrowException(TagDto tagDto) {
        assertThrows(ServiceException.class, () -> tagValidator.validate(tagDto));
    }
}