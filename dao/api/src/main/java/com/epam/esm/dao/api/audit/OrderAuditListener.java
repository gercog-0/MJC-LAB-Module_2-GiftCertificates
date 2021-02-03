package com.epam.esm.dao.api.audit;

import com.epam.esm.dao.api.entity.Order;

import javax.persistence.PrePersist;
import java.time.LocalDateTime;

public class OrderAuditListener {

    @PrePersist
    public void beforeCreateOrder(Order order){
        order.setPurchaseDate(LocalDateTime.now());
    }
}
