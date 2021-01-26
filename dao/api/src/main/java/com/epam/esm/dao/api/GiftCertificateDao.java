package com.epam.esm.dao.api;

import com.epam.esm.dao.api.entity.GiftCertificate;
import com.epam.esm.dao.api.entity.GiftCertificateQueryParameters;

import java.util.List;

/**
 * Interface {@code GiftCertificateDao} represents an extended abstract behavior
 * for working with the Gift Certificate entity.
 *
 * @author Ivan Yanushkevich
 * @version 1.0
 */
public interface GiftCertificateDao extends BaseDao<GiftCertificate> {

    /**
     * Find all list.
     *
     * @param giftCertificateQueryParameters the gift certificate query parameters
     *                                       to find all records in database
     * @return the list
     */
    List<GiftCertificate> findAll(GiftCertificateQueryParameters giftCertificateQueryParameters);
}
