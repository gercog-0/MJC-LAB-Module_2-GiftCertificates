package com.epam.esm.dao.api.audit;

import com.epam.esm.dao.api.entity.GiftCertificate;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class GiftCertificateAuditListener {

    @PrePersist
    public void beforeCreateGiftCertificate(GiftCertificate giftCertificate) {
        LocalDateTime time = LocalDateTime.now();
        giftCertificate.setCreateDate(time);
        giftCertificate.setLastUpdateDate(time);
    }

    @PreUpdate
    public void beforeUpdateGiftCertificate(GiftCertificate giftCertificate) {
        giftCertificate.setLastUpdateDate(LocalDateTime.now());
    }
}
