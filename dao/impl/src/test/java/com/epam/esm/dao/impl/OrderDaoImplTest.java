package com.epam.esm.dao.impl;

import com.epam.esm.dao.api.OrderDao;
import com.epam.esm.dao.api.entity.Order;
import com.epam.esm.dao.api.entity.Pagination;
import com.epam.esm.dao.configuration.TestConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
class OrderDaoImplTest {

    private final OrderDao orderDao;
    private static Order firstOrder;
    private static Order secondOrder;
    private static Order thirdOrder;
    private static Pagination pagination;

    @Autowired
    public OrderDaoImplTest(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @BeforeAll
    static void setUp() {
        firstOrder = new Order();
        firstOrder.setId(1L);
        firstOrder.setPurchaseDate(LocalDateTime.of(2020, 2, 1, 17, 0, 0));
        firstOrder.setCost(new BigDecimal("2500.00"));
        firstOrder.setUserId(1L);
        firstOrder.setGiftCertificateId(2L);
        secondOrder = new Order();
        secondOrder.setPurchaseDate(LocalDateTime.now());
        secondOrder.setCost(new BigDecimal("50"));
        secondOrder.setUserId(1L);
        secondOrder.setGiftCertificateId(2L);
        thirdOrder = new Order();
        thirdOrder.setPurchaseDate(LocalDateTime.now());
        thirdOrder.setCost(new BigDecimal("50"));
        thirdOrder.setUserId(1L);
        thirdOrder.setGiftCertificateId(2L);
        pagination = new Pagination();
        pagination.setPageNumber(1);
        pagination.setSize(3);
    }

    @AfterAll
    static void tearDown() {
        firstOrder = null;
        secondOrder = null;
        thirdOrder = null;
    }

    @Test
    void methodFindAllByUserIdShouldReturnCorrectSizeOrders(){
        int expectedSize = 1;

        int actualSize = orderDao.findAllByUserId(1L, pagination).size();

        assertEquals(expectedSize, actualSize);
    }

    @Transactional
    @Test
    void methodAddWithCorrectDataShouldReturnOrder(){
        Order orderToAdd = new Order();
        orderToAdd.setPurchaseDate(LocalDateTime.of(2020, 2, 1, 17, 0, 0));
        orderToAdd.setCost(new BigDecimal("100.00"));
        orderToAdd.setUserId(1L);
        orderToAdd.setGiftCertificateId(2L);
        Order expectedOrder = new Order();
        expectedOrder.setId(4L);
        expectedOrder.setPurchaseDate(LocalDateTime.of(2020, 2, 1, 17, 0, 0));
        expectedOrder.setCost(new BigDecimal("100.00"));
        expectedOrder.setUserId(1L);
        expectedOrder.setGiftCertificateId(2L);

        Order actualOrder = orderDao.add(orderToAdd);

        assertEquals(actualOrder, expectedOrder);
    }

    @Test
    void methodFindByIdShouldReturnOptionalOrder(){
        long idToFound = 1L;

        Optional<Order> actualOrder = orderDao.findById(idToFound);

        assertEquals(actualOrder, Optional.of(firstOrder));
    }

    @Test
    void methodFindByIdShouldReturnEmptyOptional(){
        long idToFound = -1L;

        Optional<Order> actualOrder = orderDao.findById(idToFound);

        assertEquals(actualOrder, Optional.empty());
    }

    @Test
    void methodUpdateShouldThrowException(){
        assertThrows(UnsupportedOperationException.class, ()-> orderDao.update(firstOrder));
    }
}
