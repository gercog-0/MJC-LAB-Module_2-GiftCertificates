package com.epam.esm.dao.api;

import com.epam.esm.dao.api.entity.Order;
import com.epam.esm.dao.api.entity.Pagination;

import java.util.List;

/**
 * The interface Order dao.
 */
public interface OrderDao extends BaseDao<Order> {

    /**
     * Find all by user id list.
     *
     * @param userId     the user id
     * @param pagination the pagination
     * @return the list
     */
    List<Order> findAllByUserId(Long userId, Pagination pagination);
}
