package com.epam.esm.serviceimpl;

import com.epam.esm.serviceapi.dto.GiftCertificateDto;

import java.util.List;
import java.util.Optional;

public class GiftCertificateServiceImpl implements com.epam.esm.serviceapi.GiftCertificateService {
    @Override
    public List<GiftCertificateDto> findAll() {
        return null;
    }

    @Override
    public GiftCertificateDto findById(String id) {
        return new GiftCertificateDto();
    }

    @Override
    public GiftCertificateDto add(GiftCertificateDto giftCertificateDto) {
        return null;
    }


    @Override
    public void remove(String id) {
    }

    @Override
    public boolean update(GiftCertificateDto giftCertificateDto) {
        return false;
    }
}
