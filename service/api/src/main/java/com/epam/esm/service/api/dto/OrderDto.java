package com.epam.esm.service.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class OrderDto {

    private Long id;
    private UserDto user;
    private GiftCertificateDto giftCertificate;
    private BigDecimal cost;
    private LocalDateTime purchaseDate;

    public OrderDto() {
    }

    public OrderDto(Long id, UserDto user, GiftCertificateDto giftCertificate,
                    BigDecimal cost, LocalDateTime purchaseDate) {
        this.id = id;
        this.user = user;
        this.giftCertificate = giftCertificate;
        this.cost = cost;
        this.purchaseDate = purchaseDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public GiftCertificateDto getGiftCertificate() {
        return giftCertificate;
    }

    public void setGiftCertificate(GiftCertificateDto giftCertificate) {
        this.giftCertificate = giftCertificate;
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
                Objects.equals(user, orderDto.user) &&
                Objects.equals(giftCertificate, orderDto.giftCertificate) &&
                Objects.equals(cost, orderDto.cost) &&
                Objects.equals(purchaseDate, orderDto.purchaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, giftCertificate, cost, purchaseDate);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderDto{");
        sb.append("id=").append(id);
        sb.append(", user=").append(user);
        sb.append(", giftCertificate=").append(giftCertificate);
        sb.append(", cost=").append(cost);
        sb.append(", purchaseDate=").append(purchaseDate);
        sb.append('}');
        return sb.toString();
    }
}
