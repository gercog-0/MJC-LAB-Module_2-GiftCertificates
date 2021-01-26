package com.epam.esm.controller;

import com.epam.esm.service.api.OrderService;
import com.epam.esm.service.api.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{userId}")
    public List<OrderDto> findAllOrdersByUserId(@PathVariable long userId) {
        return orderService.findAllByUserId(userId);
    }

    @GetMapping("/user/{id}")
    public OrderDto findOrderById(@PathVariable long id) {
        return orderService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto addOrder(@RequestBody OrderDto orderDto) {
        return orderService.add(orderDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrderById(@PathVariable long id) {
        orderService.remove(id);
    }
}
