package com.epam.esm.service.api;

import com.epam.esm.service.api.dto.GiftCertificateDto;
import com.epam.esm.service.api.dto.GiftCertificateQueryParametersDto;

import java.util.List;

public interface GiftCertificateService extends BaseService<GiftCertificateDto> {

    List<GiftCertificateDto> findAll(GiftCertificateQueryParametersDto giftCertificateQueryParametersDto);

    GiftCertificateDto update(GiftCertificateDto giftCertificateDto);
}
