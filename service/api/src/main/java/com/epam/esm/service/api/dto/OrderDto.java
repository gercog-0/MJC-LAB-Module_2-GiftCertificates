package com.epam.esm.service.api.dto;

import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The type Order dto.
 */
public class OrderDto extends RepresentationModel<OrderDto> {

    private Long id;
    private Long userId;
    private Long giftCertificateId;
    private BigDecimal cost;
    private LocalDateTime purchaseDate;

    /**
     * Instantiates a new Order dto.
     */
    public OrderDto() {
    }

    /**
     * Instantiates a new Order dto.
     *
     * @param id                the id
     * @param userId            the user id
     * @param giftCertificateId the gift certificate id
     * @param cost              the cost
     * @param purchaseDate      the purchase date
     */
    public OrderDto(Long id, Long userId, Long giftCertificateId, BigDecimal cost, LocalDateTime purchaseDate) {
        this.id = id;
        this.userId = userId;
        this.giftCertificateId = giftCertificateId;
        this.cost = cost;
        this.purchaseDate = purchaseDate;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Gets gift certificate id.
     *
     * @return the gift certificate id
     */
    public Long getGiftCertificateId() {
        return giftCertificateId;
    }

    /**
     * Sets gift certificate id.
     *
     * @param giftCertificateId the gift certificate id
     */
    public void setGiftCertificateId(Long giftCertificateId) {
        this.giftCertificateId = giftCertificateId;
    }

    /**
     * Gets cost.
     *
     * @return the cost
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * Sets cost.
     *
     * @param cost the cost
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     * Gets purchase date.
     *
     * @return the purchase date
     */
    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * Sets purchase date.
     *
     * @param purchaseDate the purchase date
     */
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
