package com.epam.esm.service.api;

import com.epam.esm.service.api.dto.OrderDto;
import com.epam.esm.service.api.dto.PaginationDto;

import java.util.List;

/**
 * The interface Order service.
 */
public interface OrderService extends BaseService<OrderDto> {

    /**
     * Find all by user id list.
     *
     * @param userId        the user id
     * @param paginationDto the pagination dto
     * @return the list
     */
    List<OrderDto> findAllByUserId(Long userId, PaginationDto paginationDto);
}
