package com.epam.esm.service.impl.validator.impl;

import com.epam.esm.service.api.GiftCertificateService;
import com.epam.esm.service.api.UserService;
import com.epam.esm.service.api.dto.OrderDto;
import com.epam.esm.service.impl.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderValidatorImpl implements BaseValidator<OrderDto> {

    private final GiftCertificateService giftCertificateService;
    private final UserService userService;

    @Autowired
    public OrderValidatorImpl(GiftCertificateService giftCertificateService, UserService userService) {
        this.giftCertificateService = giftCertificateService;
        this.userService = userService;
    }

    @Override
    public void validate(OrderDto orderDto) {
        giftCertificateService.findById(orderDto.getGiftCertificateId());
        userService.findById(orderDto.getUserId());
    }
}
