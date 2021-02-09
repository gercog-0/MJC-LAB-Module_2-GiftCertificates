package com.epam.esm.service.impl.validator.impl;

import com.epam.esm.service.api.dto.PaginationDto;
import com.epam.esm.service.api.exception.ServiceException;
import com.epam.esm.service.impl.validator.BaseValidator;
import org.springframework.stereotype.Component;

import static com.epam.esm.service.api.exception.ErrorCode.PAGINATION_INCORRECT_PAGE_NUMBER;
import static com.epam.esm.service.api.exception.ErrorCode.PAGINATION_INCORRECT_SIZE;

@Component
public class PaginationDtoValidator implements BaseValidator<PaginationDto> {

    private static final int MIN_PAGE_NUMBER = 1;
    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 100;
    private static final int DEFAULT_VALUE_PAGE = 1;
    private static final int DEFAULT_VALUE_SIZE = 5;

    @Override
    public void validate(PaginationDto paginationDto) {
        defineEmptyFields(paginationDto);
        int pageNumber = paginationDto.getPageNumber();
        int size = paginationDto.getSize();
        if (pageNumber < MIN_PAGE_NUMBER) {
            throw new ServiceException(PAGINATION_INCORRECT_PAGE_NUMBER, String.valueOf(pageNumber));
        }
        if (size < MIN_SIZE || size > MAX_SIZE) {
            throw new ServiceException(PAGINATION_INCORRECT_SIZE, String.valueOf(size));
        }
    }

    private void defineEmptyFields(PaginationDto paginationDto) {
        if (paginationDto.getPageNumber() == null) {
            paginationDto.setPageNumber(DEFAULT_VALUE_PAGE);
        }
        if (paginationDto.getSize() == null) {
            paginationDto.setSize(DEFAULT_VALUE_SIZE);
        }
    }
}
