package com.epam.esm.service.impl.validator.impl;

import com.epam.esm.service.api.dto.OrderDto;
import com.epam.esm.service.impl.validator.BaseValidator;
import org.springframework.stereotype.Component;

@Component
public class OrderValidatorImpl implements BaseValidator<OrderDto> {

    @Override
    public void validate(OrderDto orderDto) {

    }
}
