package com.epam.esm.controller;

import com.epam.esm.service.api.OrderService;
import com.epam.esm.service.api.dto.OrderDto;
import com.epam.esm.service.api.dto.PaginationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "api/v1/orders")
public class OrderController implements LinkRelationship<OrderDto> {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/user/{userId}")
    public List<OrderDto> findAllOrdersByUserId(@PathVariable long userId,
                                                @RequestParam(required = false) Integer page,
                                                @RequestParam(required = false) Integer size) {
        PaginationDto paginationDto = new PaginationDto(page, size);
        List<OrderDto> ordersDto = orderService.findAllByUserId(userId, paginationDto);
        ordersDto.forEach(this::addDependenciesLinks);
        return ordersDto;
    }

    @GetMapping("/{id}")
    public OrderDto findOrderById(@PathVariable long id) {
        OrderDto orderDto = orderService.findById(id);
        addDependenciesLinks(orderDto);
        return orderDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto addOrder(@RequestBody OrderDto orderDto) {
        OrderDto orderDtoResult = orderService.add(orderDto);
        addDependenciesLinks(orderDtoResult);
        return orderDtoResult;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrderById(@PathVariable long id) {
        orderService.remove(id);
    }

    @Override
    public void addDependenciesLinks(OrderDto orderDto) {
        orderDto.add(linkTo(methodOn(OrderController.class).findOrderById(orderDto.getId())).withSelfRel());
        orderDto.add(linkTo(methodOn(GiftCertificateController.class)
                .findGiftCertificateById(orderDto.getGiftCertificateId())).withRel("gift-certificate"));
        orderDto.add(linkTo(methodOn(UserController.class).findUserById(orderDto.getUserId())).withRel("user"));
    }
}
