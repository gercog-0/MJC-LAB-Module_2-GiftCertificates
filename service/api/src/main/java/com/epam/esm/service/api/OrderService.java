package com.epam.esm.service.api;

import com.epam.esm.service.api.dto.OrderDto;

import java.util.List;

public interface OrderService extends BaseService<OrderDto> {

    List<OrderDto> findAllByUserId(Long userId);
}
