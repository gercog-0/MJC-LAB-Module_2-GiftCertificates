package com.epam.esm.service.api.dto;

import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class OrderDto extends RepresentationModel<OrderDto> {

    private Long id;
    private Long userId;
    private Long giftCertificateId;
    private BigDecimal cost;
    private LocalDateTime purchaseDate;

    public OrderDto() {
    }

    public OrderDto(Long id, Long userId, Long giftCertificateId, BigDecimal cost, LocalDateTime purchaseDate) {
        this.id = id;
        this.userId = userId;
        this.giftCertificateId = giftCertificateId;
        this.cost = cost;
        this.purchaseDate = purchaseDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGiftCertificateId() {
        return giftCertificateId;
    }

    public void setGiftCertificateId(Long giftCertificateId) {
        this.giftCertificateId = giftCertificateId;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return Objects.equals(id, orderDto.id) &&
                Objects.equals(userId, orderDto.userId) &&
                Objects.equals(giftCertificateId, orderDto.giftCertificateId) &&
                Objects.equals(cost, orderDto.cost) &&
                Objects.equals(purchaseDate, orderDto.purchaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, giftCertificateId, cost, purchaseDate);
    }
}
