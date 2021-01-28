package com.epam.esm.service.impl;

import com.epam.esm.dao.api.OrderDao;
import com.epam.esm.dao.api.entity.Order;
import com.epam.esm.dao.api.entity.Pagination;
import com.epam.esm.service.api.GiftCertificateService;
import com.epam.esm.service.api.OrderService;
import com.epam.esm.service.api.UserService;
import com.epam.esm.service.api.dto.GiftCertificateDto;
import com.epam.esm.service.api.dto.OrderDto;
import com.epam.esm.service.api.dto.PaginationDto;
import com.epam.esm.service.api.exception.ServiceException;
import com.epam.esm.service.impl.validator.BaseValidator;
import com.epam.esm.service.impl.validator.impl.OrderValidatorImpl;
import com.epam.esm.service.impl.validator.impl.PaginationDtoValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.epam.esm.service.api.exception.ErrorCode.ORDER_ID_SPECIFIED_WHILE_CREATING;
import static com.epam.esm.service.api.exception.ErrorCode.ORDER_WITH_SUCH_ID_NOT_EXIST;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final GiftCertificateService giftCertificateService;
    private final UserService userService;
    private final BaseValidator<OrderDto> validator;
    private final BaseValidator<PaginationDto> paginationValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, GiftCertificateService giftCertificateService,
                            UserService userService, OrderValidatorImpl validator,
                            PaginationDtoValidator paginationValidator, ModelMapper modelMapper) {
        this.orderDao = orderDao;
        this.giftCertificateService = giftCertificateService;
        this.userService = userService;
        this.validator = validator;
        this.paginationValidator = paginationValidator;
        this.modelMapper = modelMapper;
    }

    public List<OrderDto> findAllByUserId(Long userId, PaginationDto paginationDto) {
        paginationValidator.validate(paginationDto);
        Pagination pagination = modelMapper.map(paginationDto, Pagination.class);
        userService.findById(userId);
        return orderDao.findAllByUserId(userId, pagination).stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto findById(long id) {
        return orderDao.findById(id)
                .map(order -> modelMapper.map(order, OrderDto.class))
                .orElseThrow(() -> new ServiceException(ORDER_WITH_SUCH_ID_NOT_EXIST, String.valueOf(id)));
    }

    @Transactional
    @Override
    public OrderDto add(OrderDto orderDto) {
        Long specifiedId = orderDto.getId();
        if (specifiedId != null) {
            throw new ServiceException(ORDER_ID_SPECIFIED_WHILE_CREATING, String.valueOf(specifiedId));
        }
        validator.validate(orderDto);
        GiftCertificateDto giftCertificateDto = giftCertificateService.findById(orderDto.getGiftCertificateId());
        BigDecimal orderCost = giftCertificateDto.getPrice();
        orderDto.setPurchaseDate(LocalDateTime.now());
        orderDto.setCost(orderCost);
        Order order = modelMapper.map(orderDto, Order.class);
        Order addedOrder = orderDao.add(order);
        return modelMapper.map(addedOrder, OrderDto.class);
    }

    @Transactional
    @Override
    public void remove(long id) {
        findById(id);
        orderDao.remove(id);
    }
}
