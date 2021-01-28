package com.epam.esm.service.api;

import com.epam.esm.service.api.dto.GiftCertificateDto;
import com.epam.esm.service.api.dto.GiftCertificateQueryParametersDto;
import com.epam.esm.service.api.dto.PaginationDto;

import java.util.List;

/**
 * The interface Gift certificate service.
 */
public interface GiftCertificateService extends BaseService<GiftCertificateDto> {

    /**
     * Find all list.
     *
     * @param giftCertificateQueryParametersDto the gift certificate query parameters dto
     * @return the list
     */
    List<GiftCertificateDto> findAll(GiftCertificateQueryParametersDto giftCertificateQueryParametersDto,
                                     PaginationDto paginationDto);

    /**
     * Update part gift certificate dto.
     *
     * @param giftCertificateDto the gift certificate dto
     * @return the gift certificate dto
     */
    GiftCertificateDto updatePart(GiftCertificateDto giftCertificateDto);

    /**
     * Update gift certificate dto.
     *
     * @param giftCertificateDto the gift certificate dto
     * @return the gift certificate dto
     */
    GiftCertificateDto update(GiftCertificateDto giftCertificateDto);
}
