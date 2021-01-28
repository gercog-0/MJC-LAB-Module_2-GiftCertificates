package com.epam.esm.dao.impl;

import com.epam.esm.dao.api.OrderDao;
import com.epam.esm.dao.api.entity.Order;
import com.epam.esm.dao.api.entity.Pagination;
import com.epam.esm.dao.impl.util.PaginationUtil;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static com.epam.esm.dao.impl.util.SqlQuery.FIND_ALL_ORDERS_BY_USER_ID;


@Repository
public class OrderDaoImpl implements OrderDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Order.class, id));
    }

    @Override
    public Order add(Order order) {
        entityManager.persist(order);
        return order;
    }

    @Override
    public Order update(Order order) {
        throw new UnsupportedOperationException("Method update for Order is unsupported.");
    }

    @Override
    public void remove(Long id) {
        Order foundOrder = entityManager.find(Order.class, id);
        entityManager.remove(foundOrder);
    }

    @Override
    public List<Order> findAllByUserId(Long userId, Pagination pagination) {
        return entityManager.createQuery(FIND_ALL_ORDERS_BY_USER_ID, Order.class)
                .setParameter("userId", userId)
                .setFirstResult(PaginationUtil.defineFirstResultToEntityManager(pagination))
                .getResultList();
    }
}
