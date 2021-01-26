package com.epam.esm.service.impl;

import com.epam.esm.dao.api.OrderDao;
import com.epam.esm.dao.api.entity.Order;
import com.epam.esm.service.api.OrderService;
import com.epam.esm.service.api.dto.OrderDto;
import com.epam.esm.service.api.exception.ServiceException;
import com.epam.esm.service.impl.validator.BaseValidator;
import com.epam.esm.service.impl.validator.impl.OrderValidatorImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.epam.esm.service.api.exception.ErrorCode.ORDER_ID_SPECIFIED_WHILE_CREATING;
import static com.epam.esm.service.api.exception.ErrorCode.ORDER_WITH_SUCH_ID_NOT_EXIST;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final BaseValidator<OrderDto> validator;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, OrderValidatorImpl validator, ModelMapper modelMapper) {
        this.orderDao = orderDao;
        this.validator = validator;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OrderDto> findAllByUserId(Long userId) {
        return orderDao.findAllByUserId(userId).stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto findById(long id) {
        return orderDao.findById(id)
                .map(order -> modelMapper.map(order, OrderDto.class))
                .orElseThrow(() -> new ServiceException(ORDER_WITH_SUCH_ID_NOT_EXIST, String.valueOf(id)));
    }

    @Override
    public OrderDto add(OrderDto orderDto) {
        Long specifiedId = orderDto.getId();
        if (specifiedId != null) {
            throw new ServiceException(ORDER_ID_SPECIFIED_WHILE_CREATING, String.valueOf(specifiedId));
        }
        // TODO: 26.01.2021 validate
        orderDto.setPurchaseDate(LocalDateTime.now());
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
