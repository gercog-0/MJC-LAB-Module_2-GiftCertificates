package com.epam.esm.service.impl;

import com.epam.esm.dao.api.GiftCertificateDao;
import com.epam.esm.dao.api.entity.GiftCertificate;
import com.epam.esm.dao.api.entity.GiftCertificateQueryParameters;
import com.epam.esm.dao.api.entity.Pagination;
import com.epam.esm.service.api.GiftCertificateService;
import com.epam.esm.service.api.TagService;
import com.epam.esm.service.api.dto.GiftCertificateDto;
import com.epam.esm.service.api.dto.GiftCertificateQueryParametersDto;
import com.epam.esm.service.api.dto.PaginationDto;
import com.epam.esm.service.api.dto.TagDto;
import com.epam.esm.service.api.exception.ServiceException;
import com.epam.esm.service.impl.validator.impl.GiftCertificateValidatorImpl;
import com.epam.esm.service.impl.validator.impl.PaginationDtoValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

 class GiftCertificateServiceImplTest {

    private GiftCertificateDao giftCertificateDao;
    private GiftCertificateValidatorImpl validator;
    private ModelMapper modelMapper;
    private GiftCertificateService giftCertificateService;
     private TagService tagService;

    @BeforeEach
    void setUp() {
        giftCertificateDao = mock(GiftCertificateDao.class);
        tagService = mock(TagServiceImpl.class);
        validator = mock(GiftCertificateValidatorImpl.class);
        PaginationDtoValidator paginationDtoValidator = mock(PaginationDtoValidator.class);
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        giftCertificateService = new GiftCertificateServiceImpl(giftCertificateDao, tagService, validator, paginationDtoValidator, modelMapper);
    }

    @AfterEach
    void tierDown() {
        giftCertificateDao = null;
        validator = null;
        modelMapper = null;
        giftCertificateService = null;
        tagService = null;
    }

    @Test
    void findAllCorrectDataShouldReturnListWithGiftCertificatesDto() {
        GiftCertificate giftCertificate1 = new GiftCertificate();
        giftCertificate1.setId(1L);
        giftCertificate1.setName("name");
        giftCertificate1.setDescription("desc");
        giftCertificate1.setDuration(5);
        giftCertificate1.setTags(new ArrayList<>());
        GiftCertificate giftCertificate2 = new GiftCertificate();
        giftCertificate2.setId(2L);
        giftCertificate2.setName("name");
        giftCertificate2.setDescription("desc");
        giftCertificate2.setDuration(5);
        giftCertificate2.setTags(new ArrayList<>());
        when(giftCertificateDao.findAll(any(GiftCertificateQueryParameters.class), any(Pagination.class))).
                thenReturn(Arrays.asList(giftCertificate1, giftCertificate2));
        int expectedSize = 2;

        int actualSize = giftCertificateService.findAll(new GiftCertificateQueryParametersDto(),new PaginationDto(1,5) ).size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void findByIdCorrectDataShouldReturnLGiftCertificate() {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setId(1L);
        giftCertificate.setName("name");
        giftCertificate.setDescription("desc");
        giftCertificate.setDuration(5);
        giftCertificate.setTags(new ArrayList<>());
        GiftCertificateDto expectedGiftCertificate = new GiftCertificateDto();
        expectedGiftCertificate.setId(1L);
        expectedGiftCertificate.setName("name");
        expectedGiftCertificate.setDescription("desc");
        expectedGiftCertificate.setDuration(5);
        expectedGiftCertificate.setTags(new ArrayList<>());
        when(giftCertificateDao.findById(any(Long.class))).thenReturn(Optional.of(giftCertificate));

        GiftCertificateDto actualGiftCertificate = giftCertificateService.findById(1L);

        assertEquals(expectedGiftCertificate, actualGiftCertificate);
    }

    @Test
    void findByIdNotExistIdShouldThrowException() {
        when(giftCertificateDao.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(
                ServiceException.class, () -> giftCertificateService.findById(-1L));
    }

    @Test
    void addCorrectDataShouldNotThrowException() {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setName("nameorrect");
        giftCertificate.setPrice(new BigDecimal("500"));
        giftCertificate.setDescription("desccoorect");
        giftCertificate.setDuration(5);
        giftCertificate.setTags(new ArrayList<>());
        GiftCertificateDto giftCertificateDto = new GiftCertificateDto();
        giftCertificateDto.setName("nameorrect");
        giftCertificateDto.setPrice(new BigDecimal("500"));
        giftCertificateDto.setDescription("desccoorect");
        giftCertificateDto.setDuration(5);
        giftCertificateDto.setTags(new ArrayList<>());
        when(giftCertificateDao.add(any(GiftCertificate.class))).thenReturn(giftCertificate);

        assertDoesNotThrow(() -> giftCertificateService.add(giftCertificateDto));
    }

    @Test
    void addIdWhileAddingShouldThrowException() {
        GiftCertificate giftCertificate = new GiftCertificate();
        GiftCertificateDto giftCertificateDto = new GiftCertificateDto();
        giftCertificateDto.setId(1L);
        when(giftCertificateDao.add(any(GiftCertificate.class))).thenReturn(giftCertificate);

        assertThrows(ServiceException.class, () -> giftCertificateService.add(giftCertificateDto));
    }

    @Test
    void removeGiftCertificateExistShouldNotThrowException() {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setId(1L);
        giftCertificate.setName("name");
        giftCertificate.setDescription("desc");
        giftCertificate.setDuration(5);
        giftCertificate.setTags(new ArrayList<>());
        when(giftCertificateDao.findById(any(Long.class))).thenReturn(Optional.of(giftCertificate));

        assertDoesNotThrow(() -> giftCertificateService.remove(1L));
    }

    @Test
    void removeNotExistIdShouldThrowException() {
        when(giftCertificateDao.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(ServiceException.class, () -> giftCertificateService.remove(-1L));
    }

    @Test
    void updatePartCorrectDataShouldReturnGiftCertificateDto() {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setId(1L);
        giftCertificate.setName("namecorrect");
        giftCertificate.setPrice(new BigDecimal("500"));
        giftCertificate.setDescription("descrip");
        giftCertificate.setDuration(5);
        giftCertificate.setTags(new ArrayList<>());
        GiftCertificateDto giftCertificateDto = new GiftCertificateDto();
        giftCertificateDto.setName("namecorrect");
        giftCertificateDto.setPrice(new BigDecimal("500"));
        giftCertificateDto.setDescription("descrip");
        giftCertificateDto.setDuration(5);
        giftCertificateDto.setTags(new ArrayList<>());
        when(giftCertificateDao.findById(any(Long.class))).thenReturn(Optional.of(giftCertificate));
        when(tagService.findByName(any(String.class))).thenReturn(new TagDto());
        when(tagService.add(any(TagDto.class))).thenReturn(new TagDto());
        when(giftCertificateDao.update(any(GiftCertificate.class))).thenReturn(any(GiftCertificate.class));

        GiftCertificateDto actualGiftCertificate = giftCertificateService.update(giftCertificateDto);

        assertEquals(giftCertificateDto, actualGiftCertificate);
    }
}
