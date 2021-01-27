package com.epam.esm.dao.api;

import com.epam.esm.dao.api.entity.Order;

import java.util.List;

public interface OrderDao extends BaseDao<Order> {

    List<Order> findAllByUserId(Long userId);
}
