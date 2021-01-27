package com.epam.esm.dao.api;

import com.epam.esm.dao.api.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao extends BaseDao<Order> {

     List<Order> findAllByUserId(Long userId);

     Optional<Order> findByUserId(Long orderId, Long userId);


}
