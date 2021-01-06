package com.epam.esm.service.api;

import com.epam.esm.service.api.dto.GiftCertificateDto;

public interface GiftCertificateService extends BaseService<GiftCertificateDto> {

    GiftCertificateDto update(GiftCertificateDto giftCertificateDto);
}
