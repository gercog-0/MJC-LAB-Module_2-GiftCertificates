package com.epam.esm.service.api;

import com.epam.esm.service.api.dto.OrderDto;
import com.epam.esm.service.api.dto.PaginationDto;

import java.util.List;

public interface OrderService extends BaseService<OrderDto> {

    List<OrderDto> findAllByUserId(Long userId, PaginationDto paginationDto);
}
