package com.epam.esm.dao.api.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "userId_fk")
    private Long userId;
    @JoinColumn(name = "gift_certificateId_fk")
    private Long giftCertificateId;
    @Column(name = "cost")
    private BigDecimal cost;
    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;

    public Order() {
    }

    public Order(Long id, Long userId, Long giftCertificateId, BigDecimal cost, LocalDateTime purchaseDate) {
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
}
