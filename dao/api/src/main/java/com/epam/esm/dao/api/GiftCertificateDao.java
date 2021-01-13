package com.epam.esm.dao.api;

import com.epam.esm.dao.api.entity.GiftCertificate;
import com.epam.esm.dao.api.entity.GiftCertificateQueryParameters;

import java.util.List;

public interface GiftCertificateDao extends BaseDao<GiftCertificate> {
    List<GiftCertificate> findAll(GiftCertificateQueryParameters giftCertificateQueryParameters);

    void removeTagHasGiftCertificate(Long giftCertificateId);
}
