package com.epam.esm.service.impl;

import com.epam.esm.dao.api.OrderDao;
import com.epam.esm.dao.api.entity.Order;
import com.epam.esm.dao.api.entity.Pagination;
import com.epam.esm.service.api.GiftCertificateService;
import com.epam.esm.service.api.OrderService;
import com.epam.esm.service.api.UserService;
import com.epam.esm.service.api.dto.OrderDto;
import com.epam.esm.service.api.dto.PaginationDto;
import com.epam.esm.service.api.exception.ServiceException;
import com.epam.esm.service.impl.validator.impl.OrderValidatorImpl;
import com.epam.esm.service.impl.validator.impl.PaginationDtoValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderServiceImplTest {

    private OrderDao orderDao;
    private OrderValidatorImpl validator;
    private ModelMapper modelMapper;
    private OrderService orderService;

    @BeforeEach
    void setUp(){
        orderDao = mock(OrderDao.class);
        validator = mock(OrderValidatorImpl.class);
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        GiftCertificateService giftCertificateService = mock(GiftCertificateService.class);
        UserService userService = mock(UserService.class);
        orderService = new OrderServiceImpl(orderDao, giftCertificateService, userService,validator,
                new PaginationDtoValidator(), modelMapper);
    }

    @Test
    void methodFindAllByUserId(){
        when(orderDao.findAllByUserId(any(Long.class), any(Pagination.class))).thenReturn(Arrays.asList(new Order(), new Order()));
        int expectedSize = 2;

        int actualSize = orderService.findAllByUserId(1L, new PaginationDto(1,5)).size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void methodFindByIdShouldReturnOrderCorrect(){
        OrderDto expectedDto = new OrderDto();
        expectedDto.setId(1L);
        expectedDto.setCost(new BigDecimal("100"));
        expectedDto.setPurchaseDate(LocalDateTime.of(2020, 2, 1, 17, 0, 0));
        expectedDto.setGiftCertificateId(1L);
        expectedDto.setUserId(1L);
        Order returnOrder = new Order();
        returnOrder.setId(1L);
        returnOrder.setCost(new BigDecimal("100"));
        returnOrder.setPurchaseDate(LocalDateTime.of(2020, 2, 1, 17, 0, 0));
        returnOrder.setGiftCertificateId(1L);
        returnOrder.setUserId(1L);
        when(orderDao.findById(any(Long.class))).thenReturn(Optional.of(returnOrder));

        OrderDto actualOrder = orderService.findById(1L);

        assertEquals(expectedDto, actualOrder);
    }

    @Test
    void methodFindByIdShouldThrowException(){
        when(orderDao.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(ServiceException.class, () -> orderService.findById(1L));
    }

    @AfterEach
    void tearDown(){
        orderDao = null;
        validator = null;
        modelMapper = null;
        orderService = null;
    }
}
